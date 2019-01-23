package design_mode.abstract_factory;

import design_mode.abstract_factory.impl.AmdFactory;

public class ComputerMain {

    public static void main(String[] args) {
        AbstractFactory parts=new AmdFactory();
        createComputer(parts);
    }

    private static void createComputer(AbstractFactory parts) {
        Cpu cpu=parts.createCpu();
        Board board=parts.createBoard();
        System.out.println(cpu.calculate());
        System.out.println(board.calculate());
        //组装电脑
    }

}
