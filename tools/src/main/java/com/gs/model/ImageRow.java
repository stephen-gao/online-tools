package com.gs.model;

import java.awt.image.BufferedImage;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/08/07 17:18
 **/
public class ImageRow extends AbstractRow {

    private int width;

    private int hight;

    private BufferedImage image;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
