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
     * ���ӳɹ�ʱ�򣬻ᴥ��ҳ����onopen����
     */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws IOException{
        //��ȡ�û���Ϣ
        String sysUserId = getSysUserId(session);
        if(sysUserId==null){
            return;
        }
        //��ȡ��Ӧ��WSsession
        WebSocketSession oldWSSession = WSSessionMapUtil.getInstance().getWebSocketSession(sysUserId);
        if(oldWSSession!=null && oldWSSession.isOpen()){
            //�����ǰWS�Ѵ��ڣ�������ص�½֪ͨ����
            WSSendMessageUtil.sendMessageToUser(sysUserId, 
                    WebSocketMessageEnum.ForcedOfflineMethod, 
                    WebSocketMessageEnum.ForcedOfflineInfo);
            oldWSSession.close();
        }
        while(oldWSSession==null || !oldWSSession.isOpen()){
            //ֱ��oldWSSession���رգ��ٸ���map
            WSSessionMapUtil.getInstance().putWebSocketSession(sysUserId, session);
            logger.debug("afterConnectionEstablished......");
            logger.debug("�û���"+sysUserId+" ��½�ɹ�����ǰ�����û�������"+WSSessionMapUtil.getInstance().getWebSocketSessionNumber());
            break;
        }
	}
	
	/**
     * ҳ��js����websocket.send(message)������Ϣ������ø÷���
     */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException{
        logger.debug("handleTextMessage......");
        //TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        //TODO
        //session.sendMessage(returnMessage);
	}
	
	/**
     * �ر�����ʱ�������ᴥ��ҳ����onclose����
     */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus){
		String sysUserId = getSysUserId(session);
		WSSessionMapUtil.getInstance().removeWebSocketSession(sysUserId);
		logger.debug("afterConnectionClosed......");
		logger.debug("�û���"+sysUserId+" �����ߣ���ǰ�����û�������"+WSSessionMapUtil.getInstance().getWebSocketSessionNumber());
	}
	
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException{
        if(session.isOpen()){
          //�Ƴ��û�
          String sysUserId = getSysUserId(session);
          WSSessionMapUtil.getInstance().removeWebSocketSession(sysUserId);
          logger.debug("TransportError,websocket connection closed......");
          logger.debug("�û���"+sysUserId+" �����ߣ���ǰ�����û�������"+WSSessionMapUtil.getInstance().getWebSocketSessionNumber());
          //session.close()���ܻ��׳��쳣
          session.close();
        }
	}
	
	@Override
	public boolean supportsPartialMessages() {
        return false;
    }
	
	/**
	 * �򵥷�װ����ȡWebSocketSession�е�sysUserId
	 * @author ZhangJiawei
	 * @param session
	 * @return
	 */
	public String getSysUserId(WebSocketSession session){
		return (String) session.getAttributes().get("WS_sysUserId");
	}
	
}
