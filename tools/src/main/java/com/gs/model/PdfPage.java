package com.gs.model;

import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/08/06 17:10
 **/
public class PdfPage {
    private String clazz;

    private String id;

    private String text;

    private List<PdfEntity> rows;

    @Override
    public String toString() {
        return "PdfPage{" +
                "clazz='" + clazz + '\'' +
                ", id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", rows=" + rows +
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

    public List<PdfEntity> getRows() {
        return rows;
    }

    public void setRows(List<PdfEntity> rows) {
        this.rows = rows;
    }
}
