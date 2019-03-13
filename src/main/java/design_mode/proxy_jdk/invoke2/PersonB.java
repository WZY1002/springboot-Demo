package design_mode.proxy_jdk.invoke2;

public class PersonB {

    public String name="路人b";

    private int age=20;

    private String sex="男";

    public String sayHellow(){
        return "Hello";
    }

    private String sayLove(){
        return "lova";
    }

    public PersonB() {
    }

    public PersonB(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
