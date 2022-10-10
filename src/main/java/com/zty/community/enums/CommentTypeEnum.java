package com.zty.community.enums;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.enums
 * @Author: zhangty
 * @CreateTime: 2022-10-09  20:54
 * @Description: TODO
 * @Version: 1.0
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if(value.getType() == type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
