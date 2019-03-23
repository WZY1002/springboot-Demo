package javabase.generic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 泛型类,引入泛型变量在类名后面，类中可以有多个类型变量
 * @author wzy
 * @since 2019/3/21 9:48
 **/
@Data
@ToString
@EqualsAndHashCode
public class GenericClass<T> {
    private T ortherName;

    private T ortherSex;

    public GenericClass() { }

    public GenericClass(T ortherName, T ortherSex) {
        this.ortherName = ortherName;
        this.ortherSex = ortherSex;
    }
}
