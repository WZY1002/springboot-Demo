package design_mode.proxy_jdk.invokeGet;

import java.io.Serializable;

public class PersonA implements Serializable{

    public String name;

    private Integer age;

    private String marry="保密";

    public String sex;


    public String sayHello(){
        return "Hellw";
    }

    private String sayLove(){
        return "I Love You";
    }


//    public PersonA() {
//    }

//    private PersonA(String name,Integer age){
//        this.name = name;
//        this.age = age;
//    }
//
//    public PersonA(String name, Integer age, String marry, String sex) {
//        this.name = name;
//        this.age = age;
//        this.marry = marry;
//        this.sex = sex;
//    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "PersonA{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
