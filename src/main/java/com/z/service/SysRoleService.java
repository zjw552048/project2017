package com.z.service;

import com.z.entity.SysRole;

public interface SysRoleService {
	
	void insertSysRole(SysRole sysRole);
	
	void deleteSysRoleById(long id);
	
	void updateSysRole(SysRole sysRole);
	
	void selectSysRoleById(long id);	
}
