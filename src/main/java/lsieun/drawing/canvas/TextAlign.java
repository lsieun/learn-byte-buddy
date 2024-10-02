package lsieun.drawing.canvas;

public enum TextAlign {
    LEFT_TOP(HorizontalAlign.LEFT, VerticalAlign.TOP),
    LEFT_MIDDLE(HorizontalAlign.LEFT, VerticalAlign.MIDDLE),
    LEFT_BOTTOM(HorizontalAlign.LEFT, VerticalAlign.BOTTOM),
    CENTER_TOP(HorizontalAlign.CENTER, VerticalAlign.TOP),
    CENTER_MIDDLE(HorizontalAlign.CENTER, VerticalAlign.MIDDLE),
    CENTER_BOTTOM(HorizontalAlign.CENTER, VerticalAlign.BOTTOM),
    RIGHT_TOP(HorizontalAlign.RIGHT, VerticalAlign.TOP),
    RIGHT_MIDDLE(HorizontalAlign.RIGHT, VerticalAlign.MIDDLE),
    RIGHT_BOTTOM(HorizontalAlign.RIGHT, VerticalAlign.BOTTOM);

    public final HorizontalAlign hAlign;
    public final VerticalAlign vAlign;

    TextAlign(HorizontalAlign hAlign, VerticalAlign vAlign) {
        this.hAlign = hAlign;
        this.vAlign = vAlign;
    }

}
