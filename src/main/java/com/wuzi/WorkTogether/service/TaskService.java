package com.wuzi.WorkTogether.service;

import com.wuzi.WorkTogether.domain.SubTask;
import com.wuzi.WorkTogether.domain.Task;
import com.wuzi.WorkTogether.domain.dto.SubTaskDto;
import com.wuzi.WorkTogether.domain.dto.TaskDto;

import java.util.List;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/11/8 12:49
 * @lastEditor
 */
public interface TaskService {
    public List<TaskDto> queryAllTaskByUser(Integer userId);

    public List<TaskDto> queryAllTaskByTeam(Integer teamId);

    public void addTask(Task task);

    public void addSubTask(SubTask subTask);

//    public void updateTaskProgress(Task task);

    public List<TaskDto> queryTaskByKeyword(Integer userId,String keyword);

    public List<SubTaskDto> querySubTask(Integer taskId);

    public String queryTaskName(Integer taskId);

    public Integer queryTaskProgress(Integer taskId);

    public List<SubTaskDto> completeTask(Integer taskId,Integer subTaskId);
}
