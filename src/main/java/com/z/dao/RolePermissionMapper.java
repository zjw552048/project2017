package com.z.dao;

import java.util.List;

public interface RolePermissionMapper {
	List<String> selectPermissionByRoleIdList(List<String> roleIdList);
}
