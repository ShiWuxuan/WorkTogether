package com.wuzi.WorkTogether.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wuzi.WorkTogether.domain.Log;
import com.wuzi.WorkTogether.domain.dto.LogDto;
import com.wuzi.WorkTogether.service.LogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.List;

/**
 * @author 王子皓
 * @title
 * @Package com.wuzi.WorkTogether.controller
 * @date 2020/11/29 21:44
 */
@Controller
@RequestMapping("/log")
public class LogController {
    @Resource
    private LogService logService;

    @RequestMapping("/mylog/{userId}")
    public String getAllLogs(Model model, @PathVariable Integer userId){
        List<LogDto> logs = logService.queryAllLogByUser(userId);
        model.addAttribute("logs", logs);
        return "mylog";
    }

    @RequestMapping("/teamlog/{teamId}")
    public String getAllTeamLogs(Model model, @PathVariable Integer teamId){
        List<LogDto> logs = logService.queryAllLogByTeam(teamId);
        model.addAttribute("logs", logs);
        return "mylog";
    }

    @RequestMapping("/addlog/{log}")
    public String addLog(@PathVariable Log log){
        logService.addLog(log);
        return "redirect:mylog";
    }

    @RequestMapping("/mylog/{userId}/{logType}")
    public String getTypeLogs(Model model, @PathVariable Integer userId, @PathVariable Integer logType){
        List<LogDto> logs = logService.queryUserTypeLog(userId, logType);
        model.addAttribute("logs", logs);
        return "mylog";
    }

    @RequestMapping("/keyLogs/{userId}")
    public String getLogByKeyword(Model model, @PathVariable Integer userId, @RequestParam String keyword){
        List<LogDto> logs = logService.queryLogByKeyword(userId, keyword);
        model.addAttribute("logs", logs);
        return "mylog";
    }

}
