package com.z.plugin.webSocket;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * WS����WebSocketSessionά��������
 * @author ZhangJiawei
 *
 */
public class WSSessionMapUtil {
	private static WSSessionMapUtil instance;
	/**
	 * key-sysUserId;
	 * value-WebSocketSession;
	 */
	private static Map<String,WebSocketSession> wsSessionMap;
	
	private WSSessionMapUtil(){
		wsSessionMap = new ConcurrentHashMap<String,WebSocketSession>();
	}
	
	/**
	 * ��ȡ��������
	 * @author ZhangJiawei
	 * @return
	 */
	public static WSSessionMapUtil getInstance(){
		if(instance==null){
			instance = new WSSessionMapUtil();
		}
		return instance;
	}
	/**
	 * ����
	 * @author ZhangJiawei
	 * @param sysUserId
	 * @param session
	 */
	public void putWebSocketSession(String sysUserId,WebSocketSession session){
		wsSessionMap.put(sysUserId, session);
	}
	/**
	 * ɾ��
	 * @author ZhangJiawei
	 * @param sysUserId
	 */
	public void removeWebSocketSession(String sysUserId){
		wsSessionMap.remove(sysUserId);
	}
	/**
	 * ��ȡ
	 * @author ZhangJiawei
	 * @param sysUserId
	 * @return
	 */
	public WebSocketSession getWebSocketSession(String sysUserId){
		return wsSessionMap.get(sysUserId);
	}
	/**
	 * ���ص�ǰ��������
	 * @author ZhangJiawei
	 * @return
	 */
	public int getWebSocketSessionNumber(){
		return wsSessionMap.size();
	}
	/**
	 * �������Ӽ���
	 * @author ZhangJiawei
	 * @return
	 */
	public List<WebSocketSession> getAllWebSocketSessions(){
		return (List<WebSocketSession>) wsSessionMap.values();
	}
	/**
	 * ���ص�ǰmap
	 * @author ZhangJiawei
	 * @return
	 */
	public Map<String,WebSocketSession> showWebSocketSessionMap(){
		Iterator<Map.Entry<String,WebSocketSession>> it = wsSessionMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,WebSocketSession> entry = it.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		return wsSessionMap;
	}
	
	
	
}
