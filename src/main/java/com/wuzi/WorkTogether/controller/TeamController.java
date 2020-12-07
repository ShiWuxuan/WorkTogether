package com.wuzi.WorkTogether.controller;

import com.wuzi.WorkTogether.domain.Team;
import com.wuzi.WorkTogether.domain.dto.TeamDto;
import com.wuzi.WorkTogether.domain.dto.UserDto;
import com.wuzi.WorkTogether.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/12/5 14:36
 * @lastEditor
 */
@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    private String curUserTel;

    /**
     * 根据不同条件显示团队（所有团队，用户的团队，用户担任队长的团队）
     * @param model
     * @return
     */
    @RequestMapping("/showAllTeam")
    public String showAllTeam(Model model)
    {
        List<TeamDto> teams = teamService.findAllTeam();
        model.addAttribute("teams",teams);
        return "allTeam";
    }

    @RequestMapping("/myTeam/{userTel}")
    public String showMyTeam(Model model,@PathVariable String userTel)
    {
        this.curUserTel = userTel;
        List<TeamDto> teams = teamService.findMyTeam(userTel);
        model.addAttribute("teams",teams);
        return "myTeam";
    }

    @RequestMapping("/showLeadingTeam")
    public String showLeadingTeam(Model model)
    {
        List<TeamDto> teams = teamService.findMyLeadingTeam(curUserTel);
        model.addAttribute("teams",teams);
        return "myLeadingTeam";
    }

    /**
     * 返回用户的团队主页
     * @param modelAndView
     * @return
     */
    @RequestMapping("/backToMyTeam")
    public ModelAndView backToMyTeam(ModelAndView modelAndView)
    {
        RedirectView redirectView = new RedirectView("/WorkTogether/team/myTeam/{userTel}");
        modelAndView.setView(redirectView);
        modelAndView.addObject("userTel", curUserTel);
        return modelAndView;
    }

    /**
     * 根据关键字查询团队
     * @param model
     * @param keyword
     * @return
     */
    @RequestMapping("/findTeamByKeyword")
    public String findTeamByKeyword(Model model, @RequestParam String keyword)
    {
        List<TeamDto> teams = teamService.findTeamByKeyword(keyword);
        model.addAttribute("teams",teams);
        return "allTeam";
    }

    @RequestMapping("/findMyTeamByKeyword")
    public String findMyTeamByKeyword(Model model, @RequestParam String keyword)
    {
        List<TeamDto> teams = teamService.findUserTeamByKeyword(keyword,curUserTel);
        model.addAttribute("teams",teams);
        return "myTeam";
    }


    @RequestMapping("/showLeadingMember/{teamId}")
    public String showLeadingMember(Model model, @PathVariable Integer teamId)
    {
        Team team = teamService.findTeamById(teamId);
        List<UserDto> users = teamService.showTeamMember(teamId);
        model.addAttribute(users);
        model.addAttribute(team);
        return "manageMember";
    }

    /**
     * 创建团队，使用out.print()来实现在对应情况下呈现不同的提示框
     * @param modelAndView
     * @param teamName
     * @param teamNumberLimit
     * @param response
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("/createMyTeam/{teamName}/{teamNumberLimit}")
    public ModelAndView createMyTeam(ModelAndView modelAndView,@PathVariable String teamName,@PathVariable Integer teamNumberLimit, HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        Team team = new Team();
        PrintWriter out = response.getWriter();
        team.setTeamName(teamName);
        team.setMemberNumLimit(teamNumberLimit);
        team.setLeaderTel(curUserTel);
        team.setMemberTel(curUserTel);
        switch (teamService.createTeam(team))
        {
            case 0:
                out.print("<script language=\"javascript\">alert(\"成功创建您的团队！\");window.location.href='/WorkTogether/team/myTeam/"+curUserTel+"'</script>");
                break;
            case 1:
                out.print("<script language=\"javascript\">alert(\"该团队名已被使用，请重新决定您的团队名称\");window.location.href='/WorkTogether/team/myTeam/"+curUserTel+"'</script>");
                break;
            case 2:
                out.print("<script language=\"javascript\">alert(\"团队成员最大数量超过上限，请重新输入\");window.location.href='/WorkTogether/team/myTeam/"+curUserTel+"'</script>");
                break;
            default:
                out.print("<script language=\"javascript\">alert(\"服务器繁忙，请重试\");window.location.href='/WorkTogether/team/myTeam/"+curUserTel+"'</script>");
                break;

        }
        wait(10);
        return backToMyTeam(modelAndView);
    }

    /**
     * 展示团队详情（用户名，用户身份，团队详情等）
     * @param model
     * @param teamId
     * @return
     */
    @RequestMapping("/teamDetail/{teamId}")
    public String showTeamDetail(Model model,@PathVariable Integer teamId)
    {
        List<UserDto> users = teamService.showTeamMember(teamId);
        Team team = teamService.findTeamById(teamId);
        model.addAttribute("team",team);
        model.addAttribute("users",users);
        return "teamDetail";
    }

    /**
     * 加入团队
     * @param teamId
     * @param response
     * @param model
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("/joinTeam/{teamId}")
    public String joinTeam(@PathVariable Integer teamId,HttpServletResponse response,Model model) throws IOException, InterruptedException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        switch (teamService.joinTeam(teamId,curUserTel))
        {
            case 0:
                out.print("<script language=\"javascript\">alert(\"成功加入该团队！\");window.location.href='/WorkTogether/team/showAllTeam'</script>");
                break;
            case 1:
                out.print("<script language=\"javascript\">alert(\"该团队成员已满！加入失败\");window.location.href='/WorkTogether/team/showAllTeam'</script>");
                break;
            case 2:
                out.print("<script language=\"javascript\">alert(\"您已是该团队的成员！请勿重复添加\");window.location.href='/WorkTogether/team/showAllTeam'</script>");
                break;
            default:
                out.print("<script language=\"javascript\">alert(\"服务器繁忙，请重试\");window.location.href='/WorkTogether/team/showAllTeam'</script>");
                break;
        }
        wait(10);
        return showAllTeam(model);
    }

    /**
     * 退出团队
     * @param modelAndView
     * @param teamId
     * @param response
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("/quitTeam/{teamId}")
    public ModelAndView quitTeam(ModelAndView modelAndView, @PathVariable Integer teamId,HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        switch (teamService.quitTeam(teamId,curUserTel))
        {
            case 0:
                out.print("<script language=\"javascript\">alert(\"成功退出该团队！\");window.location.href='/WorkTogether/team/myTeam/"+curUserTel+"'</script>");
                break;
            default:
                out.print("<script language=\"javascript\">alert(\"服务器繁忙，请重试\");window.location.href='/WorkTogether/team/myTeam/"+curUserTel+"'</script>");
                break;
        }
        wait(10);
        return backToMyTeam(modelAndView);
    }


    /**
     * 管理团队成员（转让队长、删除成员）
     * @param model
     * @param teamId
     * @param flag
     * @param memberTel
     * @param response
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("updateLeadingTeamMember/{teamId}/{flag}/{memberTel}")
    public String updateLeadingTeamMember(Model model, @PathVariable Integer teamId ,@PathVariable Integer flag, @PathVariable String memberTel,HttpServletResponse response) throws IOException, InterruptedException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1)
        {
            teamService.changeTeamLeader(memberTel,teamId);
            out.print("<script language=\"javascript\">alert(\"成功转让队长！\");window.location.href='/WorkTogether/team/showLeadingTeam'</script>");
            wait(10);
            return showLeadingTeam(model);
        }
        else if(flag == 2)
        {
            teamService.quitTeam(teamId,memberTel);
            out.print("<script language=\"javascript\">alert(\"成功删除该成员！\");window.location.href='/WorkTogether/team/showLeadingMember/"+teamId+"'</script>");
            wait(10);
        }
        return showLeadingMember(model,teamId);
    }

    /**
     * 管理团队状态（修改成员上限，修改团队介绍)
     * @param model
     * @param teamNumberLimit
     * @param teamIntroduction
     * @param teamID
     * @param flag
     * @param response
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("updateMyLeadingTeam/{flag}/{teamID}/{teamNumberLimit}/{teamIntroduction}")
    public String updateMyLeadingTeam(Model model,@PathVariable Integer teamNumberLimit, @PathVariable String teamIntroduction, @PathVariable Integer teamID, @PathVariable Integer flag,HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(teamService.updateTeamStatus(flag,teamID,teamIntroduction,teamNumberLimit))
        {
            out.print("<script language=\"javascript\">alert(\"修改成功！\");window.location.href='/WorkTogether/team/showLeadingTeam'</script>");
        }
        else
        {
            out.print("<script language=\"javascript\">alert(\"修改失败！成员上限超过最大值或小于当前成员数量！\");window.location.href='/WorkTogether/team/showLeadingTeam'</script>");
        }
        wait(10);
        return showLeadingTeam(model);
    }
}
