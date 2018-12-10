package printer.readxlsx;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest {

    public static void test(String path) throws Exception {
        int sheetNum = 0;//读取表格第几页
        int imageWidth = 0;//图片宽度
        int imageHeight = 0;//图片高度

        // int nullRowCount = 0;

        InputStream inputStream = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(sheetNum);
        List<List<String>> excelList = readXlsx(path, sheetNum);
        List<CellRangeAddress> rangeAddress = sheet.getMergedRegions(); // 获取整个sheet中合并单元格组合的集合
        //根据读取数据，动态获得表边界行列
        int totalRow = excelList.size() + 1;
        int totalCol = excelList.get(0).size();

        //创建单元格数组，用于遍历单元格
        UserCell[][] cells = new UserCell[totalRow + 1][totalCol + 1];
        int[] rowPixPos = new int[totalRow + 1];// 存放行边界
        rowPixPos[0] = 0;
        int[] colPixPos = new int[totalCol + 1];// 存放列边界
        colPixPos[0] = 0;
        //开始遍历单元格
        for (int i = 0; i < totalRow; i++) {
            for (int j = 1; j < totalCol; j++) {
                cells[i][j] = new UserCell();
                cells[i][j].setCell(sheet.getRow(i).getCell(j));
                cells[i][j].setRow(i);
                cells[i][j].setCol(j);
                boolean ifShow = !(sheet.isColumnHidden(j) || sheet.getRow(i).getZeroHeight());
                cells[i][j].setShow(ifShow);
                // 计算所求区域宽度
                float widthPix = !ifShow ? 0 : (sheet.getColumnWidthInPixels(j)); // 如果该单元格是隐藏的，则置宽度为0
                if (i == 0) {
                    imageWidth += widthPix;
                }
                colPixPos[j + 1] = (int) (widthPix * 1.15 + colPixPos[j]);
            }
            // 计算所求区域高度
            boolean ifShow = (i >= 0); // 行序列在指定区域中间
            ifShow = ifShow && !sheet.getRow(i).getZeroHeight(); // 行序列不能隐藏
            float heightPoint = !ifShow ? 0 : (sheet.getRow(i).getHeightInPoints()); // 如果该单元格是隐藏的，则置高度为0
            imageHeight += heightPoint;
            rowPixPos[i + 1] = (int) (heightPoint * 96 / 72) + rowPixPos[i];
        }
        imageHeight = imageHeight * 96 / 72;
        imageWidth = imageWidth * 115 / 100;

		/* ------------------------------ */
        List<Grid> grids = new ArrayList<Grid>();
        for (int i = 0; i < totalRow; i++) {
            for (int j = 1; j < totalCol; j++) {
                Grid grid = new Grid();
                // 设置坐标和宽高
                grid.setX(colPixPos[j]);
                grid.setY(rowPixPos[i]);
                grid.setWidth(colPixPos[j + 1] - colPixPos[j]);
                grid.setHeight(rowPixPos[i + 1] - rowPixPos[i]);
                grid.setRow(cells[i][j].getRow());
                grid.setCol(cells[i][j].getCol());
                grid.setShow(cells[i][j].isShow());

                // 判断是否为合并单元格
                int[] isInMergedStatus = isInMerged(grid.getRow(), grid.getCol(), rangeAddress);
                if (isInMergedStatus[0] == 0 && isInMergedStatus[1] == 0) {
                    // 此单元格是合并单元格，并且不是第一个单元格，需要跳过本次循环，不进行绘制
                    continue;
                } else if (isInMergedStatus[0] != -1 && isInMergedStatus[1] != -1) {
                    // 此单元格是合并单元格，并且属于第一个单元格，则需要调整网格大小
                    int lastRowPos = isInMergedStatus[0] > totalRow - 1 ? totalRow - 1 : isInMergedStatus[0];
                    int lastColPos = isInMergedStatus[1] > totalCol - 1 ? totalCol - 1 : isInMergedStatus[1];
                    grid.setWidth(colPixPos[lastColPos + 1] - colPixPos[j]);
                    grid.setHeight(rowPixPos[lastRowPos + 1] - rowPixPos[i]);

                }
                // 单元格背景颜色
                Cell cell = cells[i][j].getCell();
                if (cell != null) {
                    CellStyle cs = cell.getCellStyle();
                    grid.setBgColor(cs.getFillForegroundColorColor());
                    // 设置字体
                    org.apache.poi.ss.usermodel.Font font = workbook.getFontAt(cs.getFontIndex());
                    grid.setFont(font);
                    // 设置前景色
                    grid.setFtColor(cs.getFillBackgroundColorColor());
                    // 设置文本
                    String strCell;
                    CellType cellType = cell.getCellTypeEnum();
                    switch (cellType) {
                        case STRING:
                            strCell = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            strCell = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BLANK:
                            strCell = "";
                            break;
                        case FORMULA:
                            try {
                                strCell = String.valueOf(cell.getNumericCellValue());
                            } catch (IllegalStateException e) {
                                strCell = String.valueOf(cell.getRichStringCellValue());
                            }
                            break;
                        default:
                            strCell = "";
                            break;
                    }
                    if (cell.getCellStyle().getDataFormatString().contains("0.00%")) {
                        try {
                            double dbCell = Double.valueOf(strCell);
                            strCell = new DecimalFormat("0.00").format(dbCell * 100) + "%";
                        } catch (NumberFormatException e) {
                        }
                    }
                    grid.setText(strCell.matches("\\w*\\.0") ? strCell.substring(0, strCell.length() - 2) : strCell);
                }
                grids.add(grid);
            }
        }

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = image.createGraphics();

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, imageWidth, imageHeight);
        // 平滑字体
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 140);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // 绘制表格
        for (Grid g : grids) {
            if (!g.isShow()) {
                continue;
            }
            // 绘制背景色
            g2d.setColor(g.getBgColor() == null ? Color.white : g.getBgColor());
            g2d.fillRect(g.getX(), g.getY(), g.getWidth(), g.getHeight());

            // 绘制边框
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawRect(g.getX(), g.getY(), g.getWidth(), g.getHeight());

            // 绘制文字,居中显示
            g2d.setColor(g.getFtColor());
            Font font = g.getFont();
            if (font == null) {
                continue;
            }
            FontMetrics fm = g2d.getFontMetrics(font);
            int strWidth = fm.stringWidth(g.getText());// 获取将要绘制的文字宽度
            g2d.setFont(font);
            g2d.drawString(g.getText(), g.getX() + (g.getWidth() - strWidth) / 2,
                    g.getY() + (g.getHeight() - font.getSize()) / 2 + font.getSize());
        }
        //表格最后一行有可能不显示，手动画上一行
        g2d.drawLine(0, imageHeight - 1, imageWidth - 4, imageHeight - 1);

        g2d.dispose();
        ImageIO.write(image, "png", new File("E:/test.png"));

        System.out.println("Output to PNG file Success!");
    }

    /**
     * 判断Excel中的单元格是否为合并单元格
     *
     * @param row
     * @param col
     * @param rangeAddress
     * @return 如果不是合并单元格返回{-1,-1},如果是合并单元格并且是一个单元格返回{lastRow,lastCol},
     *         如果是合并单元格并且不是第一个格子返回{0,0}
     */
    private static int[] isInMerged(int row, int col, List<CellRangeAddress> rangeAddress) {
        int[] isInMergedStatus = { -1, -1 };
        for (CellRangeAddress cra : rangeAddress) {
            if (row == cra.getFirstRow() && col == cra.getFirstColumn()) {
                isInMergedStatus[0] = cra.getLastRow();
                isInMergedStatus[1] = cra.getLastColumn();
                return isInMergedStatus;
            }
            if (row >= cra.getFirstRow() && row <= cra.getLastRow()) {
                if (col >= cra.getFirstColumn() && col <= cra.getLastColumn()) {
                    isInMergedStatus[0] = 0;
                    isInMergedStatus[1] = 0;
                    return isInMergedStatus;
                }
            }
        }
        return isInMergedStatus;
    }

    public static List<List<String>> readXlsx(String path, int pageNum) throws Exception {
        InputStream in = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
        List<List<String>> resultList = new ArrayList<List<String>>();
        // 遍历每一页，并循环处理当前页
        // for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets();
        // numSheet++) {
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(pageNum);// 获取当前页
        if (xssfSheet == null) {
            // continue;
        } else {
            // 处理当前页，并处理每一行
            for (int rowNum = 0; rowNum < xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);// 获取行数据

                int minColIX = xssfRow.getFirstCellNum();// 第一行数据
                int maxColIX = xssfRow.getLastCellNum();// 总行数
                List<String> rowList = new ArrayList<String>();
                // 遍历该行，处理该行数据
                for (int colIX = minColIX; colIX < maxColIX; colIX++) {
                    XSSFCell cell = xssfRow.getCell(colIX);// 获取单元格
                    if (cell == null) {
                        continue;
                    } else {
                        rowList.add(cell.toString());
                    }
                }
                resultList.add(rowList);
            }
        }
        // }
        return resultList;
    }

    /**
     * 读取xls文件
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static List<List<String>> readXls(String path, int num) throws Exception {
        InputStream in = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
        List<List<String>> resultList = new ArrayList<List<String>>();
        // 遍历每一页，并循环处理当前页
        // for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets();
        // numSheet++) {
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(num);// 获取当前页
        if (hssfSheet == null) {
            // continue;
        } else {
            // 处理当前页，并处理每一行
            for (int rowNum = 0; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);// 获取行数据

                int minColIX = hssfRow.getFirstCellNum();// 第一行数据
                int maxColIX = hssfRow.getLastCellNum();// 总行数
                List<String> rowList = new ArrayList<String>();
                // 遍历该行，处理该行数据
                for (int colIX = minColIX; colIX < maxColIX; colIX++) {
                    HSSFCell cell = hssfRow.getCell(colIX);// 获取单元格
                    if (cell == null) {
                        continue;
                    } else {
                        rowList.add(cell.toString());
                    }
                }
                resultList.add(rowList);
            }
        }
        // }
        return resultList;
    }

    public static void main(String[] args) throws Exception {
        String path = "E:/3MS年前优化版本需求清单.xlsx";
        test(path);
    }

}