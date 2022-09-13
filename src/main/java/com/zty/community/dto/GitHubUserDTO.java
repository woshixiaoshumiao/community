package com.zty.community.dto;

import lombok.Data;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.dto
 * @Author: zhangty
 * @CreateTime: 2022-09-05  22:37
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class GitHubUserDTO {
    private String name;
    private String bio;
    private String id;
    private String avatarUrl;
}
