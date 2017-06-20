package com.z.service;

import java.util.List;

public interface RolePermissionService {
	List<String> selectPermissionByRoleIdList(List<String> roleIdList);
}
