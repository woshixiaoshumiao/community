package com.zty.community.controller;

import com.zty.community.dto.QuestionDTO;
import com.zty.community.mapper.QuestionMapper;
import com.zty.community.model.Question;
import com.zty.community.model.User;
import com.zty.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.controller
 * @Author: zhangty
 * @CreateTime: 2022-09-09  13:46
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class PublishController {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @GetMapping("/publish/{questionId}")
    public String edit(@PathVariable("questionId") Integer id,
                       Model model){
        QuestionDTO questionDTO = questionService.getQuestionDetailById(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        //加入问题id信息
        model.addAttribute("questionId", id);
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam(name = "questionId", required = false) Integer questionId,
            HttpSession session,
            Model model
    ) {
        Question question = new Question();
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        } else {
            //校验输入信息
            model.addAttribute("title", title);
            model.addAttribute("description", description);
            model.addAttribute("tag", tag);
            model.addAttribute("questionId", questionId);
            if(title == null || "".equals(title)){
                model.addAttribute("error", "标题不能为空");
                return "publish";
            }
            if(description == null || "".equals(description)){
                model.addAttribute("error", "问题描述不能为空");
                return "publish";
            }
            if(tag == null || "".equals(tag)){
                model.addAttribute("error", "标签不能为空");
                return "publish";
            }
            User user = (User) session.getAttribute("user");
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setId(questionId);
            questionService.createOrUpdate(question);
        }
        return "redirect:/";
    }
}
