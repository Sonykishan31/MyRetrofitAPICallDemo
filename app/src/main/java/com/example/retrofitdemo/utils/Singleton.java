package com.example.retrofitdemo.utils;

import java.io.File;
import java.util.ArrayList;

public class Singleton {
    private static Singleton uniqInstance;


    private Integer testDiscountId = 0;
    private Integer packageDiscountId = 0;

    private final File prescritionFile = null;
    private final ArrayList<File> imagesList = new ArrayList<File>();


    private Singleton() {

    }

    public static Singleton getInstance() {
        if (uniqInstance == null)
            uniqInstance = new Singleton();
        return uniqInstance;
    }

    public Integer getTestDiscountId() {
        return testDiscountId;
    }

    public void setTestDiscountId(Integer testDiscountId) {
        this.testDiscountId = testDiscountId;
    }

    public Integer getPackageDiscountId() {
        return packageDiscountId;
    }

    public void setPackageDiscountId(Integer packageDiscountId) {
        this.packageDiscountId = packageDiscountId;
    }

}