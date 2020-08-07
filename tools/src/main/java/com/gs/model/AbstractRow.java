package com.gs.model;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/08/07 17:20
 **/
public class AbstractRow implements Row {

    protected int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
