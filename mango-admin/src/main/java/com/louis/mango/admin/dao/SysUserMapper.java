package com.louis.mango.admin.dao;

import com.louis.mango.admin.model.SysUser;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * @Author wangkaishuang
     * @Description 查询所有用户
     * @Date 2019-06-23 02:23
     * @Param []
     * @Return java.util.List<com.louis.mango.admin.model.SysUser>
     **/
    List<SysUser> findAll();

    /**
     * @Author wangkaishuang
     * @Description 分页查询
     * @Date 2019-06-23 02:24
     * @Param []
     * @Return java.util.List<com.louis.mango.admin.model.SysUser>
     **/
    List<SysUser> findPage();
}