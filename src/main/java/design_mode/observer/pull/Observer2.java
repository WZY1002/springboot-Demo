package design_mode.observer.pull;

import design_mode.observer.pull.Observer;

/**
 * 观察者一号
 * @author wzy
 * @date 2019/1/23
 **/
public class Observer2 implements Observer{

    private Integer status;

    @Override
    public void update(Subject subject) {
        this.status=((ConcreteSubject) subject).status;
    }

    @Override
    public void ptint(){
        System.out.println("Observer2-------------------"+this.status);
    }
}
