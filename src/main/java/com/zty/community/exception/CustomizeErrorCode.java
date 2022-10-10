package com.zty.community.exception;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.exception
 * @Author: zhangty
 * @CreateTime: 2022-10-03  23:59
 * @Description: TODO
 * @Version: 1.0
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题找不到了，要不要换个试试！"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题，要不要换个试试！"),
    NO_LOGIN(2003, "未登录，请先登录"),
    SYS_ERROR(2004, "系统冒烟了，请稍后再试~"),
    TYPE_PARAM_WRONG(2005, "评论类型参数错误"),
    Comment_NOT_FOUND(2006, "该评论未找到，可能已被删除");
    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
