package com.z.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {
	public static Map<String,Object> result(Boolean result,Object data){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", result.toString());
		map.put("data", data);
		return map;
	}
	public static Map<String,Object> error(Object data){
		return result(false,data);
	}
	public static Map<String,Object> success(Object data){
		return result(true,data);
	}
}
