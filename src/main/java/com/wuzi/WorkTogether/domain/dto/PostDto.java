package com.wuzi.WorkTogether.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/12/7 15:15
 * @lastEditor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Integer id;
    private String title;
    private String time;
    private String userName;
    private Integer likeNumber;
    private String detail;
}
