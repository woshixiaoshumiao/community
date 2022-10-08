package com.zty.community.exception;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.exception
 * @Author: zhangty
 * @CreateTime: 2022-10-03  23:59
 * @Description: TODO
 * @Version: 1.0
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOTFOUND("你找的问题找不到了，要不要换个试试！");
    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
