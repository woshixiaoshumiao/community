package com.zty.community.mapper;

import com.zty.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.mapper
 * @Author: zhangty
 * @CreateTime: 2022-09-07  15:57
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (id, account_id, name, token, gmt_create, gmt_modified, avatar_url)" +
            " values (#{id}, #{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insertUser(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);
}
