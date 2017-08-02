package com.golui.shopping.models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RAVI on 7/31/2017.
 */

public class ProductListItems implements Parcelable {

    private int drawableRes;
    private String name;
    private String price;

    public ProductListItems() {
    }

    public ProductListItems(int drawableRes, String name, String price) {
        this.drawableRes = drawableRes;
        this.name = name;
        this.price = price;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.drawableRes);
        dest.writeString(this.name);
        dest.writeString(this.price);
    }

    protected ProductListItems(Parcel in) {
        this.drawableRes = in.readInt();
        this.name = in.readString();
        this.price = in.readString();
    }

    public static final Parcelable.Creator<ProductListItems> CREATOR = new Parcelable.Creator<ProductListItems>() {
        @Override
        public ProductListItems createFromParcel(Parcel source) {
            return new ProductListItems(source);
        }

        @Override
        public ProductListItems[] newArray(int size) {
            return new ProductListItems[size];
        }
    };
}
