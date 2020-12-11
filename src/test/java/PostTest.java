import com.wuzi.WorkTogether.dao.PostDao;
import com.wuzi.WorkTogether.domain.Record;
import com.wuzi.WorkTogether.service.PostService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
}
