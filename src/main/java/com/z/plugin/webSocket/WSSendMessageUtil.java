package com.z.plugin.webSocket;

import java.io.IOException;
import java.util.List;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import net.sf.json.JSONObject;


public class WSSendMessageUtil {
	/**
	 * ��װ���ط���
	 * @author ZhangJiawei
	 * @param methodName
	 * @param parameter
	 * @return
	 */
	private static String createWSReturnData(String methodName, String parameter) {
		JSONObject data = new JSONObject();
		data.put("method", methodName);
		data.put("param", parameter);
		
		return data.toString();
	}
	/**
     * ��ĳ���û�������Ϣ apply
     *
     * @param userName
     * @param message
     */
    public static void sendMessageToUser(String sysUserId, 
    									 String methodName, String parameter) {
    	WebSocketSession session = WSSessionMapUtil.getInstance().getWebSocketSession(sysUserId);
    	String message = createWSReturnData(methodName,parameter);
        try {
            if (session.isOpen()) {
            	session.sendMessage(new TextMessage(message));
            	System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * �����������û�������Ϣ
     *
     * @param message
     */
    public static void sendMessageToUsers(String methodName, String parameter) {
    	List<WebSocketSession> sessions = WSSessionMapUtil.getInstance().getAllWebSocketSessions();
    	String message = createWSReturnData(methodName,parameter);
        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) {
                	session.sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
