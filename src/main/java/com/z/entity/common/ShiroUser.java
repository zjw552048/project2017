package com.z.entity.common;

import java.io.Serializable;
import java.util.List;

import com.z.entity.SysUser;

public class ShiroUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SysUser sysUser;
	private List<String> roleList;
    private List<String> permissionList;
    
    
	public ShiroUser(SysUser sysUser, List<String> roleList, List<String> permissionList) {
		super();
		this.sysUser = sysUser;
		this.roleList = roleList;
		this.permissionList = permissionList;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public List<String> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	public List<String> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}
	@Override
	public String toString() {
		return "ShiroUser [sysUser=" + sysUser + ", roleList=" + roleList + ", permissionList=" + permissionList + "]";
	}
    
    
    
    
}
