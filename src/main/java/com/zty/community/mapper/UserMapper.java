package com.zty.community.mapper;

import com.zty.community.controller.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import javax.jws.soap.SOAPBinding;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.mapper
 * @Author: yanhongwei
 * @CreateTime: 2022-09-07  22:51
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (id, account_id, name, token, gmt_create, gmt_modified) " +
            "values (#{id}, #{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified})")
    public void inserUser(User user);
}
