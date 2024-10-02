package lsieun.drawing.table;

public abstract class AbstractTable implements Table {
    private final int totalRows;
    private final int totalCols;
    public final int cellInnerRowPadding;
    public final int cellInnerColPadding;

    public final int[] cellContentHeightArray;
    public final int[] cellContentWidthArray;

    public AbstractTable(int totalRows, int totalCols) {
        this(totalRows, totalCols, 0, 1);
    }

    public AbstractTable(
            int totalRows, int totalCols,
            int cellInnerRowPadding, int cellInnerColPadding
    ) {
        this.totalRows = totalRows;
        this.totalCols = totalCols;

        this.cellInnerRowPadding = cellInnerRowPadding;
        this.cellInnerColPadding = cellInnerColPadding;

        this.cellContentHeightArray = new int[totalRows];
        this.cellContentWidthArray = new int[totalCols];
    }

    @Override
    public int getTotalRows() {
        return totalRows;
    }

    @Override
    public int getTotalCols() {
        return totalCols;
    }

    @Override
    public int getCellContentWidth(int rowIndex, int colIndex) {
        return cellContentWidthArray[colIndex];
    }

    @Override
    public int getCellContentHeight(int rowIndex, int colIndex) {
        return cellContentHeightArray[rowIndex];
    }

    @Override
    public int getCellInnerRowPadding(int rowIndex, int colIndex) {
        return cellInnerRowPadding;
    }
    @Override
    public int getCellInnerColPadding(int rowIndex, int colIndex) {
        return cellInnerColPadding;
    }
}