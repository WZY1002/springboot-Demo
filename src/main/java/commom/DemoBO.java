package commom;

public class DemoBO {
    private String demo;

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    @Override
    public String toString() {
        return "DemoBO{" +
                "demo='" + demo + '\'' +
                '}';
    }
}
