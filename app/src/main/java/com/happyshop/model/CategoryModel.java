package com.happyshop.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by terril1 on 20/05/16.
 */
public class CategoryModel implements Parcelable{


    private String id;
    private String name;
    private String category;
    private String price;
    private String imgUrl;
    private boolean underSale;

    public CategoryModel(){

    }

    protected CategoryModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        category = in.readString();
        price = in.readString();
        imgUrl = in.readString();
        underSale = in.readByte() != 0;
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean getUnderSale() {
        return underSale;
    }

    public void setUnderSale(boolean underSale) {
        this.underSale = underSale;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(price);
        dest.writeString(imgUrl);
        dest.writeByte((byte) (underSale ? 1 : 0));
    }
}
