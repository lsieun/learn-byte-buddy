package lsieun.drawing.shape.rect;

import lsieun.drawing.BoxMisc;
import lsieun.drawing.canvas.Canvas;
import lsieun.drawing.canvas.TextAlign;
import lsieun.drawing.shape.AbstractShape;
import lsieun.drawing.text.EmptyText;
import lsieun.drawing.text.Text;

import java.util.List;


/**
 * <ul>
 *     <li>borderWidth == 1</li>
 *     <li>innerWidth = contentWidth + 2 * paddingWidth</li>
 *     <li>totalWidth = contentWidth + 2 * paddingWidth + 2 * borderWidth</li>
 *     <li>width == totalWidth</li>
 * </ul>
 */
public class FullRectangle extends AbstractShape implements Rectangle {
    private static final int PADDING_WIDTH_DEFAULT = 0;
    private static final int PADDING_HEIGHT_DEFAULT = 0;
    private static final TextAlign TEXT_ALIGN_DEFAULT = TextAlign.CENTER_MIDDLE;

    private static final boolean REQUIRES_ODD_DEFAULT = true;

    public final int contentWidth;
    public final int contentHeight;
    public final int paddingWidth;
    public final int paddingHeight;

    public final Text text;
    public final TextAlign align;


    private FullRectangle(
            int contentWidth, int contentHeight,
            int paddingWidth, int paddingHeight,
            Text text, TextAlign align, boolean requiresOdd) {
        contentWidth = contentWidth == 0 ? text.getMaxColWidth() : contentWidth;
        contentHeight = contentHeight == 0 ? text.getTotalRows() : contentHeight;
        this.contentWidth = requiresOdd ? BoxMisc.getOdd(contentWidth) : contentWidth;
        this.contentHeight = requiresOdd ? BoxMisc.getOdd(contentHeight) : contentHeight;
        this.paddingHeight = paddingHeight;
        this.paddingWidth = paddingWidth;
        this.text = text;
        this.align = align;
    }

    @Override
    public void draw(Canvas canvas, int startRow, int startCol) {
        // save position
        super.draw(canvas, startRow, startCol);

        // innerWidth and innerHeight
        int innerWidth = contentWidth + 2 * paddingWidth;
        int innerHeight = contentHeight + 2 * paddingHeight;

        // draw rectangle
        canvas.moveTo(startRow, startCol);
        canvas.drawRectangle(innerWidth, innerHeight);

        // draw text
        int count = Math.min(contentHeight, text.getTotalRows());
        for (int i = 0; i < count; i++) {
            int row = getRowConsideringVerticalAlign(i);
            int col = getCol(0);
            canvas.moveTo(row, col);
            canvas.drawText(contentWidth, text.getLine(i), align.hAlign);
        }
    }

    public int getRowConsideringVerticalAlign(int textIndex) {
        switch (align.vAlign) {
            case MIDDLE: {
                int offset = contentHeight > text.getTotalRows() ? (contentHeight - text.getTotalRows()) / 2 : 0;
                return getRow(textIndex) + offset;
            }
            case BOTTOM: {
                int offset = contentHeight > text.getTotalRows() ? contentHeight - text.getTotalRows() : 0;
                return getRow(textIndex) + offset;
            }
            case TOP:
            default: {
                return getRow(textIndex);
            }
        }
    }


    @Override
    public int getContentWidth() {
        return contentWidth;
    }

    @Override
    public int getContentHeight() {
        return contentHeight;
    }

    @Override
    public int getPaddingWidth() {
        return paddingWidth;
    }

    @Override
    public int getPaddingHeight() {
        return paddingHeight;
    }

    public static FullRectangle of(Text text) {
        return of(text.getMaxColWidth(), text.getTotalRows(),
                PADDING_WIDTH_DEFAULT, PADDING_HEIGHT_DEFAULT,
                text, TEXT_ALIGN_DEFAULT, REQUIRES_ODD_DEFAULT);
    }

    public static FullRectangle of(int contentWidth, Text text) {
        return of(contentWidth, text.getTotalRows(),
                PADDING_WIDTH_DEFAULT, PADDING_HEIGHT_DEFAULT,
                text, TEXT_ALIGN_DEFAULT, REQUIRES_ODD_DEFAULT);
    }

    public static FullRectangle of(int contentWidth, int contentHeight) {
        return of(contentWidth, contentHeight,
                PADDING_WIDTH_DEFAULT, PADDING_HEIGHT_DEFAULT,
                EmptyText.INSTANCE, TEXT_ALIGN_DEFAULT, REQUIRES_ODD_DEFAULT);
    }

    public static FullRectangle of(
            int contentWidth, int contentHeight,
            int paddingWidth, int paddingHeight,
            String[] array, TextAlign align, boolean requiresOdd) {
        return of(contentWidth, contentHeight,
                paddingWidth, paddingHeight,
                Text.of(array), align, requiresOdd);
    }

    public static FullRectangle of(
            int contentWidth, int contentHeight,
            int paddingWidth, int paddingHeight,
            List<String> lines, TextAlign align, boolean requiresOdd) {
        return of(contentWidth, contentHeight,
                paddingWidth, paddingHeight,
                Text.of(lines), align, requiresOdd);
    }

    public static FullRectangle of(
            int contentWidth, int contentHeight,
            int paddingWidth, int paddingHeight,
            Text text, TextAlign align, boolean requiresOdd) {
        return new FullRectangle(contentWidth, contentHeight,
                paddingWidth, paddingHeight,
                text, align, requiresOdd);
    }
}