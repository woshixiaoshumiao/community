package com.zty.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.controller
 * @Author: yanhongwei
 * @CreateTime: 2022-09-03  23:01
 * @Description: TODO
 * @Version: 1.0
 */
@Controller

public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "zty") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }
}
