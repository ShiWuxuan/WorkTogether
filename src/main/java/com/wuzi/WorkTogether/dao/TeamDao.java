package com.wuzi.WorkTogether.dao;

import com.wuzi.WorkTogether.domain.Team;
import com.wuzi.WorkTogether.domain.dto.TeamDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamDao {

    @Select("select * from team where teamId = #{teamId}")
    public Team findTeamByID(Integer teamId);

    @Select("select * from team where teamName like '%${keyword}%'")
    public List<Team> findTeamByKeyword(String keyword);

    @Select("select * from team where teamName like '%${keyword}%' and memberTel like '%${userTel}%'")
    public List<Team> findUserTeamByKeyword(@Param("keyword") String keyword,@Param("userTel") String userTel);

    @Select("select * from team where memberTel like '%${userTel}%'")
    public List<Team> findTeamByUserTel(String userTel);

    @Select("select * from team where leaderTel like '%${userTel}%'")
    public List<Team> findLeadingTeamByUserTel(String userTel);

    @Select("select * from team")
    public List<Team> findAllTeam();

    @Update("update team set memberNum = #{nowNum} where teamId = #{teamId}")
    public void updateTeamMemNum(@Param("nowNum") Integer nowNum, @Param("teamId") Integer teamId);

    @Update("update team set memberTel = #{newMemberTel} where teamId = #{teamId}")
    public void updateTeamMem(@Param("newMemberTel") String newMemberTel, @Param("teamId") Integer teamId);

    @Update("update team set teamIntroduction = #{teamIntroduction}, memberNumLimit = #{memberNumLimit} where teamId = #{teamId}")
    public void updateTeamStatus(@Param("teamIntroduction")String teamIntroduction,@Param("memberNumLimit") Integer memberNumLimit,@Param("teamId") Integer teamId);

    @Update("update team set leaderTel = #{leaderTel} where teamId = #{teamId}")
    public void updateTeamLeader(@Param("leaderTel") String leaderTel,@Param("teamId") Integer teamId);

    @Update("insert into team (teamId,teamName,leaderTel,memberTel,memberNum,memberNumLimit,teamIntroduction)values(#{teamId},#{teamName},#{leaderTel},#{memberTel},#{memberNum},#{memberNumLimit},#{teamIntroduction})")
    public void addTeam(Team team);

    @Delete("delete from team where teamId = #{teamId}")
    public void deleteTeam(Integer teamId);
}
