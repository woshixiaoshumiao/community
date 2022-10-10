package com.zty.community.controller;

import com.zty.community.dto.CommentDTO;
import com.zty.community.dto.ResultDTO;
import com.zty.community.exception.CustomizeErrorCode;
import com.zty.community.model.Comment;
import com.zty.community.model.User;
import com.zty.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    CommentService commentService;
    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        if(request == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setCommentator(user.getAccountId());
//        comment.setCommentator(1L);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
