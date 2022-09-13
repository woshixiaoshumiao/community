package com.zty.community.mapper;

import com.zty.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.mapper
 * @Author: zhangty
 * @CreateTime: 2022-09-11  11:13
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question(title,description, creator,gmt_create,gmt_modified,comment_count,view_count,like_count,tag)\n" +
            "VALUES" +
            "(#{title},#{description},#{creator},#{gmtCreate},#{gmtModified},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    public Integer insertQuestion(Question question);
}
