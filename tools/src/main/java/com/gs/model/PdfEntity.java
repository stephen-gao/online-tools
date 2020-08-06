package com.gs.model;

import java.util.Map;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/08/06 09:13
 **/
public class PdfEntity {

    private String clazz;

    private String id;

    private String text;

    private Map<String,String> style;

    @Override
    public String toString() {
        return "PdfEntity{" +
                "clazz='" + clazz + '\'' +
                ", id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", style=" + style +
                '}';
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getStyle() {
        return style;
    }

    public void setStyle(Map<String, String> style) {
        this.style = style;
    }
}
