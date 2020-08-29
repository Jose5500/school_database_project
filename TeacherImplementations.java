package com.company;

import java.util.ArrayList;

public class TeacherImplementations {
    public static void addTeacher(){
        System.out.println("Enter the teacher's id, name, school, and classes");
        System.out.println("Id:");
        int id = UserSelectionDecider.getIntegerInput();
        System.out.println("First Name:");
        String firstName = UserSelectionDecider.getStringInput();
        System.out.println("Last Name:");
        String lastName = UserSelectionDecider.getStringInput();
        System.out.println("School:");
        String school = UserSelectionDecider.getSchoolNameToAddToDatabase();
        String classes = UserSelectionDecider.getClassesToAddToDatabase(school);
        TeacherQueries.addTeacherToTeacherTable(id,lastName,school,classes);
    }
    public static void getListOfTeachers(){
        TeacherQueries.shortPrintAllTeachersInTeachersTable();
    }
    public static void getInformationAboutTeacher(){
        TeacherQueries.shortPrintAllTeachersInTeachersTable();
        TeacherQueries.getInformationAboutTeacher(getId());
    }
    public static void getTeachersClassrooms(){
        TeacherQueries.shortPrintAllTeachersInTeachersTable();
        TeacherQueries.printClasses(getId());
    }
   public static void getTeachersStudents(){
       TeacherQueries.shortPrintAllTeachersInTeachersTable();
        System.out.println("Enter the teachers's id:");
        int id = UserSelectionDecider.getIntegerInput();
        TeacherQueries.printStudentsWithTeacher(id);
    }
    public static void removeTeacher(){
        TeacherQueries.shortPrintAllTeachersInTeachersTable();
        int id = getId();
        String teacherName = TeacherQueries.getTeacherName(id);
        boolean result = TeacherQueries.removeTeacher(id);
        if(result){
            StudentQueries.removeClassroomFromStudentWithTeacher(teacherName);
            ClassroomQueries.removeClassroomWithTeacher(teacherName);
        }
    }
    public static int getId(){
        System.out.println("Enter the teacher's id:");
        int id = UserSelectionDecider.getIntegerInput();
        return id;
    }
}
