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
	 * ת����֤��½ҳ��
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
	 * ��֤��½
	 * @author ZhangJiawei
	 * @param username	��ǰ�û���
	 * @param password	�û�����
	 * @param imgCode	�û��������֤��
	 * @param session	
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String username, String password,
		   String verificationCode, String remenber, HttpSession session, 
		   HttpServletResponse response){
		//�ж���Ϣ�Ƿ�����
	    //TODO ע����֤��
//		String sessionCaptcha = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//		if(!sessionCaptcha.equals(verificationCode)){
//			return ResponseUtils.error("��֤�����");
//		}
		if(StringUtils.isEmpty(username)){
			return ResponseUtils.error("�û�������");
		}
		if(StringUtils.isEmpty(password)){
			return ResponseUtils.error("�������");
		}
		
		//��סtoken
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		if(remenber!=null){
			token.setRememberMe(true);
		}
		
		try {
			logger.debug("�û���"+username+"���Ե�½������");
			subject.login(token);
			SysUser sysUser = ShiroUtils.getSysUser();
			//session�����û���Ϣ
			session.setAttribute("SESSION_sysUserId", sysUser.getId());
			LogUtils.saveLog(sysUser.getId());
			logger.debug("�û���"+username+"�ѵ�½");
		} catch (UnknownAccountException e) {
			logger.error("�˺Ų����ڣ�", e);
			return ResponseUtils.error("�˺Ų����ڣ�");
		} catch (DisabledAccountException e) {
			logger.error("�˺�δ���ã�", e);
			return ResponseUtils.error("�˺�δ���ã�");
		} catch (IncorrectCredentialsException e) {
			logger.error("�������", e);
			return ResponseUtils.error("�������");
		} catch (RuntimeException e) {
			logger.error("δ֪����,����ϵ����Ա��", e);
			return ResponseUtils.error("δ֪����,����ϵ����Ա��");
		}
		return ResponseUtils.success("");
		
	}
	
	/**
	 * ת����ҳ��
	 * @author ZhangJiawei
	 * @return
	 */
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	/**
	 * �ض���ǳ�ҳ��
	 * @author ZhangJiawei
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) throws IOException{
		//ע��shiro subject
	    SysUser sysUser = ShiroUtils.getSysUser();
	    
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		if(sysUser!=null){
		    LogUtils.saveLog(sysUser.getId());
	        logger.debug("�û���"+sysUser.getUsername()+"���˳�");
		}
		return "redirect:/login";
	} 
	/**
	 * ת����Ȩ��ҳ��
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
