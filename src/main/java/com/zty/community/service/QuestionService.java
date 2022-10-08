package com.zty.community.service;

import com.zty.community.dto.QuestionDTO;
import com.zty.community.exception.CustomizeErrorCode;
import com.zty.community.exception.CustomizeException;
import com.zty.community.mapper.QuestionExtMapper;
import com.zty.community.mapper.QuestionMapper;
import com.zty.community.mapper.UserMapper;
import com.zty.community.model.Question;
import com.zty.community.model.QuestionExample;
import com.zty.community.model.User;
import com.zty.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
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
    QuestionExtMapper questionExtMapper;
    @Autowired
    UserMapper userMapper;

    public List<QuestionDTO> list(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdIsNotNull();
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
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
            questionDTO.setUser(users.get(0));
            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }

    public List<QuestionDTO> listByUserId(Integer userId, Integer page, Integer size) {
        Integer offset = size * (page - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
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
            questionDTO.setUser(users.get(0));
            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }
    public QuestionDTO getQuestionDetailById(Integer id){
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new  CustomizeException(CustomizeErrorCode.QUESTION_NOTFOUND);
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(userExample);
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
        questionDTO.setUser(users.get(0));
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //执行问题插入
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            //执行问题更新
            question.setGmtModified(System.currentTimeMillis());
            int result = questionMapper.updateByPrimaryKeySelective(question);
            if(result != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOTFOUND);
            }
        }
    }

    public void incView(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
