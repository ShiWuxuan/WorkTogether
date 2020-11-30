package com.wuzi.WorkTogether.service;

import com.wuzi.WorkTogether.domain.Log;
import com.wuzi.WorkTogether.domain.dto.LogDto;

import java.util.List;

/**
 * @author 王子皓
 * @title
 * @Package com.wuzi.WorkTogether.service
 * @date 2020/11/29 16:22
 */
public interface LogService {

    List<LogDto> queryAllLogByUser(Integer userId);

    List<LogDto> queryAllLogByTeam(Integer teamId);

    List<LogDto> queryUserTypeLog(Integer userId, Integer logType);

    void addLog(Log log);

    List<LogDto> queryLogByKeyword(Integer userId, String keyword);
}
