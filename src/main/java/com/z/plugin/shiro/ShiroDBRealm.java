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
 * 通过用户名查询正确的用户信息，封装令牌，shiro判断登陆是否成功
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
	 * Shiro获取身份验证信息
	 * shiro判断用户登录是否通过
	 * Authentication 身份认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		logger.debug("Shiro开始登录认证用户:"+token.getUsername());
		SysUser sysUser = sysUserService.selectSysUserByUsername(token.getUsername());
		if(sysUser==null){
			throw new UnknownAccountException();
		}else if(sysUser.getLocked()==true){
			throw new DisabledAccountException();
		}
		List<String> roleIdList = userRoleService.selectRoleIdByUserId(sysUser.getId());
		List<String> permissionList = rolePermissionService.selectPermissionByRoleIdList(roleIdList);
		ShiroUser shiroUser = new ShiroUser(sysUser,roleIdList,permissionList);
		
		//返回由用户名确定的安全令牌，包含用户名、密码、盐(可选项)
		logger.debug("认证通过，缓存用户："+sysUser.getUsername()+"信息");
		return new SimpleAuthenticationInfo(shiroUser, sysUser.getPassword(),getName());
	}
	
	/**
	 * Shiro授权信息，判断用户是否拥有权限
     * 权限角色应该在此方法中被塞进AuthorizationInfo
     * 只不过已经被封装在shiroUser中，直接获取即可
     * Authorization 授权
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
