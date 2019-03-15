package design_mode.proxy_jdk.invoke;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射demo
 * @author wzy
 * @since 2019/3/12 19:35
 **/
public class InvokeDemo {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        PersonA personA=new PersonA("李四",21,"保密","男");
        PersonA personA=new PersonA();
        //匿名内部类的对象，无法一直存在虚拟机中，所以getDeclaredXxx()方法将无效，但getXxx方法会递归计算超类所以能获取到;
        PersonA personB=new PersonA(){{setName("张三");setAge(16);setSex("男");setMarry("未婚");}};
        Class cl=personA.getClass();
        Class clb=personB.getClass();

//        System.out.println(cl);
//        System.out.println(clb);
//        System.out.println(cl.getDeclaringClass());
//        System.out.println(clb.getDeclaringClass());
//
//        Field fieldName=cl.getField("name");
//        Field fieldNameDeclaring=cl.getDeclaredField("age");
//        System.out.println(fieldName.get(personA));
//        fieldName.set(personA,"李四");
//        System.out.println(fieldName.get(personA));
//        fieldNameDeclaring.setAccessible(true);
//        Field[] fieldArray=cl.getFields();
//        Field[] fieldArray1=cl.getDeclaredFields();
        //递归计算获取该Class和超类的所有公有域
        Field[] fieldsArray=clb.getFields();
        //只计算获取该Class的所有域
        Field[] fieldsArray1=clb.getDeclaredFields();
//        System.out.println(Arrays.toString(fieldArray));
//        System.out.println(Arrays.toString(fieldArray1));
        System.out.println(Arrays.toString(fieldsArray));
//        System.out.println(Arrays.toString(fieldsArray1));
//
//        Method [] methods=cl.getMethods();
//        System.out.println(Arrays.toString(methods));

//        Method [] methods1=cl.getDeclaredMethods();
//        System.out.println(Arrays.toString(methods1));
//
//        Constructor [] constructor=cl.getConstructors();
//        System.out.println(Arrays.toString(constructor));
//        Constructor [] constructors=cl.getDeclaredConstructors();
//        System.out.println(Arrays.toString(constructors));

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

//        System.out.println(Arrays.toString(fieldArray));
//        System.out.println(Arrays.toString(fieldsArray));

    }



}
