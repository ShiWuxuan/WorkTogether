package com.wuzi.WorkTogether.dao;

import com.wuzi.WorkTogether.domain.Team;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TeamDao {

    @Select("select * from team where teamId = #{teamId}")
    public Team findTeamByID(Integer teamId);

    @Select("select * from team")
    public List<Team> findAllTeam();

    @Update("update team set teamNum = teamNum + 1")
    public void updateTeamMemNum();

}
