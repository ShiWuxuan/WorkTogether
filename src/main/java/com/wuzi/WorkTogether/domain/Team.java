package com.wuzi.WorkTogether.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/12/3 13:40
 * @lastEditor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private Integer teamId;
    private String teamName;
    private String leaderTel;
    private String memberTel;
    private Integer memberNum;
    private Integer memberNumLimit;
    private String teamIntroduction;
}
