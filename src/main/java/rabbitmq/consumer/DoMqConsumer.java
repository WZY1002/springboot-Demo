//package rabbitmq.consumer;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RabbitListener(queues = rabbitmq.RabbitMQSpringConfig.queue)
//public class DoMqConsumer {
//    @RabbitHandler
//    public void process(String message){
//        //监听处理
//        System.out.println("DirectExchange receiver  : " + message);
//    }
//}
