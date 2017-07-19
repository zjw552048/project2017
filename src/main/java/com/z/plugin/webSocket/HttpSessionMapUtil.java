package com.z.plugin.webSocket;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;


/**
 * WS连接HttpSession维护工具类
 * @author ZhangJiawei
 *
 */
public class HttpSessionMapUtil {
	private static HttpSessionMapUtil instance;
	/**
	 * key-sysUserId
	 * value-HttpSession
	 */
	private static Map<String,HttpSession> httpSessionMap;
	
	private HttpSessionMapUtil(){
		httpSessionMap = new ConcurrentHashMap<String,HttpSession>();
	}
	
	/**
	 * 获取单例方法
	 * @author ZhangJiawei
	 * @return
	 */
	public static HttpSessionMapUtil getInstance(){
		if(instance==null){
			instance = new HttpSessionMapUtil();
		}
		return instance;
	}
	/**
	 * 新增
	 * @author ZhangJiawei
	 * @param sysUserId
	 * @param session
	 */
	public void putHttpSession(String sysUserId,HttpSession session){
		httpSessionMap.put(sysUserId, session);
	}
	/**
	 * 删除
	 * @author ZhangJiawei
	 * @param sysUserId
	 */
	public void removeHttpSession(String sysUserId){
		httpSessionMap.remove(sysUserId);
	}
	/**
	 * 获取
	 * @author ZhangJiawei
	 * @param sysUserId
	 * @return
	 */
	public HttpSession getHttpSession(String sysUserId){
		return httpSessionMap.get(sysUserId);
	}
	/**
	 * 返回当前在线数量
	 * @author ZhangJiawei
	 * @return
	 */
	public int getHttpSessionNumber(){
		return httpSessionMap.size();
	}
	/**
	 * 返回连接集合
	 * @author ZhangJiawei
	 * @return
	 */
	public List<HttpSession> getAllHttpSessions(){
		return (List<HttpSession>) httpSessionMap.values();
	}
	/**
	 * 返回当前map
	 * @author ZhangJiawei
	 * @return
	 */
	public Map<String,HttpSession> showHttpSessionMap(){
		Iterator<Map.Entry<String,HttpSession>> it = httpSessionMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,HttpSession> entry = it.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		return httpSessionMap;
	}
}
