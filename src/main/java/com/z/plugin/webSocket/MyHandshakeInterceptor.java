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
        //TODO ��������ģ�¸���
        if(request instanceof ServletServerHttpRequest){
        	ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
        	HttpSession session = servletRequest.getServletRequest().getSession(false);
        	if(session!=null){
        		String sysUserId = (String) session.getAttribute("SESSION_sysUserId");
        		attributes.put("WS_sysUserId", sysUserId);
        		attributes.put("HttpSession", session);
//        		//TODO
//        		Map map = HttpSessionMapUtil.getInstance().showHttpSessionMap();
//        		HttpSession oldSession = HttpSessionMapUtil.getInstance().getHttpSession(sysUserId);
//        		if(oldSession==null){
//        		    //δ��½��
//        		    HttpSessionMapUtil.getInstance().putHttpSession(sysUserId, session);
//        		}else if(oldSession!=null && oldSession.getId().equals(session.getId())){
//        		    //ͬһ�������
//        		}else{
//        		    //��ͬ�����
//        		    SecurityUtils.getSecurityManager().logout(arg0);
//        		    HttpSessionMapUtil.getInstance().putHttpSession(sysUserId, session);
//        		}
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
