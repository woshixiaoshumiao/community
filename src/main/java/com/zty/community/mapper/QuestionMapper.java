package com.zty.community.mapper;

import com.zty.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("select * from question limit #{offset}, #{size}")
    public List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select * from question where creator = #{id} limit #{offset}, #{size}")
    public List<Question> listByUserId(@Param("id") Integer id, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    public Integer countAll();

    @Select("select count(1) from question where creator = #{id}")
    Integer countQuestionByUserId(@Param("id") Integer id);
}
