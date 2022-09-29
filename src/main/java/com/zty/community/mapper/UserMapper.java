package com.zty.community.mapper;

import com.zty.community.model.User;
import org.apache.ibatis.annotations.*;

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
    @Select("select * from user where account_id = #{id}")
    User findById(@Param("id") Integer id);

    @Update("update user set name=#{name}, avatar_url=#{avatarUrl}, token=#{token}, gmt_modified=#{gmtModified} where account_id=#{accountId}")
    void updateUser(@Param("accountId") Integer accountId, @Param("name") String name,@Param("avatarUrl") String avatarUrl,@Param("token") String token,@Param("gmtModified") Long gmtModified);
}
