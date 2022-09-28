package com.zty.community.controller;

import com.zty.community.dto.QuestionDTO;
import com.zty.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.controller
 * @Author: zhangty
 * @CreateTime: 2022-09-28  14:19
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{questionId}")
    public String viewQuestion(@PathVariable(name = "questionId") Integer id,
                               Model model){
        QuestionDTO questionDTO = questionService.getQuestionDetailById(id);
        model.addAttribute("questionDetail", questionDTO);
        return "question";
    }
}
