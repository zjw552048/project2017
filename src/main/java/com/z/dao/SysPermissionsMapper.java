package com.z.dao;

import com.z.entity.SysPermissions;

public interface SysPermissionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermissions record);

    int insertSelective(SysPermissions record);

    SysPermissions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermissions record);

    int updateByPrimaryKey(SysPermissions record);
}