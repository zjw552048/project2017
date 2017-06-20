package com.z.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.z.dao.SysUserMapper;
import com.z.entity.SysUser;

@Service
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	SysUserMapper dao;
	
	@Override
	public int insertSysUser(SysUser sysUser) {
		return dao.insert(sysUser);
	}

	@Override
	public int deleteSysUserById(long id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int updateSysUser(SysUser sysUser) {
		return dao.updateByPrimaryKey(sysUser);
	}

	@Override
	public SysUser selectSysUserById(long id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public SysUser selectSysUserByUsername(String username) {
		return dao.selectSysUserByUsername(username);
	}

}
