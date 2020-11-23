package com.wuzi.WorkTogether.dao;

import com.wuzi.WorkTogether.domain.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/11/8 12:26
 * @lastEditor
 */
@Repository
public interface TaskDao {
    @Select("select * from task where memberId=#{userId}")
    public List<Task> queryAllTaskByUser(Integer userId);

    @Select("select * from task where teamId=#{teamId}")
    public List<Task> queryAllTaskByTeam(Integer teamId);

    @Select("select * from task where memberId=#{userId} and taskName like '%${keyword}%'")
    public List<Task> queryTaskByKeyword(@Param("userId") Integer userId,@Param("keyword") String keyword);

    @Insert("insert into task (teamId,taskName,memberId,endTime)values(#{teamId},#{taskName},#{memberId},#{endTime})")
    public void addTask(Task task);

    @Update("update task set taskProgress=#{taskProgress} where taskId=#{taskId}")
    public void updateTaskProgress(Task task);
}
