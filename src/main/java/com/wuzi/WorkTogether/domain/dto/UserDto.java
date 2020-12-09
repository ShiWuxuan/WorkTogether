package com.wuzi.WorkTogether.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/11/30 13:10
 * @lastEditor
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String userName;
    private String teamName;
    private String userTel;
    private String userType;
}
