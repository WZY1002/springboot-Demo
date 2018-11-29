package commom;

import java.util.ArrayList;
import java.util.List;

public class DemoBO {
    private String demo;

    private String block;

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    //实例初始化块
    {
        setBlock("demo");
        setDemo("demo");
    }

    public DemoBO(String demo, String block) {
        this.demo = demo;
        this.block = block;
    }

    public DemoBO() {
    }

    @Override
    public String toString() {
        return "DemoBO{" +
                "demo='" + demo + '\'' +
                ", block='" + block + '\'' +
                '}';
    }

    public String getOutClass(){
        return "外围类的使用权";
    }

    public List getInnerField(){
        List ss=new ArrayList(){
            {
                add("内部类list");
            }
        };
        return ss;
    }

    public List getNormalField() {
        List<String> ss = new ArrayList<>();
        ss.add("正常list");
        return ss;
    }

}
