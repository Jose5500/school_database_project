package com.company;

public class ClassroomImplementations {
    public static void addClassroom(){
        System.out.println("Enter the classroom's id, name, teacher, and school");
        System.out.println("Id:");
        int id = UserSelectionDecider.getIntegerInput();
        System.out.println("Name:");
        String name = UserSelectionDecider.getStringInput();
        System.out.println("School");
        String school = UserSelectionDecider.getSchoolNameToAddToDatabase();
        System.out.println("Teacher:");
        String teacher = UserSelectionDecider.getTeacherNameToAddToDatabase(school);
        ClassroomQueries.addClassroomToClassroomTable(id,name,teacher,school);
    }
    public static void getListOfClassrooms(){
        ClassroomQueries.printClassrooms();
    }
    public static void printTeacherTeachingClass(){
        ClassroomQueries.printClassrooms();
        ClassroomQueries.printClassroomTeacher(getId());
    }
    public static void getStudentsInClass(){
        ClassroomQueries.printClassrooms();
        StudentQueries.printStudentsInClass(getId());
    }
    public static void removeClassroom(){
        ClassroomQueries.printClassrooms();
        int id = getId();
        String classRoomName = ClassroomQueries.getClassroomName(id);
        boolean result = ClassroomQueries.removeClassroom(id);
        if(result){
            StudentQueries.removeClassroomFromStudent(id,classRoomName);
            TeacherQueries.removeTeacherInClassroom(id,classRoomName);
        }
    }
    public static int getId(){
        System.out.println("Enter the classroom's id:");
        int id = UserSelectionDecider.getIntegerInput();
        return id;
    }
}
