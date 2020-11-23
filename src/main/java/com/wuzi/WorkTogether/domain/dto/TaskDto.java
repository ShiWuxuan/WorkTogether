package com.wuzi.WorkTogether.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/11/8 15:06
 * @lastEditor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String teamId;
    private String taskName;
    private String endTime;
    private Integer taskProgress;
    private Integer priority;
}
