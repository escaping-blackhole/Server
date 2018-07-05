package com.hjy.dao;

import com.hjy.entity.FileCreation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author : yanzz
 * @createTime : 03 13:59
 * @description : 
 **/
public interface FileCreationMapper {

    List<FileCreation> getList();
    //根据用户id,得到用户上传文件信息
    List<FileCreation> getListByUserId(@Param("userId") Long userId);

    int deleteByPrimaryKey(Long userId);
    //根据用户id 和文件id 删除
    void deleteSomeFileInfo(@Param("userId") Long userId, @Param("fileHashs") List<String> fileHashs);

    //用户上传文件信息，需要添加
    int insert(@Param("userId") Long userId, @Param("fileHash") String fileHash, @Param("gmtCreate") Date gmtCreate);

    //更新浏览次数和收藏次数和喜欢次数
    int updateDownloadCount(@Param("fileHash") String fileHash);
    int updateCollectCount(@Param("fileHash") String fileHash);
    int updateLikeCount(@Param("fileHash") String fileHash);


}
