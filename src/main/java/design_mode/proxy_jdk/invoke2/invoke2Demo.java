package design_mode.proxy_jdk.invoke2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class invoke2Demo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
//        PersonB personB=new PersonB("小气",20,"女");
        PersonB personB=new PersonB();
        Class cl=personB.getClass();
        Field fieldName=cl.getDeclaredField("name");
        Field fieldAge=cl.getDeclaredField("age");
        fieldAge.setAccessible(true);
        System.out.println(fieldName.get(personB));
        System.out.println(fieldAge.get(personB));

        Field [] fields=cl.getFields();
        System.out.println(Arrays.toString(fields));
        Field [] fields1=cl.getDeclaredFields();
        System.out.println(Arrays.toString(fields1));

        Method methodHellw=cl.getMethod("sayHellow",cl);
        System.out.println(methodHellw);
        Method [] methods=cl.getMethods();
        System.out.println(methods);
        Method [] methods1=cl.getDeclaredMethods();
        System.out.println(methods1);


    }
}
