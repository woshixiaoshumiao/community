package com.zty.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.dto
 * @Author: zhangty
 * @CreateTime: 2022-09-20  14:13
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class PageInfoDTO {
    List<Integer> pages;
    Integer totalPages;
    Boolean showTurnFront;
    Boolean showTurnLast;
    Boolean showPreviewKey;
    Boolean showNextKey;
}
