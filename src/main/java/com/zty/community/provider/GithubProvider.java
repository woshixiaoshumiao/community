package com.zty.community.provider;

import com.alibaba.fastjson.JSON;
import com.zty.community.dto.AccessTokenDTO;
import com.zty.community.dto.GitHubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.provider
 * @Author: zhangty
 * @CreateTime: 2022-09-05  21:51
 * @Description: TODO
 * @Version: 1.0
 */

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            System.out.println(str);
            String token = str.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public GitHubUserDTO getUser(String accessToken){
//        Request request = new Request.Builder()
//                .url("https://api.github.com/user?access_token=" + accessToken)
//                .build();
        Request request = new Request.Builder().url("https://api.github.com/user?access_token=").header("Authorization","token "+accessToken).build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GitHubUserDTO gitHubUserDTO = JSON.parseObject(string, GitHubUserDTO.class);
            return gitHubUserDTO;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
