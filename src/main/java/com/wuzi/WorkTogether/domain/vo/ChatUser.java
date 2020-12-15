package com.wuzi.WorkTogether.domain.vo;

import com.google.gson.annotations.Expose;

/**
 * @author 王子皓
 * @title
 * @Package com.wuzi.WorkTogether.domain.vo
 * @date 2020/12/14 19:38
 */
public class ChatUser {
    @Expose
    private String id;

    @Expose
    private String nickname;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + nickname  + "]";
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
