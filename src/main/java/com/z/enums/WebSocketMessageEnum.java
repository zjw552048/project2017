package com.z.enums;

public class WebSocketMessageEnum {
	//向前台发送跳转登陆页面信息
	public static final String ForcedOfflineMethod = "forcedOffline";
	public static final String ForcedOfflineInfo = "您的账户已在异地登陆，若非本人操作，请联系管理员修改密码！";
	//向前台发送刷新页面信息
	public static final String RefreshPageMethod = "refreshPage";
	public static final String RefreshPageInfo = "您的浏览器已登入其他用户，即将刷新页面！";
	//向前台发送消息
	public static final String ReceiveMessageMethod = "receiveMessage";
}
