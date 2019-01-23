package design_mode.observer.pull;

import design_mode.observer.pull.ConcreteSubject;
import design_mode.observer.pull.Observer;
import design_mode.observer.pull.Observer2;


/**
 * 拉模型-观察者模式
 *  主题对象在通知观察者的时候，只传递少量信息。如果观察者需要更具体的信息，由观察者主动到主题对象中获取，相当于是观察者从主题对象中拉数据。
 *  将主题对象自身传给消费者，消费者自己去获取数据
 * @author wzy
 * @date 2019/1/23
 **/
public class Client {
    public static void main(String[] args) {
        //创建主题对象
        ConcreteSubject subject=new ConcreteSubject();
        //创建观察者对象
        Observer observer1=new Observer2();
        Observer observer2=new Observer2();
        //注册观察者对象
        subject.attach(observer1);
        subject.attach(observer2);
        //主题对象修改属性
        subject.update();
        observer1.ptint();
        observer2.ptint();
    }
}
