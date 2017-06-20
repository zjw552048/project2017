package com.z.utils;

import org.apache.shiro.SecurityUtils;

import com.z.entity.SysUser;
import com.z.entity.common.ShiroUser;

public class ShiroUtils {
	/**
	 * ��ȡ��ǰ��¼����֤ʵ��
	 * @author ZhangJiawei
	 * @return
	 */
	public static ShiroUser getPrincipal(){
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return shiroUser;
	}
	
	/**
	 * ��ȡ��ǰ��֤ʵ����û�����
	 * @author ZhangJiawei
	 * @return
	 */
	public static SysUser getSysUser(){
		return getPrincipal().getSysUser();
	}
	
}
