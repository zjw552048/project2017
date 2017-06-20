package com.z.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z.entity.SysUser;
import com.z.utils.LogUtils;
import com.z.utils.ResponseUtils;
import com.z.utils.ShiroUtils;
import com.z.utils.StringUtils;


@Controller
public class LoginController {
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 转发验证登陆页面
	 * @author ZhangJiawei
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String toLogin(HttpServletRequest request){
	    if(request.getSession().getAttribute("SESSION_sysUserId")==null){
	        return "login";
	    }else{
	        return "index";
	    }
	}
	/**
	 * 验证登陆
	 * @author ZhangJiawei
	 * @param username	当前用户名
	 * @param password	用户密码
	 * @param imgCode	用户输入的验证码
	 * @param session	
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String username, String password,
		   String verificationCode, String remenber, HttpSession session, 
		   HttpServletResponse response){
		//判断信息是否完整
	    //TODO 注释验证码
//		String sessionCaptcha = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//		if(!sessionCaptcha.equals(verificationCode)){
//			return ResponseUtils.error("验证码错误！");
//		}
		if(StringUtils.isEmpty(username)){
			return ResponseUtils.error("用户名错误！");
		}
		if(StringUtils.isEmpty(password)){
			return ResponseUtils.error("密码错误！");
		}
		
		//记住token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		if(remenber!=null){
			token.setRememberMe(true);
		}
		
		try {
			logger.debug("用户："+username+"尝试登陆。。。");
			subject.login(token);
			SysUser sysUser = ShiroUtils.getSysUser();
			//session缓存用户信息
			session.setAttribute("SESSION_sysUserId", sysUser.getId());
			LogUtils.saveLog(sysUser.getId());
			logger.debug("用户："+username+"已登陆");
		} catch (UnknownAccountException e) {
			logger.error("账号不存在！", e);
			return ResponseUtils.error("账号不存在！");
		} catch (DisabledAccountException e) {
			logger.error("账号未启用！", e);
			return ResponseUtils.error("账号未启用！");
		} catch (IncorrectCredentialsException e) {
			logger.error("密码错误！", e);
			return ResponseUtils.error("密码错误！");
		} catch (RuntimeException e) {
			logger.error("未知错误,请联系管理员！", e);
			return ResponseUtils.error("未知错误,请联系管理员！");
		}
		return ResponseUtils.success("");
		
	}
	
	/**
	 * 转发主页面
	 * @author ZhangJiawei
	 * @return
	 */
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	/**
	 * 重定向登出页面
	 * @author ZhangJiawei
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) throws IOException{
		//注销shiro subject
	    SysUser sysUser = ShiroUtils.getSysUser();
	    
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		if(sysUser!=null){
		    LogUtils.saveLog(sysUser.getId());
	        logger.debug("用户："+sysUser.getUsername()+"已退出");
		}
		return "redirect:/login";
	} 
	/**
	 * 转发无权限页面
	 * @author ZhangJiawei
	 * @return
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized(){
		return "unauthorized";
	}
	
	@RequestMapping("/getWSUrl")
	@ResponseBody
	public String getWSUrl(HttpServletRequest request){
		String url = "ws://"+request.getServerName()
					+":"+request.getServerPort()
					+request.getContextPath()
					+"/myWebSocketHandler";
		return url;
	}
	
}
