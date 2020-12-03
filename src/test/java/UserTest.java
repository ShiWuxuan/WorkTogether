import com.wuzi.WorkTogether.domain.User;
import com.wuzi.WorkTogether.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserTest {
    @Test
    public void testAddUser(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userServiceImpl");
        User user = new User();
        user.setUserName("1234");
        user.setUserPwd("123321");
        user.setUserTel("18801896987");
        userService.registerUser(user);
    }

    @Test
    public void testLogin(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userServiceImpl");
        userService.loginUser("321","123");
        userService.loginUser("18801896987","123");
        userService.loginUser("18801896987","123321");
    }

    @Test
    public void testChangeName(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userServiceImpl");
        System.out.println(userService.changeUserName("123","13888888888"));
        System.out.println(userService.changeUserName("ken","13888888888"));
    }

    @Test
    public void testFind(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userServiceImpl");
    }

    @Test
    public void testTel(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userServiceImpl");
        System.out.println(userService.testUserTel("1234"));
        System.out.println(userService.testUserTel("13688888888"));
    }
}