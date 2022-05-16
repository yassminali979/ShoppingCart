package com.example.shoppingapp;

public class Model {
    String clothesname;
    String description;
    int Photo;
    public Model(String Name, String description, int Photo) {
        this.clothesname = Name;
        this.description = description;
        this.Photo = Photo;
    }
    public String getmDrinkName() {
        return clothesname;
    }

    public String getmDrinkDetail() {
        return description;
    }

    public int getmDrinkPhoto() {
        return Photo;
    }
}
