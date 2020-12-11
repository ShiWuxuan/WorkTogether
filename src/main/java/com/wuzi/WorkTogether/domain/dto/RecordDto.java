package com.wuzi.WorkTogether.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/12/7 22:54
 * @lastEditor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {
    private String userName;
    private String content;
    private String time;
}
