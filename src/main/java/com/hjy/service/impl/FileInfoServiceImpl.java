package com.hjy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import com.hjy.common.ResponseCode;
import com.hjy.common.ServerResponse;
import com.hjy.dao.FileCreationMapper;
import com.hjy.dao.FileInfoMapper;
import com.hjy.dao.FileOperationMapper;
import com.hjy.dao.UserMapper;
import com.hjy.entity.FileOperation;
import com.hjy.service.FileInfoService;

import com.hjy.entity.FileInfo;
import com.hjy.vo.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author hjy
 * @create 2018/06/24
 **/
@Service("FileService")
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	FileInfoMapper fileInfoMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	FileOperationMapper fileOperationMapper;
	@Autowired
	FileCreationMapper fileCreationMapper;

	Logger logger = LoggerFactory.getLogger(FileInfoServiceImpl.class);
	/**
	 * 上传文件
	 * @param fileInfo
	 * @param userId
	 * @return
	 */
	@Override
	@Transactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
	public ServerResponse insertFileInfo(FileInfo fileInfo, Long userId) {
		try {
			//第一步添加文件信息
			int result = fileInfoMapper.insert(fileInfo);
			if (result == 0) {
				return ServerResponse.createByErrorMessage("插入文件失败");
			}
			//第二步更改用户积分
			result = userMapper.updateUserIntegral(userId);
			if (result == 0) {
				return ServerResponse.createByErrorMessage("插入文件失败");
			}
			//第三部添加关系表
			result = fileCreationMapper.insert(userId, fileInfo.getFileHash(),new Date());
			if(result == 0) {
				return ServerResponse.createByErrorMessage("插入文件失败");
			}

		} catch (Exception e) {
			return ServerResponse.createByErrorMessage("上传失败");
		}
		return ServerResponse.createBySuccess("上传成功");
	}


	@Override
	public ServerResponse deleteFileInfoById(Long userId, String fileHash) {
		//1.更新用户积分
		int result = userMapper.updateUserIntegralSub(userId);
		if(result == 0) {
			return ServerResponse.createByErrorMessage("删除失败");
		}
		//2.删除创建表关系
		List<String> fileHashList = Splitter.on(",").splitToList(fileHash);
		fileCreationMapper.deleteSomeFileInfo(userId,fileHashList);

		//3.插入文件操作表
		if(CollectionUtils.isEmpty(fileHashList)){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getDesc());
		}
		List<FileOperation> list = new ArrayList<>();
		for (int i = 0; i < fileHashList.size(); i++) {
			FileOperation temp = new FileOperation();
			temp.setUserId(userId);
			temp.setFileHash(fileHashList.get(i));
			temp.setGmtCreate(new Date());
			temp.setStatus((byte)0);
			list.add(temp);
		}
		fileOperationMapper.insert(list);
		return ServerResponse.createBySuccessMessage("删除成功");
	}


	/**
	 *	根据用户hash更新文件名，和课程名
	 *比如说用户更新文件属于课程
	 * 设计到的表有 课程和文件
	 */

	@Override
	public ServerResponse updateFileInfo(FileInfo fileInfo) {
		fileInfoMapper.updateByPrimaryKeySelective(fileInfo);
		return ServerResponse.createBySuccessMessage("更新成功");
	}

	/**
	 * 获取文件信息
	 * 1.获取所有文件信息
	 */
	@Override
	public ServerResponse<PageInfo> getFileInfoList(int pageNum, int pageSize) {

		PageHelper.startPage(pageNum,pageSize);
		List<FileVo> fileVoList = fileInfoMapper.getFile();
		Iterator<FileVo> iterator = fileVoList.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			logger.info(iterator.toString());
		}
		PageInfo<FileVo> pageInfo = new PageInfo<>(fileVoList);
		return ServerResponse.createBySuccess(pageInfo);
	}

	/**
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ServerResponse<PageInfo> getFileInfoListByUserId(int pageNum, int pageSize, Long userId) {
		//分页信息
		List<FileVo> list =  fileInfoMapper.getFileInfo( userId );
		PageInfo<FileVo> pageInfo = new PageInfo<>(list);
		return ServerResponse.createBySuccess(pageInfo);
	}

	@Override
	public ServerResponse updateFileCreation(String fileHash) {
		if ("like".equals(fileHash)) {
			fileCreationMapper.updateLikeCount(fileHash);
		} else  if("download".equals(fileHash)){
			fileCreationMapper.updateDownloadCount(fileHash);
		} else if ("collect".equals(fileHash)) {
			fileCreationMapper.updateCollectCount(fileHash);
		}
		return ServerResponse.createBySuccessMessage("关于文件的小操作成功");
	}


}
