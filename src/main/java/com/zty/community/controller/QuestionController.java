package com.zty.community.controller;

import com.zty.community.dto.QuestionDTO;
import com.zty.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    private QuestionService questionService;

    @GetMapping("/question/{questionId}")
    public String viewQuestion(@PathVariable(name = "questionId") Long id,
                               Model model){
        QuestionDTO questionDTO = questionService.getQuestionDetailById(id);
        model.addAttribute("questionDetail", questionDTO);
        questionService.incView(id);
        return "question";
    }
}
