package com.zty.community.service;

import com.zty.community.dto.GitHubUserDTO;
import com.zty.community.mapper.UserMapper;
import com.zty.community.model.User;
import com.zty.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        user.setAccountId(Long.valueOf(gitHubUser.getId()));
        user.setName(gitHubUser.getName());
        user.setAvatarUrl(gitHubUser.getAvatarUrl());
        user.setGmtModified(System.currentTimeMillis());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.isEmpty()){
            //无该用户,进行用户插入操作
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //有该用户，只需要更新该用户信息
            UserExample userExample1 = new UserExample();
            userExample1.createCriteria().andAccountIdEqualTo(user.getAccountId());
            userMapper.updateByExampleSelective(user, userExample1);
        }
        return user;
    }
}
