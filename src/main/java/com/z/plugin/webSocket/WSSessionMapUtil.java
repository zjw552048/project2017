package com.z.plugin.webSocket;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * WS连接WebSocketSession维护工具类
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
	 * 获取单例方法
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
	 * 新增
	 * @author ZhangJiawei
	 * @param sysUserId
	 * @param session
	 */
	public void putWebSocketSession(String sysUserId,WebSocketSession session){
		wsSessionMap.put(sysUserId, session);
	}
	/**
	 * 删除
	 * @author ZhangJiawei
	 * @param sysUserId
	 */
	public void removeWebSocketSession(String sysUserId){
		wsSessionMap.remove(sysUserId);
	}
	/**
	 * 获取
	 * @author ZhangJiawei
	 * @param sysUserId
	 * @return
	 */
	public WebSocketSession getWebSocketSession(String sysUserId){
		return wsSessionMap.get(sysUserId);
	}
	/**
	 * 返回当前在线数量
	 * @author ZhangJiawei
	 * @return
	 */
	public int getWebSocketSessionNumber(){
		return wsSessionMap.size();
	}
	/**
	 * 返回连接集合
	 * @author ZhangJiawei
	 * @return
	 */
	public List<WebSocketSession> getAllWebSocketSessions(){
		return (List<WebSocketSession>) wsSessionMap.values();
	}
	/**
	 * 返回当前map
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
