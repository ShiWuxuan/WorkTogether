import com.wuzi.WorkTogether.dao.TaskDao;
import com.wuzi.WorkTogether.domain.Task;
import com.wuzi.WorkTogether.service.TaskService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/11/14 22:42
 * @lastEditor
 */
public class TaskTest {

    @Test
    public void addTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       TaskDao taskDao = (TaskDao) context.getBean("taskDao");
        Task task = new Task();
        task.setTaskName("期末论文");
        task.setMemberId(2);
        task.setTeamId("#002");
//        task.setEndTime(new Date());
        taskDao.addTask(task);
    }

    @Test
    public void queryTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TaskService taskService =(TaskService) context.getBean("taskServiceImpl");
        System.out.println(taskService.queryTaskByKeyword(1,"大"));
    }
}
