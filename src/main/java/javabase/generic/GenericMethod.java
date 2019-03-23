package javabase.generic;

public class GenericMethod {

    /***
     * 泛型类,类型变量放在修饰符public后面,返回类型前面
     * @param a 任意类型参数
     * @return T 任意类型返回结果
     * @author  wzy
     * @since  2019/3/21 9:50
     **/
    public <T> T getMiddle(T... a){
        return a[a.length/2];
    }

}
