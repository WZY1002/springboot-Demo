package design_mode.observer.push;

/**
 * 观察者一号
 * @author wzy
 * @date 2019/1/23
 **/
public class Observer1 implements Observer{

    private Integer status;

    @Override
    public void update(int status){
        this.status=status;
    }

    @Override
    public void ptint(){
        System.out.println("Observer1-------------------"+this.status);
    }
}
