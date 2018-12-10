package printer.book;

import java.awt.print.*;

public class PrintUtil {
    public static void Print(String a,String b,String c,String d) {

        //通俗理解就是书、文档
        Book book = new Book();
        //设置成竖打
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);
        //通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        //纸张大小
        p.setSize(590,840);
        //A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
        p.setImageableArea(10,10, 590,840);
        pf.setPaper(p);
        //把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append(new PrintTest(a, b, c, d), pf);
        //获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();
        // 设置打印类
        job.setPageable(book);
        try {
            //可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
//            boolean flag=job.printDialog();
//            if(flag)
//            {
                job.print();
//            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}

