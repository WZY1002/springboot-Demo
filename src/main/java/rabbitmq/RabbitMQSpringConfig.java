//package rabbitmq;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQSpringConfig {
//
//    //定义队列常量
//    public final static String queue="message.server.balance.test";
//
//    //定义交换器
//    public final static String exchange="exchangeBalance";
//
//    //消息匹配键
//    public final static String key="queueTestBalanceServerKey";
//    /**
//     * 声明队列
//     * @author  wzy
//     * @create  2018/1/2 15:10
//     **/
//    @Bean
//    public Queue TestQueue() {
//        // 默认就是自动声明的
//        return new Queue(queue, true);
//    }
//
//    /**
//     * 声明direct exchange交换器
//     * @author  wzy
//     * @create  2018/1/2 16:11
//     **/
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange(exchange);
//    }
//
//
//
//    /**
//     * 绑定队列
//     * @author  wzy
//     * @create  2018/1/2 15:23
//     **/
//    @Bean
//    Binding bindingExchangeMessage(Queue queueMessage, DirectExchange exchange) {
//        //bind队列名queue到消息路由exchange，包含消息匹配键key
//        return BindingBuilder.bind(queueMessage).to(exchange).with(key);
//    }
//
//
//}
