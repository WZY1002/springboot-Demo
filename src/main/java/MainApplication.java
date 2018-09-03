package impldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(value ={"config", "impldemo","mybatisconfig","rabbitmq","interceptor", "strategy_pay"})//默认扫描@SpringBootApplication所在类的同级目录以及它的子目录
@MapperScan("mybatisconfig.dao")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
