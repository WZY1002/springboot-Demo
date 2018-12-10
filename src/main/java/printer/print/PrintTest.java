package printer.print;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.net.URLClassLoader;

public class PrintTest implements Printable {
    private String account;
    private String password;
    static final Image src = Toolkit.getDefaultToolkit().createImage(((URLClassLoader) PrintTest.class.getClassLoader()).findResource("22.jpg"));
    //获取打印服务对象
    static PrinterJob job = PrinterJob.getPrinterJob();
    static double paperW = job.getPageFormat(null).getPaper().getWidth();
    //获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)
    static double paperH = job.getPageFormat(null).getPaper().getHeight();

    public PrintTest(String account, String password) {
        this.account = account;
        this.password = password;
//        InputStream inputStream = this.getClass().getResourceAsStream("22.jpg");
//        src = Toolkit.getDefaultToolkit().getImage(img_path);
    }

    /**
     * @param Graphic 指明打印的图形环境fd
     * @param PageFormat 指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×842点）
     * @param pageIndex 指明页号
     */
    public int print(Graphics gra, PageFormat pf, int pageIndex) throws PrinterException {
        Component c = null;
        //print string
        String str = account;
        String str2 = password;
        //转换成Graphics2D
        Graphics2D g2 = (Graphics2D) gra;
        //设置打印颜色为黑色
        g2.setColor(Color.black);
//        //打印起点坐标 --vive: 用不着这里暂时，默认都是从纸张的头部开始
//        double x = pf.getImageableX();
//        double y = pf.getImageableY();
//        g2.translate(x,y);
        double x = 0;
        double y = 0;
        g2.translate(0, 0);
        if (true) {
            //设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
            //Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
            Font font = new Font("新宋体", Font.PLAIN, 35);
            g2.setFont(font);//设置字体
            //BasicStroke   bs_3=new   BasicStroke(0.5f);
            float[] dash1 = {5.0f};
            //设置打印线的属性。
            //1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
            g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash1, 0.0f));
            //g2.setStroke(bs_3);//设置线宽
            float heigth = font.getSize2D();//字体高度
//                int img_Height=src.getHeight(c);
//                int img_width=src.getWidth(c);
//                double pageW= pf.getImageableWidth();
//                double pageH = pf.getImageableHeight();
//                double pageW= 595.2;
//                double pageH = 192.7;
            double imageW = 2480;
            //src.getWidth(c);
            double imageH = 803;
            //src.getHeight(c);
//                double scaleX = pageW/2480;
//                double scaleY = pageH/803;
//                double scaleFactor = Math.min(scaleX, scaleY);
            g2.scale(0.24, 0.24);
            g2.drawImage(src, (int) x, (int) y, c);
//          System.out.println("img_Height="+img_Height+"img_width="+img_width) ;

            g2.drawString(str, (float) imageW - 506, (float) imageH - 200);
            g2.drawString(str2, (float) imageW - 506, (float) imageH - 120);

            // g2.drawImage(src,(int)x,(int)(y+1*heigth+img_Height+11),c);

            return PAGE_EXISTS;

        }
        return NO_SUCH_PAGE;

    }


    public static void printInterface(JTextArea textArea, java.util.List<String> account, java.util.List<String> password) {
        //    通俗理解就是书、文档
//        Book book = new Book();
        //    设置成竖打
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);
        //    通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        p.setSize(paperW, paperH);//纸张大小
        p.setImageableArea(0, 0, 595.2, 192.7);//A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
        pf.setPaper(p);
        //    把 PageFormat 和 Printable 添加到书中，组成一个页面
        Book firstPrintContent = new Book();
        firstPrintContent.append(new PrintTest(account.get(0), password.get(0)), pf);
        job.setPageable(firstPrintContent);
        try {
            job.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {

            //可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
            //boolean a=job.printDialog();
            //if(a)
            //{
            Book book = new Book();
            for (int i = 0; i < account.size(); i++) {
                if (account.get(i) != null && password.get(i) != null) {
                    textArea.setText(textArea.getText() + "\n" + "[" + account.get(i) + " ==> " + password.get(i) + "] 已打印");
                    book.append(new PrintTest(account.get(i), password.get(i)), pf);
                    // 设置打印类
                }
            }
            job.setPageable(book);
            job.print();
            //}
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}