package lsieun.drawing;

public class BoxMisc {

    public static int maxLength(String[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int max = 0;
        for (String str : array) {
            if (str == null) {
                continue;
            }
            int len = str.length();
            if (len > max) {
                max = len;
            }
        }
        return max;
    }

    public static int[] getColWidthArray(String[][] matrix) {
        int totalRows = matrix.length;
        int totalCols = matrix[0].length;
        int[] colWidthArray = new int[totalCols];
        for (int col = 0; col < totalCols; col++) {
            for (int row = 0; row < totalRows; row++) {
                String str = matrix[row][col];
                int len = str == null ? 0 : str.length();
                if (len > colWidthArray[col]) {
                    colWidthArray[col] = len;
                }
            }
        }
        return colWidthArray;
    }

    public static int getOdd(int num) {
        return ((num & 1) == 0) ? num + 1 : num;
    }
}
