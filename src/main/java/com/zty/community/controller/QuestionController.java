package com.zty.community.controller;

import com.zty.community.dto.CommentDTO;
import com.zty.community.dto.QuestionDTO;
import com.zty.community.service.CommentService;
import com.zty.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{questionId}")
    public String viewQuestion(@PathVariable(name = "questionId") Long id,
                               Model model){
        QuestionDTO questionDTOList = questionService.getQuestionDetailById(id);
        List<CommentDTO> commentDTOList = commentService.getCommentByQuestionId(id);
        model.addAttribute("questionDetail", questionDTOList);
        model.addAttribute("comments", commentDTOList);
        questionService.incView(id);
        return "question";
    }
}
