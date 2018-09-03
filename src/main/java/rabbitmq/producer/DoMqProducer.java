//package rabbitmq.producer;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import rabbitmq.RabbitMQSpringConfig;
//import java.util.Date;
//
//@Component
//    public class DoMqProducer {
//
//        @Autowired
//        private AmqpTemplate rabbitTemplate;
//
//        public void send() {
//            String context = "hello " + new Date();
//            System.out.println("Sender : " + context);
//            this.rabbitTemplate.convertAndSend(RabbitMQSpringConfig.exchange,RabbitMQSpringConfig.key, context);
//        }
//}
