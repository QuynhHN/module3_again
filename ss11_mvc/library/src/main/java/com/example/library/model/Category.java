package com.example.library.model;

public class Category {
    private Integer idCategory;
    private String nameCategory;

    public Category(Integer idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public Category(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Category(String categoryName) {
        this.nameCategory = nameCategory;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
