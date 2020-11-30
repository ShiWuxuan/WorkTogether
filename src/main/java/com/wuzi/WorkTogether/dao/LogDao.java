package com.wuzi.WorkTogether.dao;

import com.wuzi.WorkTogether.domain.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王子皓
 * @title
 * @Package com.wuzi.WorkTogether.dao
 * @date 2020/11/24 21:35
 */
@Repository
public interface LogDao {

    /**
     * 获取用户所有log
     * @param userId
     * @return
     */
    @Select("select * from log where memberId=#{userId}")
    List<Log> queryAllLogByUser(Integer userId);

    /**
     * 获取团队所有log
     * @param teamId
     * @return
     */
    @Select("select * from log where teamId=#{teamId}")
    List<Log> queryAllLogByTeam(Integer teamId);

    /**
     * 获取用户某种类型的log
     * @param userId
     * @param logType
     * @return
     */
    @Select("select * from log where memberId=#{userId} and logType=#{logType}")
    List<Log> queryUserTypeLog(@Param("userId") Integer userId, @Param("logType") Integer logType);

    /**
     * 创建新的log
     * @param log
     */
    @Insert("insert into log (teamId,logName,memberId,logType,submitTime)" +
            "values(#{teamId},#{logName},#{memberId},#{logType},#{submitTime})")
    void addLog(Log log);

    /**
     * 根据关键字进行log查找
     * @param userId
     * @param keyword
     * @return
     */
    @Select("select * from log where memberId=#{userId} and logTitle like '%${keyword}%'")
    List<Log> queryLogByKeyword(@Param("userId") Integer userId, @Param("keyword") String keyword);

}
