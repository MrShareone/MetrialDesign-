package com.example.mr_shareone.materialdesign.bean;

import java.io.Serializable;

/**
 * Autor：created by MR-SHAREONE on 2018/6/4 11 16
 * Emain:13437105740@163.com
 */
public class Fruit implements Serializable{
    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
