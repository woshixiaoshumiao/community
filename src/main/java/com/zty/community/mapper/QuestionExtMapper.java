package com.zty.community.mapper;

import com.zty.community.model.Question;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.mapper
 * @Author: zhangty
 * @CreateTime: 2022-10-04  15:43
 * @Description: TODO
 * @Version: 1.0
 */
@Repository
public interface QuestionExtMapper {
    int incView(Question question);
    int incComment(Question question);
}
