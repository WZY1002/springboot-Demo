package design_mode.singleton;

import commom.DemoBO;

/**
 * 双重检验加锁单例
 * @author wzy
 * @date 2019/1/17
 **/
public class SingletonLock {

    private static volatile SingletonLock singletonLock=null;

    public static SingletonLock getSingletonLock() {
        //先检验实例是否存在
        if(singletonLock==null){
            //不存在进入下面的方法
            synchronized (SingletonLock.class){
                //再次检验实例是否存在
                if(singletonLock==null){
                    singletonLock=new SingletonLock();
                }
            }
        }
        return singletonLock;
    }
}
