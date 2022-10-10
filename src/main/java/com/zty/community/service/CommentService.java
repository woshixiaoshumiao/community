package com.zty.community.service;

import com.zty.community.mapper.CommentMapper;
import com.zty.community.mapper.QuestionMapper;
import com.zty.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.service
 * @Author: zhangty
 * @CreateTime: 2022-10-09  20:57
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0){

        }
        commentMapper.insertSelective(comment);
    }
}
