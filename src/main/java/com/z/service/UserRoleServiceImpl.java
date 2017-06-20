package com.z.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.z.dao.UserRoleMapper;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	UserRoleMapper dao;
	
	@Override
	public List<String> selectRoleIdByUserId(String id) {
		return dao.selectRoleIdByUserId(id);
	}

}
