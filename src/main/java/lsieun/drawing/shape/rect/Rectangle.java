package lsieun.drawing.shape.rect;

import lsieun.drawing.canvas.Drawable;

public interface Rectangle extends Drawable {
    // region start row and col
    int getStartRow();

    int getStartCol();

    void setStartRow(int startRow);

    void setStartCol(int startCol);
    // endregion

    // region width and height: content
    int getContentWidth();

    int getContentHeight();
    // endregion

    // region width and height: padding
    int getPaddingWidth();

    int getPaddingHeight();
    // endregion

    // region width and height: border
    default int getBorderWidth() {
        return 1;
    }

    default int getBorderHeight() {
        return 1;
    }
    // endregion

    // region width and height: total
    default int getTotalWidth() {
        return getContentWidth() + 2 * getPaddingWidth() + 2 * getBorderWidth();
    }

    default int getTotalHeight() {
        return getContentHeight() + 2 * getPaddingHeight() + 2 * getBorderHeight();
    }

    default int getWidth() {
        return getTotalWidth();
    }

    default int getHeight() {
        return getTotalHeight();
    }
    // endregion

    // region top, bottom, left, right
    default int getTop() {
        return getStartRow();
    }

    default int getBottom() {
        return getStartRow() + getTotalHeight() - 1;
    }

    default int getLeft() {
        return getStartCol();
    }

    default int getRight() {
        return getStartCol() + getTotalWidth() - 1;
    }
    // endregion

    // region row and col
    default int getRow(int relativeRowIndex) {
        return getStartRow() + getBorderHeight() + getPaddingHeight() + relativeRowIndex;
    }

    default int getCol(int colIndex) {
        return getStartCol() + getBorderWidth() + getPaddingWidth() + colIndex;
    }
    // endregion

    // region center
    default int getCenterRow() {
        return (getTop() + getBottom()) / 2;
    }

    default int getCenterCol() {
        return (getLeft() + getRight()) / 2;
    }
    // endregion
}