package com.wuzi.WorkTogether.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wuzi.WorkTogether.dao.LogDao;
import com.wuzi.WorkTogether.domain.Log;
import com.wuzi.WorkTogether.domain.dto.LogDto;
import com.wuzi.WorkTogether.service.LogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping("/logDetail/{userId}/{logId}")
    public String getLogDetail(Model model, @PathVariable Integer userId, @PathVariable Integer logId){
        LogDto logDto = logService.queryLogByLogid(logId);
        model.addAttribute("userId", userId);
        model.addAttribute("logDetail", logDto);
        return "logDetail";
    }

    @RequestMapping("/addLog/{userId}")
    public String addLog(Model model, @PathVariable Integer userId){
        model.addAttribute("userId", userId);
        return "addLog";
    }

    @RequestMapping("/publishLog/{userId}")
    public ModelAndView publishLog(ModelAndView modelAndView, HttpServletRequest request, @PathVariable Integer userId){
        int logType = 0;
        try {
            logType = Integer.parseInt(request.getParameter("inlineRadioOptions"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Log log = new Log();
        log.setTeamId(request.getParameter("inputTeam"));
        log.setMemberId(userId);
        log.setLogType(logType);
        log.setLogTitle(request.getParameter("inputLogTitle"));
        log.setLogContent(request.getParameter("inputLogContent"));
        Date date = new Date();
        log.setSubmitTime(date);
        logService.addLog(log);

        RedirectView redirectView = new RedirectView("/WorkTogether/log/mylog/{userId}");
        modelAndView.setView(redirectView);
        modelAndView.addObject("userId", userId);
        return modelAndView;

//        List<LogDto> logs = logService.queryAllLogByUser(userId);
//        model.addAttribute("logs", logs);
////        return "mylog";
//        return "redirect:mylog";
    }


    @RequestMapping("/addlog/{log}")
    public String addLog(@PathVariable Log log){
        logService.addLog(log);
        return "redirect:mylog";
    }
}
