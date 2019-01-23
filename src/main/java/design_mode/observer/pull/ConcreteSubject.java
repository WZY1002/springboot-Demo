package design_mode.observer.pull;

public class ConcreteSubject extends Subject{

    public int status;

    /**
     * 更新主题角色对象
     * @author wzy
     * @date 2019/1/23
     **/
    public void update(){
        status=2;
        this.pushMsg();
    }

}
