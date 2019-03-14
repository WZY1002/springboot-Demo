package design_mode.proxy_jdk.invoke;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 反射demo
 * @author wzy
 * @since 2019/3/12 19:35
 **/
public class InvokeDemo {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        fieldDemo();
    }

    public static void fieldDemo() throws NoSuchFieldException, IllegalAccessException {
         PersonA personA=new PersonA(){{setName("张三");setAge(16);setSex("男");}};
         Class cl1=personA.getClass();
         Class cl2=PersonA.class;
//        System.out.println(personA);
//        System.out.println(cl1);

//        Field fieldName=cl1.getField("name");
//        System.out.println(fieldName.getDeclaringClass());
//        System.out.println(fieldName.get(personA));
//        fieldName.set(personA,"李四");
//        System.out.println(fieldName.get(personA));
//
//
//        Field fieldMarry=cl1.getDeclaredField("marry");
//        fieldMarry.setAccessible(true);
//        System.out.println(fieldMarry.get(personA));
//        fieldMarry.set(personA,"已婚");
//        System.out.println(fieldMarry.get(personA));
//
//
//        Field fieldAge=cl1.getDeclaredField("age");
//        fieldAge.setAccessible(true);
//        System.out.println(fieldAge.get(personA));
//        fieldAge.set(personA,18);
//        System.out.println(fieldAge.get(personA));


        Field[] fieldArray=cl1.getFields();
        Field[] fieldsArray=personA.getClass().getDeclaredFields();
//        System.out.println(Arrays.toString(fieldArray));
//        System.out.println(Arrays.toString(fieldsArray));

    }



}
