package com.example.appfood.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("images")
    private String images;
    @SerializedName("descrition")
    private  String descrition;

    public CategoryModel() {
    }

    public CategoryModel(String id, String name, String images, String descrition) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.descrition = descrition;
    }

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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }
}
