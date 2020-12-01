package com.wuzi.WorkTogether.dao;

import com.wuzi.WorkTogether.domain.SubTask;
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

    @Select("select taskProgress from task where taskId=#{taskId}")
    public Integer queryTaskProgress(Integer taskId);

    @Select("select taskName from task where taskId=#{taskId}")
    public String queryTaskName(Integer taskId);

    @Select("select * from subtask where taskId=#{taskId}")
    public List<SubTask> querySubTask(Integer taskId);

    @Insert("insert into task (teamId,taskName,memberId,endTime)values(#{teamId},#{taskName},#{memberId},#{endTime})")
    public void addTask(Task task);

    @Update("update task set taskProgress=#{progress} where taskId=#{id}")
    public void updateTaskProgress(@Param("id")Integer taskId,@Param("progress")Integer progress);

    @Update("update subtask set isComplete=1 where taskId=#{taskId} and subTaskId=#{subTaskId}")
    public void completeSubTask(@Param("taskId") Integer taskId,@Param("subTaskId") Integer subTaskId);
}
