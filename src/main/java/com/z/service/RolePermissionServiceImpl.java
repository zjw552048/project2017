package com.z.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.z.dao.RolePermissionMapper;

@Service
public class RolePermissionServiceImpl implements RolePermissionService{
	@Autowired
	RolePermissionMapper dao;
	
	@Override
	public List<String> selectPermissionByRoleIdList(List<String> roleIdList) {
		return dao.selectPermissionByRoleIdList(roleIdList);
	}

}
