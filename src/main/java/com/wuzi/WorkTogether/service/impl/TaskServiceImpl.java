package com.wuzi.WorkTogether.service.impl;

import com.wuzi.WorkTogether.dao.TaskDao;
import com.wuzi.WorkTogether.domain.Task;
import com.wuzi.WorkTogether.domain.dto.TaskDto;
import com.wuzi.WorkTogether.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/11/8 12:49
 * @lastEditor
 */


@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    @Override
    public List<TaskDto> queryAllTaskByUser(Integer userId) {
        List <Task> oriTasks = taskDao.queryAllTaskByUser(userId);
        List <TaskDto> tasks = new  ArrayList<>();
        for (Task t:oriTasks){
            TaskDto dto = new TaskDto();
            dto.setTaskName(t.getTaskName());
            dto.setTeamId(t.getTeamId());
            if (Objects.isNull(t.getEndTime())){
                dto.setEndTime("无");
            }
            else {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dto.setEndTime(sdf.format(t.getEndTime()));
            }
            dto.setTaskProgress(t.getTaskProgress());
            dto.setPriority(t.getPriority());
            tasks.add(dto);
        }
        return tasks;
    }

    @Override
    public List<Task> queryAllTaskByTeam(Integer teamId) {
        return taskDao.queryAllTaskByTeam(teamId);
    }

    @Override
    public void addTask(Task task) {
        taskDao.addTask(task);
    }

    @Override
    public void updateTaskProgress(Task task) {
        taskDao.updateTaskProgress(task);
    }

    @Override
    public List<TaskDto> queryTaskByKeyword(Integer userId, String keyword) {
        List <Task> oriTasks = taskDao.queryTaskByKeyword(userId,keyword);
        List <TaskDto> tasks = new ArrayList<>();
        for (Task t:oriTasks){
            TaskDto dto = new TaskDto();
            dto.setTaskName(t.getTaskName());
            dto.setTeamId(t.getTeamId());
            if (Objects.isNull(t.getEndTime())){
                dto.setEndTime("无");
            }
            else {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dto.setEndTime(sdf.format(t.getEndTime()));
            }
            dto.setTaskProgress(t.getTaskProgress());
            dto.setPriority(t.getPriority());
            tasks.add(dto);
        }
        return tasks;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
}
