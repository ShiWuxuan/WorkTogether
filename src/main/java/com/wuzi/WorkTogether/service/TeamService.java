package com.wuzi.WorkTogether.service;

import com.wuzi.WorkTogether.domain.Team;
import com.wuzi.WorkTogether.domain.dto.TeamDto;
import com.wuzi.WorkTogether.domain.dto.UserDto;

import java.util.List;
/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/12/3 14:36
 * @lastEditor
 */
public interface TeamService {
    public List<TeamDto> findAllTeam();
    public List<TeamDto> findTeamByKeyword(String keyword);
    public List<TeamDto> findUserTeamByKeyword(String keyword,String userTel);
    public List<TeamDto> findMyTeam(String userTel);
    public List<TeamDto> findMyLeadingTeam(String userTel);
    public Team findTeamById(Integer teamId);
    public Integer joinTeam(Integer teamId, String userTel);
    public Integer quitTeam(Integer teamId,String userTel);
    public List<UserDto> showTeamMember(Integer teamId);
    public Integer createTeam(Team team);
    public void changeTeamLeader(String leaderTel,Integer teamId);
    public boolean updateTeamStatus(Integer flag,Integer teamId,String teamIntroduction,Integer memberNumLimit);
}
