package com.zty.community.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zty.community.dto.CommentDTO;
import com.zty.community.mapper.CommentMapper;
import com.zty.community.model.Comment;
import com.zty.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.controller
 * @Author: zhangty
 * @CreateTime: 2022-10-09  14:34
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO, Model model, HttpServletRequest request){
        if(commentDTO.getContent() == null || commentDTO.getType() == null || commentDTO.getParentId() == null){
            model.addAttribute("postError", "输入参数不能为空");
            return null;
        }
        User user = (User)request.getSession().getAttribute("user");
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setCommentator(user.getAccountId());
//        comment.setCommentator(1L);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        commentMapper.insert(comment);
        return null;
    }
}
