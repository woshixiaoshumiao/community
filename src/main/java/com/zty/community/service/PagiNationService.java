package com.zty.community.service;

import com.zty.community.dto.PaginationDTO;
import com.zty.community.dto.QuestionDTO;
import com.zty.community.mapper.QuestionMapper;
import org.omg.CORBA.INTERNAL;
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

    public PaginationDTO getPaginationDTO(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
//        计算PaginationDTO中的成员值
        Integer totalQuestionNum = questionMapper.countAll();
        Integer pageNums = (totalQuestionNum + size - 1) / size;
        List<Integer> pages = new ArrayList<>();
//      若page参数和size参数不合法，将其合法化
        if (page < 0) {
            page = 1;
        } else if (page > pageNums) {
            page = pageNums;
        }
        if (size <= 0) {
            size = 5;
        }
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
        List<QuestionDTO> questions = questionService.list(page, size);

        paginationDTO.setPages(pages);
        paginationDTO.setTotalPages(pageNums);
        paginationDTO.setCurPage(page);
        paginationDTO.setQuestions(questions);
        paginationDTO.setShowNextKey(showNextKey);
        paginationDTO.setShowPreviewKey(showPreviewKey);
        paginationDTO.setShowTurnFront(showTurnFront);
        paginationDTO.setShowTurnLast(showTurnLast);
        return paginationDTO;
    }
}
