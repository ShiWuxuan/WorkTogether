package com.wuzi.WorkTogether.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/11/30 12:40
 * @lastEditor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String userPwd;
    private String teamName;
    private String userTel;
}
