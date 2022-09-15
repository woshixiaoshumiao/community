package com.zty.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.dto
 * @Author: zhangty
 * @CreateTime: 2022-09-14  23:27
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class PaginationDTO {
    List<QuestionDTO> questions;
    List<Integer> pages;
    Integer curPage;
    Integer totalPages;
    Boolean showTurnFront;
    Boolean showTurnLast;
    Boolean showPreviewKey;
    Boolean showNextKey;
}
