package com.zty.community.model;

import lombok.Data;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.model
 * @Author: zhangty
 * @CreateTime: 2022-09-07  16:00
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Integer accountId;
    private Long gmtCreate;
    private Long gmtModified;
    private String token;
    private String avatarUrl;
}
