package com.zty.community.controller;

import com.zty.community.dto.QuestionDTO;
import com.zty.community.mapper.QuestionMapper;
import com.zty.community.mapper.UserMapper;
import com.zty.community.model.Question;
import com.zty.community.model.User;
import com.zty.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
    QuestionService questionService;
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
//      计算PaginationDTO中的成员值
        Integer totalQuestionNum = questionMapper.countAll();
        Integer pageNums = (totalQuestionNum + size - 1)/size;
        List<Integer> pages = null;
//      若page参数和size参数不合法，将其合法化
        if(page < 0){
            page = 1;
        }else if(page > pageNums){
            page = pageNums;
        }
        if(size <= 0){
            size = 5;
        }
        Integer windowSize = pageNums >= 7 ? 7 : pageNums;
        for(int i = -3; i < windowSize; ++i){
            if(page + i > 0 && pages.size() < windowSize){
                pages.add(page + i);
            }
        }


        List<QuestionDTO> questions = questionService.list(page, size);
        model.addAttribute("questions", questions);
        return "index";
    }
}
