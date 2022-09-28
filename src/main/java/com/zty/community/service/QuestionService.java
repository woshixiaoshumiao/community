package com.zty.community.service;

import com.zty.community.dto.QuestionDTO;
import com.zty.community.mapper.QuestionMapper;
import com.zty.community.mapper.UserMapper;
import com.zty.community.model.Question;
import com.zty.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.service
 * @Author: zhangty
 * @CreateTime: 2022-09-14  10:20
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public List<QuestionDTO> list(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            if(Objects.isNull(question.getCommentCount())){
                question.setCommentCount(0);
            }
            if(Objects.isNull(question.getLikeCount())){
                question.setLikeCount(0);
            }
            if(Objects.isNull(question.getViewCount())){
                question.setViewCount(0);
            }
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }

    public List<QuestionDTO> listByUserId(Integer userId, Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            if(Objects.isNull(question.getCommentCount())){
                question.setCommentCount(0);
            }
            if(Objects.isNull(question.getLikeCount())){
                question.setLikeCount(0);
            }
            if(Objects.isNull(question.getViewCount())){
                question.setViewCount(0);
            }
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }
    public QuestionDTO getQuestionDetailById(Integer id){
        Question question = questionMapper.getQuestionById(id);
        Integer userId = question.getCreator();
        User user = userMapper.findById(userId);
        QuestionDTO questionDTO = new QuestionDTO();
        if(Objects.isNull(question.getCommentCount())){
            question.setCommentCount(0);
        }
        if(Objects.isNull(question.getLikeCount())){
            question.setLikeCount(0);
        }
        if(Objects.isNull(question.getViewCount())){
            question.setViewCount(0);
        }
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }
}
