package printer.printer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.IOException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;

public class MyPrintTest
        implements Printable
{
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException
    {
        Graphics2D g2 = (Graphics2D) graphics;
//      将图形在x轴方向平移dx像素
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        //表格长宽
        int w=720;
        int h=420;

        //绘制表格
        // 对指定区域填充颜色 左上角x,y，右下角width,height坐标
        g2.setColor(Color.red);
        g2.fillRect(0, 0, w, h);

        // 绘制区域内容
        //第一行
        g2.setColor(Color.black);
        // 绘制第一行文字
        Font firstFont =new Font("宋体", Font.BOLD, 18);
        FontMetrics zeroFontFm = g2.getFontMetrics(firstFont);
        // 获取将要绘制的文字宽度
        g2.setFont(firstFont);
        //设置文字内容，位置
        String title="东经科技股份有限公司-提货单";
        int zeroLine=25;
        g2.drawString(title, (w-zeroFontFm.stringWidth(title))/2, zeroLine);

        //第一行文字标题，加粗
        Font firstFontHead =new Font("宋体", Font.BOLD, 10);
        FontMetrics firstFontFm = sun.font.FontDesignMetrics.getMetrics(firstFontHead);
        //内容
        Font secondFontContent =new Font("宋体", Font.PLAIN, 10);
        //第一、二行起始
        int firstLine=5+zeroLine+zeroFontFm.getHeight();
        int secondLine=5+firstLine+firstFontFm.getHeight();
        String id="提货单号:";
        String person="打印人:";
        String time="打印时间:";
        String area="提货面积:";
        String loadTable="装车台:";
        g2.setFont(firstFontHead);
        g2.drawString(id, 50, firstLine);
        g2.drawString(person, 260, firstLine);
        g2.drawString(time, 460, firstLine);
        g2.drawString(area, 50, secondLine);
        g2.drawString(loadTable, 260, secondLine);

        g2.setFont(secondFontContent);
        g2.drawString("TH201813141231231231", firstFontFm.stringWidth(id)+50, firstLine);
        g2.drawString("马尔扎哈", firstFontFm.stringWidth(person)+260, firstLine);
        g2.drawString("2018-12-12 15:33", firstFontFm.stringWidth(time)+460, firstLine);
        g2.drawString("2018331m2", firstFontFm.stringWidth(area)+50, secondLine);
        g2.drawString("wzy测试临时装车台", firstFontFm.stringWidth(loadTable)+260, secondLine);

        //表格划线
        // 第一条横线 x1起始x轴，y1起始y轴
        int thirdLine=7+secondLine;
        int lastCrossLine=32*8+thirdLine;
        g2.drawLine(50,thirdLine,w-50 , thirdLine);
        int i=0;
        while(++i<9){
            g2.drawLine(50,32*i+thirdLine,w-50 , 32*i+thirdLine);
        }
        // 第一条竖线 x1起始x轴，y1起始y轴
        g2.drawLine(50,thirdLine,50 , lastCrossLine);
        int j=1;
        while(++j<12){
            g2.drawLine(j*(w-50)/11,thirdLine,j*(w-50)/11 , lastCrossLine);
        }
        //尾行
        int endLine=15+lastCrossLine;
        String deliveyPersion="提货人:";
        String deliveyTime="提货日期:";
        g2.setFont(secondFontContent);
        g2.drawString(deliveyPersion, 50, endLine);
        g2.drawLine(50+firstFontFm.stringWidth(deliveyPersion),endLine,130+firstFontFm.stringWidth(deliveyPersion) , endLine);
        g2.drawString(deliveyTime, 220, endLine);
        g2.drawLine(220+firstFontFm.stringWidth(deliveyTime),endLine,300+firstFontFm.stringWidth(deliveyTime) , endLine);


        //表格最后一行有可能不显示，手动画上一行
//        g2.drawLine(0, 1, 4, 1);
        g2.dispose();

        return Printable.PAGE_EXISTS;
    }

    public static void main(String[] args)
    {
        MyPrintTest myprinttest = new MyPrintTest();
        myprinttest.print();
    }

    public void print()
    {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        Book book = new Book();
        PageFormat pf = new PageFormat();
        //横打
        pf.setOrientation(PageFormat.PORTRAIT);
        //竖打
//        pf.setOrientation(PageFormat.LANDSCAPE);
        //通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        //纸张大小
        p.setSize(900, 900);
        //A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
        p.setImageableArea(72, 72, 842, 595);
        pf.setPaper(p);
        book.append(new MyPrintTest(),pf);
        printerJob.setPageable(book);
//        HashAttributeSet hs = new HashAttributeSet();
//        String printerName="EPSON TM-U220 Receipt";
//        hs.add(new PrinterName(printerName,null));
//        PrintService[] pss = PrintServiceLookup.lookupPrintServices(null, hs);
//        if(pss.length==0)
//        {
//            System.out.println("无法找到打印机:"+printerName);
//            return ;
//        }
        try
        {
            //printerJob.setPrintService(pss[0]);
            //循环批量打印
            for(int i=0;i<5;i++)
            {
//                this.obj= obj;
//                job.print();
                printerJob.print();
            }

        }
        catch (PrinterException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}