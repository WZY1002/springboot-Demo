package printer.book;

import java.awt.print.Book;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import printer.book.excelutils.Constants;
import printer.book.excelutils.ExcelColumnWidthModel;

public class BookUtil {

    /**
     * @param bigTitle    第一行标题
     * @param titleStrs   第二行标题
     * @param contentList 内容
     * @param filename    文件名
     * @param distFileDir 目标路径
     * @param sheetName  sheet名称记录详情
     * @param
     * @author wzy
     * @date 2018/12/5
     **/
    public static Workbook getBook(String bigTitle, String[] titleStrs, List<T[]> contentList,
                               String filename, String sheetName, String distFileDir,
                               List<ExcelColumnWidthModel> widthModels){
        boolean isExcel2003=true;
        String filePath = distFileDir + filename;
        FileOutputStream fileOut = null;
        Workbook workbook = null;
        try {
            workbook = writeExcel(bigTitle, titleStrs, contentList == null ? new ArrayList<>() : contentList, sheetName, isExcel2003, widthModels);
            fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * 写excel文件
     * @param bigTitle 标题
     * @param titleStrs 标题行
     * @param contentList 内容列
     * @param sheetName sheet名称
     * @param isExcel2003 是否是2003格式
     * @return workbook
     */
    public static <T>Workbook writeExcel(String bigTitle, String[] titleStrs,
                                         List<T[]> contentList, String sheetName, boolean isExcel2003, List<ExcelColumnWidthModel> widthModels) {
        int size = contentList.size();
        //列数
        int titleCount = titleStrs.length;
        // 创建新HSSFWorkbook对象
        Workbook workbook;
        if (isExcel2003) {
            workbook = new HSSFWorkbook();
            //EXCEL2003格式最大行数是65535
            if (size >= Constants.EXCEL2003_MAX_ROW) {
                return null;
            }
        } else {
            workbook = new SXSSFWorkbook();
        }

        //执行样式初始化
        List<CellStyle> cellStyles = setExcelStyle(workbook);
        CellStyle BIG_TITLE_STYLE = cellStyles.get(0);
        CellStyle TITLE_STYLE = cellStyles.get(1);
        CellStyle CONTENT_STYLE = cellStyles.get(2);

        // 创建新的sheet对象
        Sheet sheet = workbook.createSheet(sheetName);
        int begin = setExcelTitle(bigTitle, titleStrs, isExcel2003, sheet, titleCount, size, widthModels
                ,BIG_TITLE_STYLE,TITLE_STYLE);

        Row row = null;
        Cell cell = null;
        String x = null;
        T[] contents = null;

        // 写内容
        for (int i = 0, contentCount = size; i < contentCount; i++) {

            contents = contentList.get(i);

            // 新建一行
            row = sheet.createRow(i + 2 + begin);
            for (int j = 0; j < titleCount; j++) {
                // 新建一个单元格
                cell = row.createCell(j);
                //设置内容样式
                cell.setCellStyle(CONTENT_STYLE);
                //cell.getCellStyle().cloneStyleFrom(CONTENT_STYLE);

                T t = contents[j];

                x = t == null ? "" : String.valueOf(t);

                if(t == null){
                    cell.setCellValue("");
                } else if(t instanceof String){
                    cell.setCellValue(isExcel2003 ? new HSSFRichTextString(x) : new XSSFRichTextString(x));
                } else if(t instanceof BigDecimal){
                    cell.setCellValue(Double.parseDouble(new BigDecimal(x).stripTrailingZeros().toPlainString()));
                } else {
                    cell.setCellValue(Double.parseDouble(x));
                }
            }
        }
        return workbook;
    }

    private static List<CellStyle> setExcelStyle(Workbook workBook) {
        List<CellStyle> objects = new ArrayList<>();

        // 设置列标题字体，样式
        Font bigTitleFont =  workBook.createFont();
        bigTitleFont.setBold(true);
        bigTitleFont.setBold(true);
        short color = 0x000;
        bigTitleFont.setColor(color);
        bigTitleFont.setItalic(false);
        bigTitleFont.setFontName("微软雅黑");
        short heightInPoints = 16;
        bigTitleFont.setFontHeightInPoints(heightInPoints);

        CellStyle BIG_TITLE_STYLE = workBook.createCellStyle();
        // 设置边框
        BIG_TITLE_STYLE.setBorderTop(BorderStyle.THIN);
        BIG_TITLE_STYLE.setBorderBottom(BorderStyle.THIN);
        BIG_TITLE_STYLE.setBorderLeft(BorderStyle.THIN);
        BIG_TITLE_STYLE.setBorderRight(BorderStyle.THIN);
        BIG_TITLE_STYLE.setVerticalAlignment(VerticalAlignment.CENTER);
        BIG_TITLE_STYLE.setAlignment(HorizontalAlignment.CENTER);
        BIG_TITLE_STYLE.setFont(bigTitleFont);
        objects.add(BIG_TITLE_STYLE);
        // 标题列样式
        CellStyle TITLE_STYLE = workBook.createCellStyle();
        Font titleFont = workBook.createFont();
        titleFont.setBold(true);
        // 设置边框
        TITLE_STYLE.setBorderTop(BorderStyle.THIN);
        TITLE_STYLE.setBorderBottom(BorderStyle.THIN);
        TITLE_STYLE.setBorderLeft(BorderStyle.THIN);
        TITLE_STYLE.setBorderRight(BorderStyle.THIN);
        TITLE_STYLE.setVerticalAlignment(VerticalAlignment.CENTER);
        TITLE_STYLE.setAlignment(HorizontalAlignment.CENTER);
        titleFont.setColor(IndexedColors.WHITE.getIndex());
        TITLE_STYLE.setFont(titleFont);
        TITLE_STYLE.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        TITLE_STYLE.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        objects.add(TITLE_STYLE);
        // 内容列样式
        CellStyle CONTENT_STYLE = workBook.createCellStyle();
        CONTENT_STYLE.setBorderTop(BorderStyle.THIN);
        CONTENT_STYLE.setBorderBottom(BorderStyle.THIN);
        CONTENT_STYLE.setBorderLeft(BorderStyle.THIN);
        CONTENT_STYLE.setBorderRight(BorderStyle.THIN);
        CONTENT_STYLE.setVerticalAlignment(VerticalAlignment.CENTER);
        CONTENT_STYLE.setAlignment(HorizontalAlignment.CENTER);
        objects.add(CONTENT_STYLE);
        return objects ;
    }

    /**
     * 设置excel标题
     *
     * @param bigTitle    第一行标题
     * @param titleStrs   数据标题
     * @param isExcel2003 是否是2003格式的excel
     * @param sheet       excel里面的sheet
     * @param titleCount  标题个数
     * @param begin       开始插入excel的行数
     *
     * @return 最好插入excel的行数
     */
    private static int setExcelTitle(String bigTitle, String[] titleStrs, boolean isExcel2003,
                                     Sheet sheet, int titleCount, int begin, List<ExcelColumnWidthModel> widthModels
            ,CellStyle BIG_TITLE_STYLE,CellStyle TITLE_STYLE
    ) {
        //创建第一行
        if(titleCount > 1) {
            //单元格范围 参数（int firstRow, int lastRow, int firstCol, int lastCol)
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, titleCount - 1);
            //添加合并单元格
            sheet.addMergedRegion(cellRangeAddress);
        }

        //创建单元格并接设置值为富文本
        Row bigTitleRow = sheet.createRow(0);
        Cell first = bigTitleRow.createCell(0);
        RichTextString str;
        if (isExcel2003) {
            str = new HSSFRichTextString(bigTitle);
        } else {
            str = new XSSFRichTextString(bigTitle);
        }

        first.setCellValue(str);
        first.setCellStyle(BIG_TITLE_STYLE);
        //first.getCellStyle().cloneStyleFrom(BIG_TITLE_STYLE);
        bigTitleRow.setHeightInPoints(22);

        //第二行，标题行
        Row titleRow = sheet.createRow(1);

        //20像素
        titleRow.setHeightInPoints(20);

        // 写标题
        Cell cell;
        for (int k = 0; k < titleCount; k++) {
            // 新建一个单元格
            cell = titleRow.createCell(k);

            //设置标题样式
            cell.setCellStyle(TITLE_STYLE);
            //cell.getCellStyle().cloneStyleFrom(TITLE_STYLE);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(titleStrs[k]);
            //设置列宽
            sheet.setColumnWidth(k, 9000);
            if(widthModels != null){
                for (ExcelColumnWidthModel model : widthModels) {
                    if (k == model.getIndex()) {
                        sheet.setColumnWidth(k, model.getWidth());
                    }
                }
            }
            begin = sheet.getLastRowNum() - 1;
        }

        //冻结第一、二行
        sheet.createFreezePane(0, 2);
        return begin;
    }

}
