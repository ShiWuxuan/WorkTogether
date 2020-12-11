package com.wuzi.WorkTogether.dao;

import com.wuzi.WorkTogether.domain.Post;
import com.wuzi.WorkTogether.domain.Record;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/12/7 15:26
 * @lastEditor
 */
@Repository
public interface PostDao {

    @Select("select * from post limit #{start},#{size}")
    public List<Post> queryPostForPage(@Param("start") Integer startIndex, @Param("size") Integer size);

    @Select("select count(*) from post")
    public Integer queryPostCount();

    @Select("select * from post where id=#{postId}")
    public Post queryPostInfo(Integer postId);

    @Select("select * from record where postId=#{postId}")
    public List<Record> queryAllRecordForPost(Integer postId);

    @Select("select count(*) from record where postId=#{postId}")
    public Integer queryRecordNumber(Integer postId);

    @Insert("insert into record (postId,userId,content)values(#{postId},#{userId},#{content})")
    public Integer addRecord(Record record);

    @Insert("insert into post (title,userId,detail)values(#{title},#{userId},#{detail})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public Integer addPost(Post post);

    @Update("update post set likeNumber=likeNumber+1 where id=#{postId}")
    public void likePost(Integer postId);
}
