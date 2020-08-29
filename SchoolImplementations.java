package com.company;

import java.util.ArrayList;

public class SchoolImplementations {
    public static void addSchool(){
        System.out.println("Enter the school's id, name, address, city, state, and country");
        System.out.println("Id:");
        int id = UserSelectionDecider.getIntegerInput();
        System.out.println("Name:");
        String name = UserSelectionDecider.getStringInput();
        System.out.println("Address:");
        String address = UserSelectionDecider.getStringInput();
        System.out.println("City:");
        String city = UserSelectionDecider.getStringInput();
        System.out.println("State:");
        String state = UserSelectionDecider.getStringInput();
        System.out.println("Country:");
        String country = UserSelectionDecider.getStringInput();
        SchoolQueries.addSchoolToSchoolTable(id,name,address,city,state,country);
    }
    public static void getListOfSchools(){
        SchoolQueries.shortPrintAllSchoolsInSchoolTable();
    }
    public static void getInformationAboutSchool(){
        SchoolQueries.shortPrintAllSchoolsInSchoolTable();
        SchoolQueries.getInformationAboutSchool(getId());
    }
    public static void getTeachersInSchool(){
        SchoolQueries.shortPrintAllSchoolsInSchoolTable();
        SchoolQueries.printTeachersInSchool(getId());
    }
    public static void getClassroomsInSchool(){
        SchoolQueries.shortPrintAllSchoolsInSchoolTable();
        SchoolQueries.printClassroomsInSchool(getId());
    }
    public static void getStudentsInSchool(){
        SchoolQueries.shortPrintAllSchoolsInSchoolTable();
        SchoolQueries.printStudentsInSchool(getId());
    }
    public static void removeSchool(){
        SchoolQueries.shortPrintAllSchoolsInSchoolTable();
        int id = getId();
        String schoolName = SchoolQueries.getSchoolName(id);
        boolean result = SchoolQueries.removeSchool(id);
        if(result){
            ArrayList<Integer> teacherIdList = TeacherQueries.getTeachersInSchool(schoolName);
            ArrayList<Integer> studentIdList = StudentQueries.getStudentsInSchool(schoolName);
            ArrayList<Integer> classroomIdList = ClassroomQueries.getClassroomsInSchool(schoolName);
            for(int i = 0; i < teacherIdList.size(); i++){
                TeacherQueries.removeTeacherWithoutPromptingUser(teacherIdList.get(i));
            }
            for(int i = 0; i < studentIdList.size(); i++){
                StudentQueries.removeStudentWithoutPromptingUser(studentIdList.get(i));
            }
            for(int i = 0; i < classroomIdList.size(); i++){
                ClassroomQueries.removeClassroomWithoutPromptingUser(classroomIdList.get(i));
            }
        }
    }
    public static int getId(){
        System.out.println("Enter the school's id:");
        int id = UserSelectionDecider.getIntegerInput();
        return id;
    }
}
