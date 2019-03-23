package design_mode.proxy_jdk.invokeNew;

import design_mode.proxy_jdk.invokeGet.PersonA;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class invokeNew {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        int[] a={1,2,3};
//        System.out.println(Arrays.toString(a));
//        int[] b=(int [])getCopyOf(a,10);
//        System.out.println(Arrays.toString(b));
        myInstant();
    }

    /***
     * 扩容一个数组
     * @param soure 原数组
     * @param newLength 扩容长度
     * @return java.lang.Object
     * @author  wzy
     * @since  2019/3/17 9:50
     **/
    public static Object getCopyOf(Object soure, int newLength){
        Class cl=soure.getClass();
        if(!cl.isArray()) {
            return null;
        }
        int soureLength= Array.getLength(soure);
        Class componentType = cl.getComponentType();
        Object newArray=Array.newInstance(componentType,newLength);
        System.arraycopy(soure,0,newArray,0,Math.min(soureLength,newLength));
        return newArray;
    }

    public static void myInstant() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl= PersonA.class;
        Constructor constructor=cl.getConstructor();
        PersonA o =(PersonA) constructor.newInstance();
        o.setName("张三");
        System.out.println(o.toString());
    }
}
