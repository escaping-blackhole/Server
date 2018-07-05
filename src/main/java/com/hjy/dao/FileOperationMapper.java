package com.hjy.dao;

import com.hjy.entity.FileOperation;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 文件操作表，这张表只插入不做更新
 */
public interface FileOperationMapper {

    List<FileOperation> getList();
    int insert(@Param("list") List<FileOperation> fileOperationList);
    List<FileOperation> getListByFileHash(String fileHash);

}
