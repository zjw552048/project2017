package com.z.plugin.webSocket;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.z.enums.WebSocketMessageEnum;

public class MyWebSocketHandler extends TextWebSocketHandler{
	private static final Logger logger = Logger.getLogger(MyWebSocketHandler.class);
	
	/**
     * 连接成功时候，会触发页面上onopen方法
     */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws IOException{
        //获取用户信息
        String sysUserId = getSysUserId(session);
        if(sysUserId==null){
            return;
        }
        //获取对应的WSsession
        WebSocketSession oldWSSession = WSSessionMapUtil.getInstance().getWebSocketSession(sysUserId);
        if(oldWSSession!=null && oldWSSession.isOpen()){
            //如果当前WS已存在，推送异地登陆通知下线
            WSSendMessageUtil.sendMessageToUser(sysUserId, 
                    WebSocketMessageEnum.ForcedOfflineMethod, 
                    WebSocketMessageEnum.ForcedOfflineInfo);
            oldWSSession.close();
        }
        while(oldWSSession==null || !oldWSSession.isOpen()){
            //ֱ直到oldWSSession被关闭，再更新map
            WSSessionMapUtil.getInstance().putWebSocketSession(sysUserId, session);
            logger.debug("afterConnectionEstablished......");
            logger.debug("�û���"+sysUserId+" ��½�ɹ�����ǰ�����û�������"+WSSessionMapUtil.getInstance().getWebSocketSessionNumber());
            break;
        }
	}
	
	/**
     * 页面js调用websocket.send(message)发送消息，会调用该方法
     */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException{
        logger.debug("handleTextMessage......");
        //TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        //TODO
        //session.sendMessage(returnMessage);
	}
	
	/**
     * 关闭连接时触发，会触发页面上onclose方法
     */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus){
		String sysUserId = getSysUserId(session);
		WSSessionMapUtil.getInstance().removeWebSocketSession(sysUserId);
		logger.debug("afterConnectionClosed......");
		logger.debug("用户："+sysUserId+" 已下线，当前在线用户数量："+WSSessionMapUtil.getInstance().getWebSocketSessionNumber());
	}
	
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException{
        if(session.isOpen()){
          //移除用户
          String sysUserId = getSysUserId(session);
          WSSessionMapUtil.getInstance().removeWebSocketSession(sysUserId);
          logger.debug("TransportError,websocket connection closed......");
          logger.debug("用户："+sysUserId+" 已下线，当前在线用户数量："+WSSessionMapUtil.getInstance().getWebSocketSessionNumber());
          //session.close()可能会抛出异常
          session.close();
        }
	}
	
	@Override
	public boolean supportsPartialMessages() {
        return false;
    }
	
	/**
	 * 简单封装，获取WebSocketSession中的sysUserId
	 * @author ZhangJiawei
	 * @param session
	 * @return
	 */
	public String getSysUserId(WebSocketSession session){
		return (String) session.getAttributes().get("WS_sysUserId");
	}
	
}
