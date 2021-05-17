import com.wuzi.WorkTogether.dao.PostDao;
import com.wuzi.WorkTogether.domain.Record;
import com.wuzi.WorkTogether.service.PostService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/12/7 18:32
 * @lastEditor
 */
public class PostTest {
    @Test
    public void daoTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PostDao postDao = (PostDao) context.getBean("postDao");
        System.out.println(postDao.queryPostCount());
        System.out.println(postDao.queryPostForPage(0,5));
        System.out.println(postDao.queryAllRecordForPost(1));
        System.out.println(postDao.queryRecordNumber(1));
    }

    @Test
    public void serviceTest()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PostService postService = (PostService)context.getBean("postServiceImpl");
        postService.likePost(3);
    }

    @Test
    public void normalTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println("asdasdasd");
        System.out.println("asdasdqwrwerwrasd");
        System.out.println("asdasdasdaweqeassafdasffdsfwerwerwerwerwerwerwerwsdferettwrtwersdfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffd".length());

        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1);
        Duration duration = Duration.between(localDateTime,localDateTime1);
        System.out.println(duration.toMillis());
    }

    @Test
    public void test2(){
        Long a = System.currentTimeMillis();
        Date date1 = new Date();
        System.out.println("asdasdasd");
        System.out.println("asdasdasd");
        System.out.println("asdasdasd");
        System.out.println(date1.getTime());
        Date date2 = new Date();
        System.out.println(date2.getTime());
        System.out.println(date2.getTime()-date1.getTime());
        Long b = System.currentTimeMillis();
        System.out.println(b-a);
    }
}
