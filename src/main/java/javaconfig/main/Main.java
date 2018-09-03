package javaconfig.main;

import javaconfig.config.config;
import javaconfig.model.UserPO;
import javaconfig.service.UserSercice;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 通过Java配置来实例化Spring容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(config.class);
        // 在Spring容器中获取Bean对象
        UserSercice userService = context.getBean(UserSercice.class);
        // 调用对象中的方法
        List<UserPO> list = userService.querListList();
        for (UserPO user : list) {
            System.out.println(user.getUsername() + ", " + user.getPassword() + ", " + user.getPassword());
        }
        // 销毁该容器
        context.destroy();
    }

}
