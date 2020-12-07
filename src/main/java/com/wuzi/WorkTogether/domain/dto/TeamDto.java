package com.wuzi.WorkTogether.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/12/3 14:20
 * @lastEditor
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private Integer teamId;
    private String teamName;
    private String leaderName;
    private String memberName;
    private Integer memberNum;
    private Integer memberNumLimit;
    private String teamIntroduction;
}
