package com.zty.community.model;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.model
 * @Author: zhangty
 * @CreateTime: 2022-09-07  16:00
 * @Description: TODO
 * @Version: 1.0
 */
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private Long gmtCreate;
    private Long gmtModified;

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}
