package lsieun.drawing.text;

public class EmptyText implements Text {
    public static final EmptyText INSTANCE = new EmptyText();

    @Override
    public int getTotalRows() {
        return 0;
    }

    @Override
    public int getMaxColWidth() {
        return 0;
    }

    @Override
    public String getLine(int rowIndex) {
        return null;
    }
}