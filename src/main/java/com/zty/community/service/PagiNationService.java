package com.zty.community.service;

import com.zty.community.dto.PageInfoDTO;
import com.zty.community.dto.PaginationDTO;
import com.zty.community.dto.QuestionDTO;
import com.zty.community.mapper.QuestionMapper;
import com.zty.community.model.QuestionExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.service
 * @Author: zhangty
 * @CreateTime: 2022-09-15  09:32
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class PagiNationService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;

    public PageInfoDTO getPageInfo(Integer page, Integer size, Integer totalQuestionNum) {
        PageInfoDTO pageInfoDTO = new PageInfoDTO();

//        计算PaginationDTO中的成员值
        Integer pageNums = (totalQuestionNum + size - 1) / size;
        List<Integer> pages = new ArrayList<>();

//        计算需要展示的页面
        int windowSize = pageNums >= 7 ? 7 : pageNums;
        pages.add(page);
        for (int i = 1; i <= 6; ++i) {
            if (pages.size() < windowSize && page - i > 0) {
                pages.add(page - i);
            }
            if (pages.size() < windowSize && page + i <= pageNums) {
                pages.add(page + i);
            }
        }
        Collections.sort(pages);
        //        计算页面导航键值
        Boolean showTurnFront = Boolean.FALSE;
        Boolean showTurnLast = Boolean.FALSE;
        Boolean showPreviewKey = Boolean.FALSE;
        Boolean showNextKey = Boolean.FALSE;
        if (!pages.isEmpty()) {
            if (Objects.equals(page, pages.get(0))) {
                showPreviewKey = Boolean.TRUE;
                showTurnFront = Boolean.TRUE;
            }
            if (Objects.equals(page, pages.get(pages.size() - 1))) {
                showNextKey = Boolean.TRUE;
                showTurnLast = Boolean.TRUE;
            }
        }
        pageInfoDTO.setTotalPages(pageNums);
        pageInfoDTO.setPages(pages);
        pageInfoDTO.setShowNextKey(showNextKey);
        pageInfoDTO.setShowTurnFront(showTurnFront);
        pageInfoDTO.setShowPreviewKey(showPreviewKey);
        pageInfoDTO.setShowTurnLast(showTurnLast);
        return  pageInfoDTO;
    }

    public PaginationDTO getPaginationDTO(Integer userId ,Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalQuestionNum;
        List<QuestionDTO> questions;

        //按照条件获取问题总数量
        if(Objects.equals(userId, -1)){
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdIsNotNull();
            totalQuestionNum = (int)questionMapper.countByExample(questionExample);
        }else{
            //按照用户ID获取问题数量
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andCreatorEqualTo(userId);
            totalQuestionNum = (int)questionMapper.countByExample(questionExample);
        }
        //若page参数和size参数不合法，将其合法化，再按照页码查询问题
        Integer pageNums = (totalQuestionNum + size - 1) / size;
        if (page.compareTo(0) <= 0) {
            page = 1;
        } else if (page.compareTo(pageNums) == 1) {
            if(pageNums.compareTo(0) == 0){
                page = 1;
            }else{
                page = pageNums;
            }
        }
        if (size.compareTo(0) <= 0) {
            size = 5;
        }

        if(userId.equals(-1)){
            questions = questionService.list(page, size);
        }else{
            //按照用户ID获取问题数量
            questions = questionService.listByUserId(userId, page, size);
        }
//        计算PaginationDTO中的成员值
        PageInfoDTO pageInfoDTO = getPageInfo(page, size, totalQuestionNum);

        BeanUtils.copyProperties(pageInfoDTO, paginationDTO);
        paginationDTO.setCurPage(page);
        paginationDTO.setQuestions(questions);
        return paginationDTO;
    }
}
