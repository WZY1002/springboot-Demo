//package rabbitmq.producer;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//
///**
// * @title:
// * @description:
// * @author:wzy
// * @company:djwms
// * @create:2018/1/9
// **/
//@Component
//public class MqProducer {
//    @Resource
//    private AmqpTemplate amqpTemplate;
//
//    @Transactional("rabbitTxManage")
//    public void sendMsg(String exchange,String routingKey,Object message) throws InterruptedException {
//        System.err.println("GrouponPayMqProducer路由："+exchange);
//        System.err.println("GrouponPayMqProducer队列："+routingKey);
//        System.err.println("GrouponPayMqProducer信息："+message);
//        amqpTemplate.convertAndSend(exchange, routingKey, message);
//    }
//}
