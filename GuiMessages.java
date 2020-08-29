package com.company;

public class GuiMessages {
    public static void printWelcomeMessage(){
        System.out.println("Welcome to this school database");
    }
    public static void printMainSelectionScreen(){
        System.out.println("Enter the number for what section you'd like to visit\n" +
                "1:Schools\n"+
                "2:Teachers\n"+
                "3:Classrooms\n"+
                "4:Students");
    }
    public static void printSchoolSelectionScreen(){
        System.out.println("1:Add school\n"+
                "2:Get list of schools\n" +
                "3:Get information about school\n" +
                "4:Get teachers in school\n"+
                "5:Get classrooms in school\n"+
                "6:Get students in school\n" +
                "7:Remove school\n" +
                "8:Exit");
    }
    public static void printTeacherSelectionScreen(){
        System.out.println("Enter the number for what'd you like to do\n" +
                "1:Add teacher\n"+
                "2:Get list of teachers\n" +
                "3:Get information about teacher\n"+
                "4:Get teachers' classrooms\n"+
                "5:Get teachers students\n" +
                "6:Remove teacher\n" +
                "7:Exit");
    }
    public static void printClassroomSelectionScreen(){
        System.out.println("Enter the number for what'd you like to do\n" +
                "1:Add classroom\n"+
                "2:Get list of classrooms\n"+
                "3:Get teacher which teaches this class\n" +
                "4:Get students in this class\n"+
                "5:Remove Classroom\n" +
                "6:Exit");
    }
    public static void printStudentSelectionScreen(){
        System.out.println("Enter the number for what'd you like to do\n" +
                "1:Add student\n"+
                "2:Get list of students\n" +
                "3:Get information about student\n"+
                "4:Remove student\n" +
                "5:Exit");
    }
}

