package com.myx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**   
 * @ClassName:  AboutShowController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 尹欣
 * @date:   2018年1月8日 下午9:19:07     
 * @Copyright: 2018 
 */  
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
