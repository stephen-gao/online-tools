package com.gs.model;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/08/07 17:18
 **/
public class TextRow extends AbstractRow {

    private int fontType;

    private int fontSize;

    private String content;

    public int getFontType() {
        return fontType;
    }

    public void setFontType(int fontType) {
        this.fontType = fontType;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
