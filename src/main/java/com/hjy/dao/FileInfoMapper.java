package com.hjy.dao;

import com.hjy.entity.FileInfo;
import com.hjy.vo.FileVo;

import java.util.Date;
import java.util.List;

public interface FileInfoMapper {

    int deleteByPrimaryKey(String fileHash);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(String fileHash);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    List<FileInfo> getList();

    List<FileVo> getFile();
    List<FileVo> getFileInfo(Long userId);
}