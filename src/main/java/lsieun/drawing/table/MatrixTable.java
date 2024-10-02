package lsieun.drawing.table;

import lsieun.drawing.BoxMisc;

import java.util.Arrays;

public abstract class MatrixTable extends AbstractTable {
    private static final String SPACE = " ";

    protected final String[][] matrix;

    public MatrixTable(String[][] matrix) {
        this(matrix, 0, 1);
    }

    public MatrixTable(String[][] matrix, int cellInnerRowPadding, int cellInnerColPadding) {
        super(matrix.length, matrix[0].length, cellInnerRowPadding, cellInnerColPadding);
        this.matrix = matrix;

        Arrays.fill(cellContentHeightArray, 1);
        int[] array = BoxMisc.getColWidthArray(matrix);
        System.arraycopy(array, 0, cellContentWidthArray, 0, array.length);
    }

    public String getCellValue(int row, int col) {
        String item = matrix[row][col];
        return item == null ? SPACE : item;
    }
}