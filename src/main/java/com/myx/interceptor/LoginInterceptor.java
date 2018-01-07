package com.myx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**   
 * @ClassName:  LoginInterceptor   
 * @Description:TODO(登录拦截器)   
 * @author: 尹欣
 * @date:   2017年12月8日 下午3:49:35     
 * @Copyright: 2017 
 */  
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getSession().getAttribute("user")== null){
			response.sendRedirect("/admin");
			return false;
		}
		return true;
	}

	
	
	
}
