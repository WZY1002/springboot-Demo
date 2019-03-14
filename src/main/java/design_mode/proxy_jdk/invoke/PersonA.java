package design_mode.proxy_jdk.invoke;

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
