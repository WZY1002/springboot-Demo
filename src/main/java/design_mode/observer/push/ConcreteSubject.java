package design_mode.observer.push;

/**
 * 具体主题角色类
 * @author wzy
 * @date 2019/1/23
 **/
public class ConcreteSubject extends Subject{

    /**
     * 更新主题角色对象
     * @author wzy
     * @date 2019/1/23
     **/
    public void update(int status){
        this.pushMsg(status);
    }

}
