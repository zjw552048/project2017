package com.z.dao;

import java.util.List;


public interface UserRoleMapper {
	List<String> selectRoleIdByUserId(String id);
}
