package com.myx.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**   
 * @ClassName:  WebConfig   
 * @Description:TODO(拦截器配置)   
 * @author: 尹欣
 * @date:   2017年12月8日 下午7:39:28     
 * @Copyright: 2017 
 */  
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/admin/**")//拦截的路径
				.excludePathPatterns("/admin")//排除的拦截路径
				.excludePathPatterns("/admin/login");
	}

	
	
}
