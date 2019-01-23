package design_mode.abstract_factory.impl;

import design_mode.abstract_factory.Cpu;

public class IntelCpu implements Cpu {

    @Override
    public int calculate() {
        return 957;
    }
}
