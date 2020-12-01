package com.wuzi.WorkTogether.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/11/30 15:24
 * @lastEditor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTaskDto {
    private Integer subTaskId;
    private String content;
    private Integer weight;
    private Boolean isComplete;
}
