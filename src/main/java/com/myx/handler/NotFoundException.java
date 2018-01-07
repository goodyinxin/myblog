package com.myx.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**   
 * @ClassName:  NotFoundException   
 * @Description:TODO(自定义异常)   
 * @author: 尹欣
 * @date:   2017年12月4日 下午8:12:26     
 * @Copyright: 2017 
 */  
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
	
}
