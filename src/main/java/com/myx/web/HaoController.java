package com.myx.web;

import com.myx.po.Blog;
import com.myx.po.HaoUser;
import com.myx.service.BlogService;
import com.myx.service.HaoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/admin")
public class HaoController {


    private static final String HAO = "/hao";

    @Autowired
    private HaoUserService haoUserService;

    @GetMapping("/hao")
    public String input(Model model) {
        HaoUser haoUser = haoUserService.getHaoUser(1l);
        model.addAttribute(haoUser);
        return HAO;
    }

}
