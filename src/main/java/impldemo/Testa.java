package impldemo;

public class Testa implements ccc{

    public int a=9;

    public int b=10;

    @Override
    public void sout() {
        System.out.println("testa implements ccc");
    }

    //    public Testa() {
//        this.a = 1;
//        this.b = 2;
//    }

    {
        this.a=7;
        this.b=8;
    }
}
