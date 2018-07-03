package com.hjy.dao;

import com.hjy.entity.Major;

import java.util.List;

public interface MajorMapper {
    int deleteByPrimaryKey(String majorId);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(String majorId);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

	void deleteByMajorIds(List<String> majorList);

    List<Major> getList();

}