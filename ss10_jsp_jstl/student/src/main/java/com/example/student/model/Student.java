package com.example.student.model;

public class Student {
    private Integer id;
    private String name;
    private Integer gender;
    private Double score;

    public Student(Integer id, String name, String gender, Double score) {
        this.id = id;
        this.name = name;
        this.gender = Integer.valueOf(gender);
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}