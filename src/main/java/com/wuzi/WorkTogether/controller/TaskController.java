package com.wuzi.WorkTogether.controller;

import com.wuzi.WorkTogether.domain.Task;
import com.wuzi.WorkTogether.domain.dto.TaskDto;
import com.wuzi.WorkTogether.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/11/8 14:35
 * @lastEditor
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @RequestMapping("/myTask/{userId}")
    public String userAllTask(Model model, @PathVariable Integer userId){
        List<TaskDto> tasks = taskService.queryAllTaskByUser(userId);
        model.addAttribute("tasks",tasks);
        return "myTask";
    }

    @RequestMapping("/addTask/{task}")
    public String addTask(@PathVariable Task task){
        taskService.addTask(task);
        return "redirect:myTask";
    }

    @RequestMapping("/updateTask/{userId}/{progress}")
    public String updateTask(@PathVariable Integer userId,@PathVariable Integer progress){
        Task task = new Task();
        task.setMemberId(userId);
        task.setTaskProgress(progress);
        taskService.addTask(task);
        return "redirect:myTask";
    }


    @RequestMapping("/myUrgentTask/{userId}")
    public String showUrgentTask(Model model,@PathVariable Integer userId){
        List<TaskDto> tasks = taskService.queryAllTaskByUser(userId);
        tasks.removeIf(t -> t.getPriority() != 2);
        model.addAttribute("tasks",tasks);
        return "myTask";
    }

    @RequestMapping("queryTask/{userId}")
    public String queryTaskByUser(Model model,@PathVariable Integer userId,@RequestParam String keyword){
        List<TaskDto> tasks = taskService.queryTaskByKeyword(userId,keyword);
        model.addAttribute("tasks",tasks);
        return "myTask";
    }

}
