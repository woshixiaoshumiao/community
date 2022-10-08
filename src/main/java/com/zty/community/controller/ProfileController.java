package com.zty.community.controller;

import com.zty.community.dto.PaginationDTO;
import com.zty.community.model.User;
import com.zty.community.service.PagiNationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
    @Autowired
    PagiNationService pagiNationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          Model model,
                          HttpSession session) {
        if (session.getAttribute("user") == null) {
            //可以在首页设置未登录提醒，暂不添加此功能，只作非登录拦截
            model.addAttribute("error", "用户未登录");
            return "redirect:/";
        }
        User user = (User) session.getAttribute("user");
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PaginationDTO paginationDTO = pagiNationService.getPaginationDTO(user.getAccountId(), page, size);
            model.addAttribute("paginationDTO", paginationDTO);
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        return "profile";
    }
}
