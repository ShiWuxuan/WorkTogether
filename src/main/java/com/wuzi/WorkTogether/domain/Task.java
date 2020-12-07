package com.wuzi.WorkTogether.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/11/8 12:27
 * @lastEditor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Integer taskId;
    private String teamId;
    private String taskName;
    private Integer memberId;
    private String endTime;
    private Integer taskProgress;
    private Integer priority;
}
