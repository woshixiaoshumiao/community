package com.zty.community.exception;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.exception
 * @Author: zhangty
 * @CreateTime: 2022-10-03  23:30
 * @Description: TODO
 * @Version: 1.0
 */
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode customizeErrorCode) {
        this.message = customizeErrorCode.getMessage();
        this.code = customizeErrorCode.getCode();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
    public Integer getCode(){
        return this.code;
    }
}
