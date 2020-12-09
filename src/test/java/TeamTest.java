
import com.wuzi.WorkTogether.domain.Team;
import com.wuzi.WorkTogether.service.TeamService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TeamTest {
    @Test
    public void testAddTeam(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = (TeamService) context.getBean("teamServiceImpl");
        Team team = new Team();
        team.setTeamName("1");
        team.setLeaderTel("1");
        System.out.println(teamService.createTeam(team));
        team.setTeamName("1");
        team.setLeaderTel("1");
        System.out.println(teamService.createTeam(team));
        team.setTeamName("2");
        team.setLeaderTel("1");
        System.out.println(teamService.createTeam(team));
        team.setTeamName("3");
        team.setLeaderTel("1");
        team.setMemberNumLimit(1);
        team.setMemberNum(1);
        System.out.println(teamService.createTeam(team));
    }

    @Test
    public void testFindMyTeam()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = (TeamService) context.getBean("teamServiceImpl");
        System.out.println(teamService.findMyTeam("18801896988"));
    }

    @Test
    public void testJoinTeam(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = (TeamService) context.getBean("teamServiceImpl");
        //System.out.println(teamService.joinTeam(6,"18801896987"));
        System.out.println(teamService.joinTeam(4,"18801896987"));
        System.out.println(teamService.joinTeam(4,"18801896988"));
        System.out.println(teamService.joinTeam(5,"18801896988"));
    }

    @Test
    public void testQuitTeam(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = (TeamService) context.getBean("teamServiceImpl");
        System.out.println(teamService.quitTeam(4,"18801896987"));
        System.out.println(teamService.quitTeam(4,"18801896988"));
        System.out.println(teamService.quitTeam(5,"18801896988"));
    }

    @Test
    public void testFindByTelKeyword() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = (TeamService) context.getBean("teamServiceImpl");
        System.out.println(teamService.findUserTeamByKeyword("x","18801896988"));
    }

    @Test
    public void testFindByTel(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = (TeamService) context.getBean("teamServiceImpl");
        System.out.println(teamService.findMyTeam("18801896988"));
    }


    @Test
    public void testFindLeadingByTel(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = (TeamService) context.getBean("teamServiceImpl");
        System.out.println(teamService.findMyLeadingTeam("18801896988"));
    }

    @Test
    public void testFindAll(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = (TeamService) context.getBean("teamServiceImpl");
        System.out.println(teamService.findAllTeam());
    }

    @Test
    public void testFindByKeyword(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = (TeamService) context.getBean("teamServiceImpl");
        System.out.println(teamService.findTeamByKeyword("x"));
        System.out.println(teamService.findTeamByKeyword("cx"));
    }

    @Test
    public void testFindTeamMember(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeamService teamService = (TeamService) context.getBean("teamServiceImpl");
        System.out.println(teamService.showTeamMember(4));
        System.out.println(teamService.showTeamMember(5));
    }
}
