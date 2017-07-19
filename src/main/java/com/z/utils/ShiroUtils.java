package com.z.utils;

import org.apache.shiro.SecurityUtils;

import com.z.entity.SysUser;
import com.z.entity.common.ShiroUser;

public class ShiroUtils {
	/**
	 * 获取当前登录的认证实体
	 * @author ZhangJiawei
	 * @return
	 */
	public static ShiroUser getPrincipal(){
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return shiroUser;
	}
	
	/**
	 * 获取当前认证实体的用户对象
	 * @author ZhangJiawei
	 * @return
	 */
	public static SysUser getSysUser(){
		return getPrincipal().getSysUser();
	}
	
}
