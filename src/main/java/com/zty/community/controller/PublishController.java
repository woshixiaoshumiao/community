package com.zty.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.controller
 * @Author: yanhongwei
 * @CreateTime: 2022-09-08  22:20
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
