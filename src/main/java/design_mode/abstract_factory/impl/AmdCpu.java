package design_mode.abstract_factory.impl;

import design_mode.abstract_factory.Cpu;

public class AmdCpu  implements Cpu{

    @Override
    public int calculate() {
        return 999;
    }
}
