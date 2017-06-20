package com.z.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.z.dao.SysPermissionsMapper;
import com.z.entity.SysPermissions;

@Service
public class SysPermissionsServiceImple implements SysPermissionsService{
	@Autowired
	SysPermissionsMapper dao;
	
	@Override
	public void insertSysPermissions(SysPermissions sysPermissions) {
		dao.insert(sysPermissions);
	}

	@Override
	public void deleteSysPermissionsById(long id) {
		dao.deleteByPrimaryKey(id);
	}

	@Override
	public void updateSysPermissions(SysPermissions sysPermissions) {
		dao.updateByPrimaryKey(sysPermissions);
	}

	@Override
	public void selectSysPermissionsById(long id) {
		dao.selectByPrimaryKey(id);
	}

}
