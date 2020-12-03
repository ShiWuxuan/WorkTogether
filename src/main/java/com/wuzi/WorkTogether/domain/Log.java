package com.wuzi.WorkTogether.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 王子皓
 * @title
 * @Package com.wuzi.WorkTogether.domain
 * @date 2020/11/24 21:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private Integer logId;
    private String teamId;
    private Integer memberId;
    private Integer logType; //0:日志;1:周志;2:阶段总结;
    private Date submitTime;
    private String logTitle;
    private String logContent;
}
