package com.z.plugin.webSocket;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;


/**
 * WS����HttpSessionά��������
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
	 * ��ȡ��������
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
	 * ����
	 * @author ZhangJiawei
	 * @param sysUserId
	 * @param session
	 */
	public void putHttpSession(String sysUserId,HttpSession session){
		httpSessionMap.put(sysUserId, session);
	}
	/**
	 * ɾ��
	 * @author ZhangJiawei
	 * @param sysUserId
	 */
	public void removeHttpSession(String sysUserId){
		httpSessionMap.remove(sysUserId);
	}
	/**
	 * ��ȡ
	 * @author ZhangJiawei
	 * @param sysUserId
	 * @return
	 */
	public HttpSession getHttpSession(String sysUserId){
		return httpSessionMap.get(sysUserId);
	}
	/**
	 * ���ص�ǰ��������
	 * @author ZhangJiawei
	 * @return
	 */
	public int getHttpSessionNumber(){
		return httpSessionMap.size();
	}
	/**
	 * �������Ӽ���
	 * @author ZhangJiawei
	 * @return
	 */
	public List<HttpSession> getAllHttpSessions(){
		return (List<HttpSession>) httpSessionMap.values();
	}
	/**
	 * ���ص�ǰmap
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
