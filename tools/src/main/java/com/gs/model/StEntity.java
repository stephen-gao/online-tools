package com.gs.model;

/**
 * source target 实体
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/08/03 11:03
 **/
public class StEntity {

    private int start;

    private String source;

    private String target;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public StEntity() {
    }

    public StEntity(int start, String source, String target) {
        this.start = start;
        this.source = source;
        this.target = target;
    }
}
