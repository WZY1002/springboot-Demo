package design_mode.abstract_factory.impl;

import design_mode.abstract_factory.AbstractFactory;
import design_mode.abstract_factory.Board;
import design_mode.abstract_factory.Cpu;

/**
 * Amd产品
 * @author wzy
 * @date 2019/1/15
 **/
public class AmdFactory implements AbstractFactory{
    @Override
    public Cpu createCpu() {
        return new AmdCpu();
    }

    @Override
    public Board createBoard() {
        return new AmdBoard();
    }
}
