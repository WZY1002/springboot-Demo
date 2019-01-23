package design_mode.abstract_factory;

/**
 * 组件产品抽象工厂
 * @author wzy
 * @date 2019/1/15
 **/
public interface AbstractFactory {

    /**
     * 生产cpu
     * @author wzy
     * @date 2019/1/15
     **/
    public Cpu createCpu();

    /**
     * 生产主板
     * @author wzy
     * @date 2019/1/15
     **/
    public Board createBoard();

}
