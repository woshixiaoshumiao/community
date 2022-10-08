package com.zty.community.mapper;

import com.zty.community.model.Question;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.mapper
 * @Author: zhangty
 * @CreateTime: 2022-10-04  15:43
 * @Description: TODO
 * @Version: 1.0
 */
public interface QuestionExtMapper {
    int incView(Question question);
}
