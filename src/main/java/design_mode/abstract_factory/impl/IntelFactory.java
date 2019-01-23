package design_mode.abstract_factory.impl;

import design_mode.abstract_factory.AbstractFactory;
import design_mode.abstract_factory.Board;
import design_mode.abstract_factory.Cpu;

/**
 * Intel产品
 * @author wzy
 * @date 2019/1/15
 **/
public class IntelFactory implements AbstractFactory{

    @Override
    public Cpu createCpu() {
        return new IntelCpu();
    }

    @Override
    public Board createBoard() {
        return new IntelBoard();
    }
}
