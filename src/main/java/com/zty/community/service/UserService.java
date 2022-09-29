package com.zty.community.service;

import com.zty.community.dto.GitHubUserDTO;
import com.zty.community.mapper.UserMapper;
import com.zty.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.UUID;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.service
 * @Author: zhangty
 * @CreateTime: 2022-09-29  09:40
 * @Description: TODO
 * @Version: 1.0
 */
@Service

public class UserService {
    @Autowired
    UserMapper userMapper;
    public User createOrUpdateUser(GitHubUserDTO gitHubUser, User user){

        user.setAccountId(Integer.valueOf(gitHubUser.getId()));
        user.setName(gitHubUser.getName());
        user.setAvatarUrl(gitHubUser.getAvatarUrl());
        user.setGmtModified(System.currentTimeMillis());
        if(userMapper.findById(Integer.valueOf(gitHubUser.getId())) == null){
            //无该用户,进行用户插入操作
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
        }else{
            //有该用户，只需要更新该用户信息
            userMapper.updateUser(user.getAccountId(), user.getName(), user.getAvatarUrl(), user.getToken(), user.getGmtModified());
        }
        return user;
    }
}
