package com.example.root.myvolleydemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 5/23/17.
 */

public class ProductDto implements Parcelable {
    String id;
    String name;
    String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.price);
    }

    public ProductDto() {
    }

    protected ProductDto(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.price = in.readString();
    }

    public static final Creator<ProductDto> CREATOR = new Creator<ProductDto>() {
        @Override
        public ProductDto createFromParcel(Parcel source) {
            return new ProductDto(source);
        }

        @Override
        public ProductDto[] newArray(int size) {
            return new ProductDto[size];
        }
    };
}
