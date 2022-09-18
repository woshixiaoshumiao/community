package com.zty.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.controller
 * @Author: zhangty
 * @CreateTime: 2022-09-18  22:39
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class ProfileController {
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model){
        if("question".equals(action)){
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "myQuestions");
        }
        return "profile";
    }
}
