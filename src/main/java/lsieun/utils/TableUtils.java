package lsieun.utils;

import lsieun.drawing.canvas.Canvas;
import lsieun.drawing.canvas.Drawable;
import lsieun.drawing.table.OneLineTable;

public class TableUtils {
    public static void printTable(String[][] matrix) {
        Drawable drawable = new OneLineTable(matrix);
        Canvas canvas = new Canvas();
        canvas.draw(0, 0, drawable);
        canvas.rectifyPosition();
        String str = canvas.toString();
        System.out.println(str);
    }
}
