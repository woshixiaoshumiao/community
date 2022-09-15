package com.zty.community.controller;

import com.zty.community.dto.PaginationDTO;
import com.zty.community.mapper.QuestionMapper;
import com.zty.community.mapper.UserMapper;
import com.zty.community.model.User;
import com.zty.community.service.PagiNationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.controller
 * @Author: zhangty
 * @CreateTime: 2022-09-03  23:01
 * @Description: TODO
 * @Version: 1.0
 */
@Controller

public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    PagiNationService pagiNationService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        PaginationDTO paginationDTO = pagiNationService.getPaginationDTO(page, size);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }
}
