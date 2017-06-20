package com.z.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.z.dao.SysRoleMapper;
import com.z.entity.SysRole;

@Service
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	SysRoleMapper dao;
	@Override
	public void insertSysRole(SysRole sysRole) {
		dao.insert(sysRole);
	}

	@Override
	public void deleteSysRoleById(long id) {
		dao.deleteByPrimaryKey(id);
	}

	@Override
	public void updateSysRole(SysRole sysRole) {
		dao.updateByPrimaryKey(sysRole);
	}

	@Override
	public void selectSysRoleById(long id) {
		dao.selectByPrimaryKey(id);
	}

}
