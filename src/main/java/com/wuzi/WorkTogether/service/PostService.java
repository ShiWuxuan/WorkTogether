package com.wuzi.WorkTogether.service;

import com.wuzi.WorkTogether.domain.Post;
import com.wuzi.WorkTogether.domain.Record;
import com.wuzi.WorkTogether.domain.dto.PageDto;
import com.wuzi.WorkTogether.domain.dto.PostDto;
import com.wuzi.WorkTogether.domain.dto.RecordDto;

import java.util.List;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/12/7 15:40
 * @lastEditor
 */
public interface PostService {
    public PageDto getPostForPage(Integer page, Integer size);

    public PostDto queryPostInfo(Integer postId);

    public List<RecordDto> queryPostDetail(Integer postId);

    public Integer queryRecordNumber(Integer postId);

    public boolean addRecord(Record record);

    public void likePost(Integer postId);

    public Integer addPost(Post post);
}
