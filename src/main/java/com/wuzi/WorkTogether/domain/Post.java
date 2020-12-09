package com.wuzi.WorkTogether.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/12/7 15:15
 * @lastEditor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    private String title;
    private String time;
    private Integer userId;
    private Integer likeNumber;
    private String detail;
}
