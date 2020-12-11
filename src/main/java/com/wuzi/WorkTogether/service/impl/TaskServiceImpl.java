package com.wuzi.WorkTogether.service.impl;

import com.wuzi.WorkTogether.dao.TaskDao;
import com.wuzi.WorkTogether.domain.SubTask;
import com.wuzi.WorkTogether.domain.Task;
import com.wuzi.WorkTogether.domain.dto.SubTaskDto;
import com.wuzi.WorkTogether.domain.dto.TaskDto;
import com.wuzi.WorkTogether.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * 查询当前用户的所有任务
     * @param userId 用户id
     * @return 任务信息列表
     */
    @Override
    public List<TaskDto> queryAllTaskByUser(Integer userId) {
        List <Task> oriTasks = taskDao.queryAllTaskByUser(userId);
        List <TaskDto> tasks = taskTransform(oriTasks);
        return tasks;
    }


    /**
     * 查询当前团队的所有任务
     * @param teamId 团队ID
     * @return 当前团队的任务列表
     */
    @Override
    public List<TaskDto> queryAllTaskByTeam(Integer teamId) {
        List <Task> oriTasks = taskDao.queryAllTaskByTeam(teamId);
        List <TaskDto> tasks = taskTransform(oriTasks);
        return tasks;
    }

    /**
     * 添加任务
     * @param task 任务信息
     */
    @Override
    public void addTask(Task task) {
        taskDao.addTask(task);
    }

    /**
     * 添加子任务
     * @param subTask 子任务信息
     */
    @Override
    public void addSubTask(SubTask subTask){
        taskDao.addSubTask(subTask);
    }


    /**
     * 根据关键词搜索任务名中包含关键词的所有任务
     * @param userId 用户id
     * @param keyword 搜索关键词
     * @return 匹配的任务列表
     */
    @Override
    public List<TaskDto> queryTaskByKeyword(Integer userId, String keyword) {
        List <Task> oriTasks = taskDao.queryTaskByKeyword(userId,keyword);
        List <TaskDto> tasks = taskTransform(oriTasks);
        return tasks;
    }

    /**
     * 查询任务的所有子任务
     * @param taskId 任务id
     * @return 当前任务的所有子任务
     */
    @Override
    public List<SubTaskDto> querySubTask(Integer taskId) {
        List <SubTask> oriTasks = taskDao.querySubTask(taskId);
        List <SubTaskDto> subTasks = new ArrayList<>();
        for (SubTask t:oriTasks){
            SubTaskDto taskDto = new SubTaskDto();
            taskDto.setContent(t.getContent());
            taskDto.setIsComplete(t.getIsComplete());
            taskDto.setWeight(t.getWeight());
            taskDto.setSubTaskId(t.getSubTaskId());
            subTasks.add(taskDto);
        }
        return subTasks;
    }

    /**
     *
     * @param taskId
     * @return
     */
    @Override
    public String queryTaskName(Integer taskId) {
        return taskDao.queryTaskName(taskId);
    }

    /**
     * 查询当前任务进度
     * @param taskId 任务id
     * @return 任务进度（0-100）
     */
    @Override
    public Integer queryTaskProgress(Integer taskId) {
        return taskDao.queryTaskProgress(taskId);
    }

    /**
     * 完成子任务
     * @param taskId 任务id
     * @param subTaskId 子任务id
     * @return 更新后的子任务列表
     */
    @Override
    public List<SubTaskDto> completeTask(Integer taskId, Integer subTaskId) {
        taskDao.completeSubTask(taskId,subTaskId);
        List<SubTaskDto> subTasks = querySubTask(taskId);
        int progress = 0;
        for (SubTaskDto st : subTasks){
            if(st.getIsComplete()){
                progress+=st.getWeight();
            }
        }
        if (progress==99){
            progress++;
        }
        taskDao.updateTaskProgress(taskId,progress);
        return subTasks;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    /**
     * 将Task转换为TaskDto
     * @param oriTasks 从数据库中查询的原始数据
     * @return 转换后的任务列表
     */
    private List<TaskDto> taskTransform(List<Task> oriTasks){
        List <TaskDto> tasks = new  ArrayList<>();
        for (Task t:oriTasks){
            TaskDto dto = new TaskDto();
            dto.setTaskName(t.getTaskName());
            dto.setTeamId(t.getTeamId());
            if (Objects.isNull(t.getEndTime())){
                dto.setEndTime("无");
            }
            else {
                dto.setEndTime(t.getEndTime());
            }
            dto.setTaskId(t.getTaskId());
            dto.setTaskProgress(t.getTaskProgress());
            dto.setPriority(t.getPriority());
            tasks.add(dto);
        }
        Collections.reverse(tasks);
        return tasks;
    }
}
