package com.company;

import com.sun.nio.sctp.AbstractNotificationHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentImplementations {
    public static void addStudent(){
        System.out.println("Enter the students id,name,age,sex,grade,school, and classes");
        System.out.println("Id:");
        int id = UserSelectionDecider.getIntegerInput();
        System.out.println("First Name:");
        String firstName = UserSelectionDecider.getStringInput();
        System.out.println("Last Name:");
        String lastName = UserSelectionDecider.getStringInput();
        System.out.println("Age:");
        int age = UserSelectionDecider.selectionScreensIntInput(3,200);
        System.out.println("Sex:");
        String sex = UserSelectionDecider.getStringInput();
        System.out.println("Grade:");
        int grade = UserSelectionDecider.selectionScreensIntInput(0,12);
        System.out.println("School:");
        String school = UserSelectionDecider.getSchoolNameToAddToDatabase();
        String classes = UserSelectionDecider.getClassesToAddToDatabase(school);
        String fullName = firstName + " " + lastName;
        StudentQueries.addStudentToStudentTable(id,fullName,age,sex,grade,school,classes);
    }
    public static void getListOfStudents(){
        StudentQueries.shortPrintAllStudentsInStudentTable();
    }
    public static void getInformationAboutStudent(){
        StudentQueries.shortPrintAllStudentsInStudentTable();
        StudentQueries.getInformationAboutStudent(getId());
    }
    public static void removeStudent(){
        StudentQueries.shortPrintAllStudentsInStudentTable();
        StudentQueries.removeStudent(getId());
    }
    public static int getId(){
        System.out.println("Enter the student's id:");
        int id = UserSelectionDecider.getIntegerInput();
        return id;
    }
}
