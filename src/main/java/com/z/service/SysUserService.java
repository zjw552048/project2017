package com.z.service;


import com.z.entity.SysUser;

public interface SysUserService {
	
	int insertSysUser(SysUser sysUser);
	
	int deleteSysUserById(long id);
	
	int updateSysUser(SysUser sysUser);
	
	SysUser selectSysUserById(long id);
	
	SysUser selectSysUserByUsername(String username);
}
