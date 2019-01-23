package design_mode.observer.push;

/**
 * 推模型-观察者模式
 * 主题对象向观察者推送主题的详细信息，不管观察者是否需要，推送的信息通常是主题对象的全部或部分数据。
 * @author wzy
 * @date 2019/1/23
 **/
public class Client {
    public static void main(String[] args) {
        //创建主题对象
        ConcreteSubject subject=new ConcreteSubject();
        //创建观察者对象
        Observer observer1=new Observer1();
        Observer observer2=new Observer1();
        //注册观察者对象
        subject.attach(observer1);
        subject.attach(observer2);
        //主题对象修改属性
        subject.update(1);
        observer1.ptint();
        observer2.ptint();
    }
}
