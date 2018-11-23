package algorithm.model;

/**
 * 冒泡排序实体
 * @author wzy
 * @date 2018/11/23
 **/
public class SortBO {

    private Integer num;

    private String name;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SortBO{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
