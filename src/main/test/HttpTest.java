import Http.HttpClintPost;
import Http.HttpPost;
import Http.Httpspost;
import impldemo.MainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class HttpTest {

    @Test
    public void doPost(){
        HttpPost httpPost=new HttpPost();
        httpPost.run();
    }

    @Test
    public void doClintPost(){
        HttpClintPost httpClintPost=new HttpClintPost();
        httpClintPost.run();
    }

    @Test
    public void doHttps(){
        Httpspost httpspost=new Httpspost();
        httpspost.httpsRequest("https://kyfw.12306.cn/","GET",null);
    }
}
