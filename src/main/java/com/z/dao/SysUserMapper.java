package com.z.dao;

import com.z.entity.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    //////////////////////////////
    SysUser selectSysUserByUsername(String username);
}