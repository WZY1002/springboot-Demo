package printer.book;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class PrintTest implements Printable{
    private String str;
    private String name;
    private String age;
    private String sex;
    public String getStr() {
        return str;
    }
    public void setStr(String str) {
        this.str = str;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public PrintTest(String str, String name, String age, String sex) {
        super();
        this.str = str;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public int print(Graphics gra, PageFormat pf, int pageIndex) throws PrinterException {
        System.out.println("pageIndex="+pageIndex);
        Component c = null;
        //print string

        //转换成Graphics2D
        Graphics2D g2 = (Graphics2D) gra;
        //设置打印颜色为黑色
        g2.setColor(Color.black);

        //打印起点坐标
        double x = pf.getImageableX();
        double y = pf.getImageableY();

        switch(pageIndex){
            case 0:
                //设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
                //Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
                Font font = new Font("新宋体", Font.PLAIN, 9);
                //设置字体
                g2.setFont(font);
                //BasicStroke   bs_3=new   BasicStroke(0.5f);
                float[]   dash1   =   {2.0f};
                //设置打印线的属性。
                //1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
                g2.setStroke(new   BasicStroke(0.5f,   BasicStroke.CAP_BUTT,   BasicStroke.JOIN_MITER,   2.0f,   dash1,   0.0f));
                //g2.setStroke(bs_3);//设置线宽
                //字体高度
                float heigth = font.getSize2D();
                System.out.println("x="+x);
                // -1- 用Graphics2D直接输出
                //首字符的基线(右下部)位于用户空间中的 (x, y) 位置处
                //g2.drawLine(10,10,200,300);

                Image src = Toolkit.getDefaultToolkit().getImage("D://EclipseWorkSpace3.1//Kfc-wuxi//WebRoot//image//KFC.jpg");
                g2.drawImage(src,(int)x,(int)y,c);
                int img_Height=src.getHeight(c);
                int img_width=src.getWidth(c);
                //System.out.println("img_Height="+img_Height+"img_width="+img_width) ;

                g2.drawString(str, (float)x, (float)y+1*heigth+img_Height+100);
                g2.drawString(name, (float)x, (float)y+1*heigth+img_Height+200);
                g2.drawString(age, (float)x, (float)y+1*heigth+img_Height+300);
                g2.drawString(sex, (float)x, (float)y+1*heigth+img_Height+400);
                g2.drawLine((int)x,(int)(y+1*heigth+img_Height+10),(int)x+100,(int)(y+1*heigth+img_Height+10));

                g2.drawImage(src,(int)x,(int)(y+1*heigth+img_Height+11),c);

                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;
        }

    }
}
