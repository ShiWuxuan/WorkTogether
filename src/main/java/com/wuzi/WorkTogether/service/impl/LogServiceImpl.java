package com.wuzi.WorkTogether.service.impl;

import com.wuzi.WorkTogether.dao.LogDao;
import com.wuzi.WorkTogether.domain.Log;
import com.wuzi.WorkTogether.domain.dto.LogDto;
import com.wuzi.WorkTogether.service.LogService;
import com.wuzi.WorkTogether.utils.LogDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王子皓
 * @title
 * @Package com.wuzi.WorkTogether.service.impl
 * @date 2020/11/29 17:21
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public List<LogDto> queryAllLogByUser(Integer userId) {
        List<Log> oriLog = logDao.queryAllLogByUser(userId);
        List<LogDto> ansLogs = new ArrayList<>();
        ansLogs = LogDtoConverter.convert(oriLog);
        return ansLogs;
    }

    @Override
    public List<LogDto> queryAllLogByTeam(Integer teamId) {
        List<Log> oriLog = logDao.queryAllLogByTeam(teamId);
        List<LogDto> ansLogs;
        ansLogs = LogDtoConverter.convert(oriLog);
        return ansLogs;
    }

    @Override
    public List<LogDto> queryUserTypeLog(Integer userId, Integer logType) {
        List<Log> oriLog = logDao.queryUserTypeLog(userId, logType);
        List<LogDto> ansLogs;
        ansLogs = LogDtoConverter.convert(oriLog);
        return ansLogs;
    }


    @Override
    public void addLog(Log log) {
        logDao.addLog(log);
    }

    @Override
    public List<LogDto> queryLogByKeyword(Integer userId, String keyword) {
        List<Log> oriLog = logDao.queryLogByKeyword(userId, keyword);
        List<LogDto> ansLogs;
        ansLogs = LogDtoConverter.convert(oriLog);
        return ansLogs;
    }
}
