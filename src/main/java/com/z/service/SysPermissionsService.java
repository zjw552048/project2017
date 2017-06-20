package com.z.service;

import com.z.entity.SysPermissions;

public interface SysPermissionsService {
	
	void insertSysPermissions(SysPermissions sysPermissions);
	
	void deleteSysPermissionsById(long id);
	
	void updateSysPermissions(SysPermissions sysPermissions);
	
	void selectSysPermissionsById(long id);
}
