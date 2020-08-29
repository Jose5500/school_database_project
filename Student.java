package com.company;

import java.util.ArrayList;

public class Student {
    private int id;
    private String name;
    private int age;
    private String sex;
    private int grade;
    private String school;
    private ArrayList<Classroom> classes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public ArrayList<Classroom> getClasses() {
        return classes;
    }

    public void addClasses(Classroom classroom) {
        classes.add(classroom);
    }
}
