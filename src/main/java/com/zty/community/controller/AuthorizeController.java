package com.zty.community.controller;

import com.zty.community.dto.AccessTokenDTO;
import com.zty.community.dto.GitHubUserDTO;
import com.zty.community.mapper.UserMapper;
import com.zty.community.model.User;
import com.zty.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.controller
 * @Author: yanhongwei
 * @CreateTime: 2022-09-04  23:24
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;
    @Autowired
    UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.redirectUri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUserDTO gitHubUser = githubProvider.getUser(accessToken);
        System.out.println(gitHubUser);
        if(gitHubUser != null){
            //登录成功，写cookies和session
            User user = new User();
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setName(gitHubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        }else{
            //登陆失败，重新登录
            return "redirect:/";
        }
    }
}
