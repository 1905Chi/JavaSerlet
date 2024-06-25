package com.example.appfood.model;

public class ImageUpload {
    private int id;
    private String userName;
    private String avatar;

    public ImageUpload() {
    }

    public ImageUpload(int id, String userName, String avatar) {
        this.id = id;
        this.userName = userName;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
