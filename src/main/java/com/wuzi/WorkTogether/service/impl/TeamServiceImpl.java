package com.wuzi.WorkTogether.service.impl;

import com.wuzi.WorkTogether.dao.TeamDao;
import com.wuzi.WorkTogether.dao.UserDao;
import com.wuzi.WorkTogether.domain.Team;
import com.wuzi.WorkTogether.domain.User;
import com.wuzi.WorkTogether.domain.dto.TeamDto;
import com.wuzi.WorkTogether.domain.dto.UserDto;
import com.wuzi.WorkTogether.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/12/3 14:40
 * @lastEditor
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    @Autowired
    private UserDao userDao;

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 根据不同条件查询团队（所有团队，teamId对应的团队，用户的团队，用户担任队长的团队）
     * @return
     */
    @Override
    public List<TeamDto> findAllTeam() {
        return makeTeamDtos(teamDao.findAllTeam());
    }

    @Override
    public Team findTeamById(Integer teamId) {
        return teamDao.findTeamByID(teamId);
    }

    @Override
    public List<TeamDto> findMyTeam(String userTel) {
        return makeTeamDtos(teamDao.findTeamByUserTel(userTel));
    }

    @Override
    public List<TeamDto> findMyLeadingTeam(String userTel) {
        return makeTeamDtos(teamDao.findLeadingTeamByUserTel(userTel));
    }

    /**
     * 根据关键字查询团队
     * @param keyword
     * @return
     */
    @Override
    public List<TeamDto> findTeamByKeyword(String keyword) {
        return makeTeamDtos(teamDao.findTeamByKeyword(keyword));
    }

    @Override
    public List<TeamDto> findUserTeamByKeyword(String keyword, String userTel) {
        return makeTeamDtos(teamDao.findUserTeamByKeyword(keyword,userTel));
    }


    /**
     * 把sql语句返回的team类型数据转换为teamDto类型
     * @param teams
     * @return
     */
    public List<TeamDto> makeTeamDtos(List<Team> teams)
    {
        List<TeamDto> teamDtos = new ArrayList<TeamDto>();
        for(Team team : teams)
        {
            TeamDto teamDto = new TeamDto();
            teamDto.setLeaderName(userDao.findUserByTel(team.getLeaderTel()).getUserName());
            teamDto.setMemberNum(team.getMemberNum());
            teamDto.setMemberNumLimit(team.getMemberNumLimit());
            teamDto.setTeamId(team.getTeamId());
            teamDto.setTeamIntroduction(team.getTeamIntroduction());
            teamDto.setTeamName(team.getTeamName());
            teamDtos.add(teamDto);
        }
        return teamDtos;
    }


    /**
     * 创建队伍，重名、成员上限数量大于50或者队伍名称为空都会创建失败
     * @param team
     * @return
     */
    @Override
    public Integer createTeam(Team team,String userTel) {
        if(team.getTeamName()=="")
        {
            return 2;
        }
        if(team.getMemberNumLimit() == 0)
        {
            team.setMemberNumLimit(5);
        }
        if(team.getMemberNumLimit() > 50)
        {
            return 2;
        }
        List<TeamDto> teams = findAllTeam();
        for(TeamDto curTeam : teams)
        {
            if(team.getTeamName().equals(curTeam.getTeamName())) {
                return 1;
            }
        }
        team.setMemberNum(1);
        User user = userDao.findUserByTel(userTel);
        String curUserTeam = user.getTeamName();
        if("".equals(curUserTeam) || Objects.isNull(curUserTeam))
        {
            userDao.updateTeamByTel(userTel,team.getTeamName());
        }
        else
        {
            userDao.updateTeamByTel(userTel,curUserTeam+","+team.getTeamName());
        }
        teamDao.addTeam(team);
        return 0;
    }

    /**
     * 加入队伍，如果队伍人数将超过上限则失败，在对应user的team字段增加团队名称，team的memberTel字段增加用户手机号
     * @param teamId
     * @param userTel
     * @return
     */
    @Override
    public Integer joinTeam(Integer teamId, String  userTel) {
        Team team = teamDao.findTeamByID(teamId);
        if(team.getMemberNum() == team.getMemberNumLimit())
            return 1;
        String[] curMemberTels = team.getMemberTel().split(",");
        Integer i = 0;
        for(String memberTel : curMemberTels)
        {
            if(userTel.equals(memberTel))
            {
                i++;
                return 2;
            }
            i++;
        }
        User user = userDao.findUserByTel(userTel);
        String curUserTeam = user.getTeamName();
        if(curUserTeam.equals(""))
        {
            userDao.updateTeamByTel(userTel,team.getTeamName());
        }
        else
        {
            userDao.updateTeamByTel(userTel,curUserTeam+","+team.getTeamName());
        }
        String curMemberTel = team.getMemberTel();
        if(curMemberTel.equals(""))
        {
            teamDao.updateTeamMem(userTel,teamId);
        }
        else
        {
            teamDao.updateTeamMem(curMemberTel+","+userTel,teamId);
        }
        teamDao.updateTeamMemNum(team.getMemberNum()+1,teamId);
        return 0;
    }

    /**
     * 退出团队，如果退出成员为队长将会解散团队。
     * @param teamId
     * @param userTel
     * @return
     */
    @Override
    public Integer quitTeam(Integer teamId, String userTel) {
        Team team = teamDao.findTeamByID(teamId);
        User user = userDao.findUserByTel(userTel);
        String[] curUserTeamNames = user.getTeamName().split(",");
        String changedUserTeamName = "";
        Integer i = 0;
        for(String teamName : curUserTeamNames)
        {
            //找到匹配项则略过该字符串
            if(teamDao.findTeamByID(teamId).getTeamName().equals(teamName))
            {
                //若恰好该匹配项为原字符串中最后一项，则顺带删除前一项加上的逗号
                if(i == curUserTeamNames.length-1 && changedUserTeamName.length() != 0)
                    changedUserTeamName = changedUserTeamName.substring(0,changedUserTeamName.length()-1);
                i++;
                continue;
            }
            else if(i == curUserTeamNames.length-1)
            {
                changedUserTeamName += curUserTeamNames[i];
            }
            else
            {
                changedUserTeamName += curUserTeamNames[i] + ",";
            }
            i++;
        }
        userDao.updateTeamByTel(userTel,changedUserTeamName);
        if(userTel.equals(team.getLeaderTel()))
        {
            teamDao.deleteTeam(team.getTeamId());
            return 0;
        }
        String[] curMemberTels = team.getMemberTel().split(",");
        String changedMemberTel = "";
        Integer j = 0;
        for(String memberTel : curMemberTels)
        {
            //找到匹配项则略过该字符串
            if(userTel.equals(memberTel))
            {
                //若恰好该匹配项为原字符串中最后一项，则顺带删除前一项加上的逗号
                if(j == curMemberTels.length-1 && changedMemberTel.length() != 0)
                    changedMemberTel = changedMemberTel.substring(0,changedMemberTel.length()-1);
                j++;
                continue;
            }
            else if(j == curMemberTels.length-1)
            {
                changedMemberTel += curMemberTels[j];
            }
            else
            {
                changedMemberTel += curMemberTels[j] + ",";
            }
            j++;
        }
        teamDao.updateTeamMem(changedMemberTel,teamId);
        teamDao.updateTeamMemNum(team.getMemberNum()-1,teamId);
        return 0;
    }

    /**
     * 展示团队成员（通过查询团队成员的手机号来获得用户的名称并呈现）
     * @param teamId
     * @return
     */
    @Override
    public List<UserDto> showTeamMember(Integer teamId) {
        List<UserDto> users = new ArrayList<UserDto>();
        UserDto user;
        Team team = teamDao.findTeamByID(teamId);
        user = userDao.findUserDtoByTel(team.getLeaderTel());
        user.setUserType("Leader");
        users.add(user);
        for(String curTeamMemberTel : team.getMemberTel().split(","))
        {
            if(curTeamMemberTel.equals(team.getLeaderTel()))
            {
                continue;
            }
            user = userDao.findUserDtoByTel(curTeamMemberTel);
            user.setUserType("Member");
            users.add(user);
        }
        return users;
    }

    @Override
    public void changeTeamLeader(String leaderTel,Integer teamId) {
        teamDao.updateTeamLeader(leaderTel,teamId);
    }

    /**
     * 更改团队状态（仅可由队长操作）
     * @param flag
     * @param teamId
     * @param teamIntroduction
     * @param memberNumLimit
     * @return
     */
    @Override
    public boolean updateTeamStatus(Integer flag,Integer teamId,String teamIntroduction,Integer memberNumLimit) {
        Team team = teamDao.findTeamByID(teamId);
        if(memberNumLimit > 50)
        {
            return false;
        }
        if(flag == 1) {
            if(memberNumLimit < team.getMemberNum())
            {
                return false;
            }
            teamDao.updateTeamStatus(team.getTeamIntroduction(), memberNumLimit, teamId);
        }
        else if(flag == 2)
        {
            teamDao.updateTeamStatus(teamIntroduction,team.getMemberNumLimit(),teamId);
        }
        return true;
    }
}