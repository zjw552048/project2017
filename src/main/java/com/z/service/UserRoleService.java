package com.z.service;

import java.util.List;


public interface UserRoleService {
	List<String> selectRoleIdByUserId(String id);
}
