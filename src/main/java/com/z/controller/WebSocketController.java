package com.z.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z.plugin.webSocket.WSSendMessageUtil;

@Controller
@RequestMapping("/WebSocketController")
public class WebSocketController {
	
	/**
	 * 发送消息
	 * @author ZhangJiawei
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendMessage")
	@ResponseBody
	public String sendMessage(HttpServletRequest request,
							  @RequestParam String methodName,
							  @RequestParam String parameter){
		String sysUserId = (String) request.getSession().getAttribute("SESSION_sysUserId");
		WSSendMessageUtil.sendMessageToUser(sysUserId, methodName, parameter);
		return null;
	}
}
