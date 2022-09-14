package com.zty.community.model;

import lombok.Data;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.model
 * @Author: zhangty
 * @CreateTime: 2022-09-11  11:20
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Integer creator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;

}
