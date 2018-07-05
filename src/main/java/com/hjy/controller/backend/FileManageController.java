package com.hjy.controller.backend;

import com.hjy.common.ServerResponse;
import com.hjy.entity.FileInfo;
import com.hjy.service.CourseService;
import com.hjy.service.FileInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 *
 * @author hjy
 * @create 2018/06/29
 **/
@RestController
@RequestMapping("console/file/")
public class FileManageController {

	private Logger logger = LoggerFactory.getLogger(FileManageController.class);

	@Autowired
	private FileInfoService fileInfoService;

	/**
	 * 显示所有文件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping(value = "list")
	public ServerResponse getList(
			@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
		//return  .getCourseList(pageNum,pageSize,majorName);

		return fileInfoService.getFileInfoList(pageNum,pageSize);
	}


	@PutMapping(value = "{file_hash}")
	public ServerResponse update(@PathVariable("file_hash")String file_hash,
								 @RequestBody FileInfo fileInfo) {
		logger.info("update更新");
		logger.info(fileInfo.toString() );
		return fileInfoService.updateFileInfo(fileInfo);
	}

	@DeleteMapping(value = "/{user_id}/{fileHashs}")
	public ServerResponse delete(@PathVariable("user_id")Long user_id,
								 @PathVariable("fileHashs")String fileHashs){

		logger.info(fileHashs);
		return fileInfoService.deleteFileInfoById(user_id,fileHashs);
	}


	@PostMapping(value = "/{user_id}")
	public ServerResponse insert(@PathVariable("user_id")Long user_id,
								 @RequestBody  FileInfo fileInfo) {
		logger.info(user_id.toString());
		logger.info(fileInfo.toString());
		return fileInfoService.insertFileInfo(fileInfo,user_id);
	}

	@GetMapping(value = "filter")
	public ServerResponse search(@RequestParam(value = "user_id")Long user_id,
								 @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
								 @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
		logger.info("请求到个人发送文件信息" + user_id);
		return fileInfoService.getFileInfoListByUserId(pageNum,pageSize,user_id);
	}


	public ServerResponse collectOrLikeOrDownloadFile() {

		return null;
	}
}
