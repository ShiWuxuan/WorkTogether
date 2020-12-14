package com.wuzi.WorkTogether.service.impl;

import com.wuzi.WorkTogether.dao.PostDao;
import com.wuzi.WorkTogether.dao.UserDao;
import com.wuzi.WorkTogether.domain.Post;
import com.wuzi.WorkTogether.domain.Record;
import com.wuzi.WorkTogether.domain.dto.PageDto;
import com.wuzi.WorkTogether.domain.dto.PostDto;
import com.wuzi.WorkTogether.domain.dto.RecordDto;
import com.wuzi.WorkTogether.service.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/12/7 15:40
 * @lastEditor
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    /**
     * 获取一页帖子的内容
     * @param page 当前页数
     * @param size 每页包含的帖子数
     * @return 当前页信息
     */
    @Override
    public PageDto getPostForPage(Integer page,Integer size){
        PageDto pageDto = new PageDto();
        int totalCount = postDao.queryPostCount();
        pageDto.setPagination(totalCount,page,size);
        int startIndex = size*(page-1);
        List<Post> posts = postDao.queryPostForPage(startIndex,size);
        List<PostDto> postDtoList = new ArrayList<>();
        return getPageDto(pageDto, posts, postDtoList);
    }

    @Override
    public PostDto queryPostInfo(Integer postId) {
        Post p =postDao.queryPostInfo(postId);
        PostDto dto = new PostDto();
        dto.setLikeNumber(p.getLikeNumber());
        dto.setId(p.getId());
        dto.setTitle(p.getTitle());
        dto.setTime(p.getTime());
        dto.setDetail(p.getDetail());
        //Todo 查询用户姓名
        dto.setUserName("UZI");
        return dto;
    }

    @Override
    public List<RecordDto> queryPostDetail(Integer postId) {
        List<RecordDto> recordDtoList = new ArrayList<>();
        List<Record> records = postDao.queryAllRecordForPost(postId);
        for (Record r:records){
            RecordDto dto = new RecordDto();
            dto.setContent(r.getContent());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dto.setTime(sdf.format(r.getTime()));
            dto.setUserName(userDao.getUserNameById(r.getUserId()));
            recordDtoList.add(dto);
        }
        return recordDtoList;
    }

    @Override
    public Integer queryRecordNumber(Integer postId) {
        return postDao.queryRecordNumber(postId);
    }

    @Override
    public boolean addRecord(Record record) {
        return postDao.addRecord(record) != 0;
    }

    @Override
    public void likePost(Integer postId) {
        postDao.likePost(postId);
    }

    @Override
    public Integer addPost(Post post) {
        if (Objects.isNull(post.getDetail())){
            post.setDetail("无");
        }
        int result = postDao.addPost(post);
        if(result>0){
            return post.getId();
        }
        else {
            return 0;
        }
    }

    @Override
    public PageDto getMostLikePost() {
        PageDto pageDto = new PageDto();
        pageDto.setShowPre(false);
        pageDto.setShowNext(false);
        List<Post> hotPosts = postDao.queryMostLikePost();
        List<PostDto> postDtoList = new ArrayList<>();
        return getPageDto(pageDto, hotPosts, postDtoList);
    }

    @Override
    public PageDto queryPostByKeyword(String keyword) {
        PageDto pageDto = new PageDto();
        pageDto.setShowPre(false);
        pageDto.setShowNext(false);
        List<Post> posts = postDao.queryPostByKeyword(keyword);
        List<PostDto> postDtoList = new ArrayList<>();
        return getPageDto(pageDto, posts, postDtoList);
    }

    private PageDto getPageDto(PageDto pageDto, List<Post> hotPosts, List<PostDto> postDtoList) {
        for (Post p : hotPosts){
            PostDto dto = new PostDto();
            dto.setId(p.getId());
            dto.setLikeNumber(p.getLikeNumber());
            dto.setTitle(p.getTitle());
            dto.setTime(p.getTime());
            dto.setDetail(p.getDetail());
            dto.setUserName(userDao.getUserNameById(p.getUserId()));
            postDtoList.add(dto);
        }
        pageDto.setPostList(postDtoList);
        return pageDto;
    }
}