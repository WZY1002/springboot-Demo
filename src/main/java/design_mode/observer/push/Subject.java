package design_mode.observer.push;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题角色
 * @author wzy
 * @date 2019/1/23
 **/
public abstract class Subject {

    //保存注册的观察者集合
    List<Observer> observerList=new ArrayList<>();

    public void attach(Observer observer){
        observerList.add(observer);
        System.out.println("注册了一个观察者");
    }

    public void remove(Observer observer){
        observerList.remove(observer);
        System.out.println("移除了一个观察者");
    }

    /**
     * 推送更新所有观察者
     * @author wzy
     * @date 2019/1/23
     **/
    public void pushMsg(int status){
        observerList.parallelStream().forEach(o->o.update(status));
        System.out.println("已通知所有观察者");
    }

}
