package com.z.plugin.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

public class URLPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter{
	
	/** 
     * 根据请求URL产生权限字符串，这里只产生，而比对的事交给Realm 
     * @param request 
     * @return 
     */  
    protected String[] buildPermissions(ServletRequest request) {  
        String[] perms = new String[1];  
        HttpServletRequest req = (HttpServletRequest) request;  
        String path = req.getServletPath();
        
        /*
         *  如果需要分级怎么利用正则，分离controller和action
         *	String regex = "/(.*?)/(.*?)\\.(.*)";
         *  if(path.matches(regex)){
         *  	Pattern pattern = Pattern.compile(regex); 
         *  	Matcher matcher = pattern.matcher(path); 
         *  	String controller =  matcher.group(1); 
         *  	String action = matcher.group(2); 
         *  }
         */
        //path直接作为权限字符串  
        perms[0] = path;
        return perms;  
    }
    /**
     * 根据生成的权限字符串交给父类方法，判断是否拥有权限
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request,  
            ServletResponse response, Object mappedValue) throws IOException {  
         return super.isAccessAllowed(request, response, buildPermissions(request));  
    } 
    /**
     * 当没有权限时
     */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		String header = WebUtils.toHttp(request).getHeader("x-requested-with");
		boolean isAjax = header!=null && header.equalsIgnoreCase("XMLHttpRequest");
		
		Subject subject = getSubject(request, response);
		if(subject.getPrincipal() == null){
			//未登录
			if(isAjax){
				//无Principal的ajax请求,向游览器返回错误代码
				WebUtils.toHttp(response).sendError(490);
			}else{
				//无Principal的普通请求，直接重定向到登录页面
				saveRequestAndRedirectToLogin(request, response);
			}
        }else{
        	//已登录
        	if(isAjax){
        		//有Principal的ajax请求，想游览器返回错误代码
        		WebUtils.toHttp(response).sendError(491);
        	}else{
        		//以下参考AuthorizationFilter类
        		String unauthorizedUrl = getUnauthorizedUrl();
            	if(StringUtils.hasText(unauthorizedUrl)){
            		//当设定过越权页面时，跳转至登录页面
            		WebUtils.issueRedirect(request, response, unauthorizedUrl);
            	}else{
            		//未设定越权页面时，返回错误代码
            		WebUtils.toHttp(response).sendError(401);
            	}
        	}
        }
        return false;
	}

}
