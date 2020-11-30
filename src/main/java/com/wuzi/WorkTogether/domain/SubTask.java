package com.wuzi.WorkTogether.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/11/30 15:06
 * @lastEditor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {
    private Integer id;
    private Integer subTaskId;
    private Integer taskId;
    private String content;
    private Integer weight;
    private Boolean isComplete;
}
