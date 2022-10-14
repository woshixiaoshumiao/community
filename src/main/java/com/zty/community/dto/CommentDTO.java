package com.zty.community.dto;

import com.zty.community.model.User;
import lombok.Data;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.dto
 * @Author: zhangty
 * @CreateTime: 2022-10-13  13:34
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String content;
    private User user;
}
