import com.myproject.myspring.servlet.MyDispatcherServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value ={"redis.redisconfig", "impldemo","mybatisconfig","rabbitmq","interceptor", "design_mode.strategy_pay"})//默认扫描@SpringBootApplication所在类的同级目录以及它的子目录
@MapperScan("mybatisconfig.dao")
public class MainApplication {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        //替代原xml里的servlet配置，所有进入的路径都经过MyDispatcherServlet拦截
        return new ServletRegistrationBean(new MyDispatcherServlet(), "/springdemo");
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
