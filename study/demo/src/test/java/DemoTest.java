import cn.ives.Application;
import cn.ives.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class DemoTest {
   @Autowired
   private DemoService demoService;
   
   @Test
   public void testQuery() {
      List<TpUserInfo> data = demoService.getData();
      System.out.println(data.toString());
   }
}
