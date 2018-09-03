package impldemo;


import org.springframework.stereotype.Component;

@Component
public class bbb extends aaa implements ccc{
    @Override
    public void aa() {
        super.aa();
        System.out.println("bba");
    }

    public void bb(){
        System.out.println("bb2");
    }

    @Override
    public void sout(String cc) {
        System.out.println("implccc");
        System.out.println(cc);
    }
}
