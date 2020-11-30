package com.wuzi.WorkTogether.utils;

import com.wuzi.WorkTogether.domain.Log;
import com.wuzi.WorkTogether.domain.dto.LogDto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王子皓
 * @title
 * @Package com.wuzi.WorkTogether.utils
 * @date 2020/11/30 16:30
 */
public class LogDtoConverter {

    public static List<LogDto> convert(List<Log> logs){
        List<LogDto> ansLogs = new ArrayList<>();
        for (Log l : logs){
            LogDto logDto = new LogDto();
            logDto.setTeamId(l.getTeamId());
            logDto.setLogContent(l.getLogContent());
            logDto.setLogTitle(l.getLogTitle());
            logDto.setLogType(l.getLogType());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            logDto.setSubmitTime(sdf.format(l.getSubmitTime()));
            ansLogs.add(logDto);
        }
        return ansLogs;
    }
}
