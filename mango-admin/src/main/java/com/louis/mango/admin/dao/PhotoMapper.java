package com.louis.mango.admin.dao;

import com.louis.mango.admin.model.Photo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PhotoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);

    @Select("select * from photo where user_id = #{userId} and classification = #{classification}")
    List<Photo> getPhotoByClassification(@Param("userId") Long userId, @Param("classification") String classification);
}