package com.z.plugin.webSocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class MyHandshakeInterceptor extends HttpSessionHandshakeInterceptor{
	@Override  
    public boolean beforeHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {  
        System.out.println("Before Handshake......");
      //TODO 以下内容模仿父类
        if(request instanceof ServletServerHttpRequest){
        	ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
        	HttpSession session = servletRequest.getServletRequest().getSession(false);
        	if(session!=null){
        		String sysUserId = (String) session.getAttribute("SESSION_sysUserId");
        		attributes.put("WS_sysUserId", sysUserId);
        		attributes.put("HttpSession", session);
//        		//TODO
//              Map map = HttpSessionMapUtil.getInstance().showHttpSessionMap();
//              HttpSession oldSession = HttpSessionMapUtil.getInstance().getHttpSession(sysUserId);
//              if(oldSession==null){
//                  //未登陆过
//                  HttpSessionMapUtil.getInstance().putHttpSession(sysUserId, session);
//              }else if(oldSession!=null && oldSession.getId().equals(session.getId())){
//                  //同一个浏览器
//              }else{
//                  //不同浏览器
//                  SecurityUtils.getSecurityManager().logout(arg0);
//                  HttpSessionMapUtil.getInstance().putHttpSession(sysUserId, session);
//              }
        	}
        }
        
        return super.beforeHandshake(request, response, wsHandler, attributes);  
    }  
  
    @Override  
    public void afterHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Exception ex) {  
        System.out.println("After Handshake......");  
        super.afterHandshake(request, response, wsHandler, ex);  
    }  
}
