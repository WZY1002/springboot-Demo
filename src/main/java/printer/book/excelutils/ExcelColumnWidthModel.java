package printer.book.excelutils;

/**
 * @author TruthBean
 * @since 2018-01-17 17:59
 */
public class ExcelColumnWidthModel {
    /**
     * excel column index
     */
    private int index;

    /**
     * width
     */
    private int width;

    public ExcelColumnWidthModel(int index, int width) {
        this.index = index;
        this.width = width;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
