package com.zty.community.dto;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.dto
 * @Author: zhangty
 * @CreateTime: 2022-09-05  22:37
 * @Description: TODO
 * @Version: 1.0
 */
public class GitHubUserDTO {
    private String name;
    private String bio;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GitHubUserDTO{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
