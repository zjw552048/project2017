package com.z.plugin.shiro;

import java.util.List;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.z.entity.SysUser;
import com.z.entity.common.ShiroUser;
import com.z.service.RolePermissionService;
import com.z.service.SysUserService;
import com.z.service.UserRoleService;

/**
 * ͨ���û�����ѯ��ȷ���û���Ϣ����װ���ƣ�shiro�жϵ�½�Ƿ�ɹ�
 * @author ZhangJiawei
 *
 */
public class ShiroDBRealm extends AuthorizingRealm{
	private static final Logger logger = LoggerFactory.getLogger(ShiroDBRealm.class);
	
	@Autowired
	SysUserService sysUserService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	RolePermissionService rolePermissionService;
	
	/**
	 * Shiro��ȡ�����֤��Ϣ
	 * shiro�ж��û���¼�Ƿ�ͨ��
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		logger.debug("Shiro��ʼ��¼��֤�û�:"+token.getUsername());
		SysUser sysUser = sysUserService.selectSysUserByUsername(token.getUsername());
		if(sysUser==null){
			throw new UnknownAccountException();
		}else if(sysUser.getLocked()==true){
			throw new DisabledAccountException();
		}
		List<String> roleIdList = userRoleService.selectRoleIdByUserId(sysUser.getId());
		List<String> permissionList = rolePermissionService.selectPermissionByRoleIdList(roleIdList);
		ShiroUser shiroUser = new ShiroUser(sysUser,roleIdList,permissionList);
		
		//�������û���ȷ���İ�ȫ���ƣ������û��������롢��(��ѡ��)
		logger.debug("��֤ͨ���������û���"+sysUser.getUsername()+"��Ϣ");
		return new SimpleAuthenticationInfo(shiroUser, sysUser.getPassword(),getName());
	}
	
	/**
	 * Shiro��Ȩ��Ϣ���ж��û��Ƿ�ӵ��Ȩ��
	 * Ȩ�޽�ɫӦ���ڴ˷����б�����AuthorizationInfo
	 * ֻ�����Ѿ�����װ��shiroUser�У�ֱ�ӻ�ȡ����
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(shiroUser.getRoleList());
		info.addStringPermissions(shiroUser.getPermissionList());
		return info;
	}
	public static void main(String[] args) {
		ShiroDBRealm a = new ShiroDBRealm();
		ShiroDBRealm b = new ShiroDBRealm();
		System.out.println(a.getName());
		System.out.println(b.getName());
	}
	

}
