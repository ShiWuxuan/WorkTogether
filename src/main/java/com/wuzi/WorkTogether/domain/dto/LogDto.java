package com.wuzi.WorkTogether.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 王子皓
 * @title
 * @Package com.wuzi.WorkTogether.domain.dto
 * @date 2020/11/24 21:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDto {
    private String teamId;
    private Integer logType;//0:日志;1:周志;2:阶段总结;
    private String submitTime;
    private String logTitle;
    private String logContent;
}
