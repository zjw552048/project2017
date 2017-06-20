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
     * ��������URL����Ȩ���ַ���������ֻ���������ȶԵ��½���Realm 
     * @param request 
     * @return 
     */  
    protected String[] buildPermissions(ServletRequest request) {  
        String[] perms = new String[1];  
        HttpServletRequest req = (HttpServletRequest) request;  
        String path = req.getServletPath();
        
        /*
         *  �����Ҫ�ּ���ô�������򣬷���controller��action
         *	String regex = "/(.*?)/(.*?)\\.(.*)";
         *  if(path.matches(regex)){
         *  	Pattern pattern = Pattern.compile(regex); 
         *  	Matcher matcher = pattern.matcher(path); 
         *  	String controller =  matcher.group(1); 
         *  	String action = matcher.group(2); 
         *  }
         */
        //pathֱ����ΪȨ���ַ���  
        perms[0] = path;
        return perms;  
    }
    /**
     * �������ɵ�Ȩ���ַ����������෽�����ж��Ƿ�ӵ��Ȩ��
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request,  
            ServletResponse response, Object mappedValue) throws IOException {  
         return super.isAccessAllowed(request, response, buildPermissions(request));  
    } 
    /**
     * ��û��Ȩ��ʱ
     */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		String header = WebUtils.toHttp(request).getHeader("x-requested-with");
		boolean isAjax = header!=null && header.equalsIgnoreCase("XMLHttpRequest");
		
		Subject subject = getSubject(request, response);
		if(subject.getPrincipal() == null){
			//δ��¼
			if(isAjax){
				//��Principal��ajax����,�����������ش������
				WebUtils.toHttp(response).sendError(490);
			}else{
				//��Principal����ͨ����ֱ���ض��򵽵�¼ҳ��
				saveRequestAndRedirectToLogin(request, response);
			}
        }else{
        	//�ѵ�¼
        	if(isAjax){
        		//��Principal��ajax���������������ش������
        		WebUtils.toHttp(response).sendError(491);
        	}else{
        		//���²ο�AuthorizationFilter��
        		String unauthorizedUrl = getUnauthorizedUrl();
            	if(StringUtils.hasText(unauthorizedUrl)){
            		//���趨��ԽȨҳ��ʱ����ת����¼ҳ��
            		WebUtils.issueRedirect(request, response, unauthorizedUrl);
            	}else{
            		//δ�趨ԽȨҳ��ʱ�����ش������
            		WebUtils.toHttp(response).sendError(401);
            	}
        	}
        }
        return false;
	}

}
