package lsieun.drawing.text;

import java.util.List;

public interface Text {
    int getTotalRows();

    int getMaxColWidth();

    String getLine(int rowIndex);

    static Text of(String... array) {
        if (array == null) {
            return EmptyText.INSTANCE;
        }
        else {
            return new MultiLineText(array);
        }
    }

    static Text of(List<String> lines) {
        if (lines == null) {
            return EmptyText.INSTANCE;
        }
        else {
            int size = lines.size();
            String[] array = new String[size];
            for (int i = 0; i < size; i++) {
                String str = lines.get(i);
                array[i] = str;
            }
            return new MultiLineText(array);
        }
    }
}