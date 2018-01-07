package com.myx.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @ClassName:  ControllerExceptionHandler   
 * @Description:TODO(拦截异常的类)   
 * @author: 尹欣
 * @date:   2017年12月4日 下午7:23:39     
 * @Copyright: 2017 
 */  
@ControllerAdvice
public class ControllerExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception{
		log.error("Requst URL : {}, Exception : {}", request.getRequestURL(),e);
		
		if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
			throw e;
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("url",request.getRequestURL());
		mv.addObject("exception", e);
		mv.setViewName("error/error");
		return mv;
	}
	
}
