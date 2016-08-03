package com.example.loan.exampletab;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by LOAN on 7/25/2016.
 */
public class Phone {
    private String name;
    private String number;
    private Uri icon;
    private Uri photo;

    public Phone(String name, String number, Uri icon,Uri photo) {
        this.name = name;
        this.number = number;
        this.icon = icon;
       this.photo=photo;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public Uri getIcon() {
        return icon;
    }

    public void setIcon(Uri icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}