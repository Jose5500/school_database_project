package com.company;

import java.util.ArrayList;

public class Teacher {
    private int id;
    private String name;
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
