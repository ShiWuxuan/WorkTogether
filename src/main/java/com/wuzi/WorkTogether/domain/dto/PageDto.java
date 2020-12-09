package com.wuzi.WorkTogether.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/12/7 15:28
 * @lastEditor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private List<PostDto> postList;

    //是否展示前一页
    private boolean showPre;

    //是否展示后一页
    private boolean showNext;

    //是否展示第一页
    private boolean showFirst;

    //是否展示最后一页
    private boolean showLast;

    private Integer nextPage;

    private Integer prePage;
    //当前页码
    private int page;

    //当前展示的页码集合
    private List<Integer> pages = new ArrayList<>();

    //所有页数
    private int totalPage;

    public void setPagination(int totalCount, int page, int size) {
        prePage = page-1;
        nextPage = page+1;
        this.page = page;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //page<1就显示1，page>最大页数就显示最大页数
        if (page<1){
            this.page=1;
        }
        if (page> totalPage){
            this.page= totalPage;
        }
        //将需要展示的页码插入到pages中
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        //是否展示上一页
        if (page == 1) {
            showPre = false;
        } else {
            showPre = true;
        }
        //是否展示后一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示第一页
        if (pages.contains(1)) {
            showFirst = false;
        } else {
            showFirst = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showLast = false;
        } else {
            showLast = true;
        }
    }
}
