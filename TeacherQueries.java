package com.company;

import java.sql.*;
import java.util.ArrayList;

public class TeacherQueries {
    public static final String TEACHER_ID = "id";
    public static final String TEACHER_NAME = "name";
    public static final String TEACHER_SCHOOL = "school";
    public static final String TEACHER_CLASSES = "classes";

    public static final String DB_NAME = "teachers.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:";

    public static final String TEACHER_TABLE_NAME = "teachers";
    public static final String TEACHER_TABLE_CREATOR= "CREATE TABLE IF NOT EXISTS " + TEACHER_TABLE_NAME +  " ( " +
            TEACHER_ID + " INTEGER, " +
            TEACHER_NAME + " TEXT, " +
            TEACHER_SCHOOL + " TEXT, " +
            TEACHER_CLASSES + " TEXT" +
            " )";
    public static void createTeacherTableIfNotExist(){
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement teacherStatement = teacherConnection.createStatement();
        ){
            teacherStatement.execute(TEACHER_TABLE_CREATOR);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void getInformationAboutTeacher(int id){
        System.out.println("Teacher List");
        System.out.println("-----------");
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = teacherConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT * FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + id);
            while(results.next()){
                System.out.println(TEACHER_ID + ":" + results.getInt(TEACHER_ID)) ;
                System.out.println("Name:" + results.getString(TEACHER_NAME) + "\n" + "School:" + results.getString(TEACHER_SCHOOL));
                printClasses(id);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void addTeacherToTeacherTable(int id, String name, String school, String classes){
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement teacherStatement = teacherConnection.createStatement();
        ){
            ResultSet results = teacherStatement.executeQuery("SELECT * FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID +" = " + id);
            if(results.next() == false) {
                System.out.println("Are you sure you want to add " + name + "?");
                if(UserSelectionDecider.getDecision()) {
                    teacherStatement.execute("INSERT INTO " + TEACHER_TABLE_NAME + "(" + TEACHER_ID + "," + TEACHER_NAME + "," + TEACHER_SCHOOL + "," +
                            TEACHER_CLASSES + ") VALUES ('"
                            + id + "','" + name + "','" + school + "','" + classes + "')");
                    System.out.println("Successfully added " + name + "\n" +
                            "-----------------");
                }else{
                    System.out.println(name + " has not been added");
                }
            }else{
                System.out.println("Error:ID found. Teacher already exists in database");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void addTeacherToTeacherTableWithoutPromptingUser(int id, String name, String school, String classes){
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement teacherStatement = teacherConnection.createStatement();
        ){
            ResultSet results = teacherStatement.executeQuery("SELECT * FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID +" = " + id);
            if(results.next() == false) {
                    teacherStatement.execute("INSERT INTO " + TEACHER_TABLE_NAME + "(" + TEACHER_ID + "," + TEACHER_NAME + "," + TEACHER_SCHOOL + "," +
                            TEACHER_CLASSES + ") VALUES ('"
                            + id + "','" + name + "','" + school + "','" + classes + "')");
                    System.out.println("Successfully added " + name + "\n" +
                            "-----------------");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void shortPrintAllTeachersInTeachersTable(){
        System.out.println("Teacher List");
        System.out.println("-----------");
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = teacherConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT *  FROM " + TEACHER_TABLE_NAME);
            while(results.next()){
                System.out.println(TEACHER_ID + ":" + results.getInt(TEACHER_ID)) ;
                System.out.println("Name:" + results.getString(TEACHER_NAME));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void printClasses(int teacherId) {
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement teacherStatement = teacherConnection.createStatement();
        ){
            ResultSet results = teacherStatement.executeQuery("SELECT * FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + teacherId);
            while(results.next()) {
                String classes = results.getString(TEACHER_CLASSES);
                System.out.println("Classes\n" +
                        "--------------------------");
                ArrayList<String> classesNums = new ArrayList<>();
                String classesString = "";
                for (int i = 0; i < classes.length(); i++) {
                    if (classes.charAt(i) == ',') {
                        classesNums.add(classesString);
                        classesString = "";
                    } else {
                        classesString += classes.charAt(i);
                    }
                }
                if (classesNums.isEmpty()) {
                    System.out.println(results.getString(TEACHER_NAME) + " has no classes");
                } else {
                    for (int i = 0; i < classesNums.size(); i++) {
                        System.out.print("Class:");
                        System.out.println(ClassroomQueries.getClassroomName((Integer.parseInt(classesNums.get(i)))));
                    }
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void printTeachersInSchool(String school){
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = teacherConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT *  FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_SCHOOL + " = '" + school + "'");
            while(results.next()){
                System.out.println(TEACHER_ID + ":" + results.getInt(TEACHER_ID)) ;
                System.out.println("Name:" + results.getString(TEACHER_NAME));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Integer> getTeachersInSchool(String school){
        ArrayList<Integer> result = new ArrayList<>();
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = teacherConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT *  FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_SCHOOL + " = '" + school + "'");
            while(results.next()){
                result.add(results.getInt(TEACHER_ID));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static ArrayList<Integer> getClasses(int teacherId){
        ArrayList<Integer> result = new ArrayList<>();
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement teacherStatement = teacherConnection.createStatement();
        ){
            ResultSet results = teacherStatement.executeQuery("SELECT " + TEACHER_CLASSES + " FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + teacherId);
            String classes = results.getString(TEACHER_CLASSES);
            String classesString = "";
            for(int i = 0; i < classes.length(); i++){
                if(classes.charAt(i) == ','){
                    result.add(Integer.parseInt(classesString));
                    classesString = "";
                }else{
                    classesString+=classes.charAt(i);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static String getTeacherName(int id){
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = teacherConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT *  FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + id);
            while(results.next()){
                return results.getString(TEACHER_NAME);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
   public static void printStudentsWithTeacher(int id){
        String teacherName = "";
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT " + TEACHER_NAME + " FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + id);
            teacherName = results.getString(TEACHER_NAME);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        StudentQueries.printStudentsWithTeacher(teacherName);
    }
    public static boolean removeTeacher(int id){
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement teacherStatement = teacherConnection.createStatement();
        ){
            ResultSet results = teacherStatement.executeQuery("SELECT * FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + id);
            if(results.next() == true){
                System.out.println("Are you sure you want to delete " + results.getString(TEACHER_NAME) + "?\n" +
                        "All classrooms taught by this teacher will be deleted and removed from students.");
                if(UserSelectionDecider.getDecision()) {
                    System.out.println(results.getString(TEACHER_NAME) + " has been successfully deleted");
                    teacherStatement.execute("DELETE FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + id);
                    return true;
                }else{
                    System.out.println(results.getString(TEACHER_NAME) + " has not been deleted");
                    return false;
                }
            }else{
                System.out.println("Error. School doesn't exist");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void removeTeacherWithoutPromptingUser(int id){
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement teacherStatement = teacherConnection.createStatement();
        ){
            ResultSet results = teacherStatement.executeQuery("SELECT * FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + id);
            if(results.next() == true){
                    System.out.println(results.getString(TEACHER_NAME) + " has been successfully deleted");
                    teacherStatement.execute("DELETE FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + id);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void removeTeacherInClassroom(int id,String classroomName){
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement firstStatement = teacherConnection.createStatement();
            Statement secondStatement = teacherConnection.createStatement();
            Statement thirdStatement = teacherConnection.createStatement()
        ){
            ResultSet results = firstStatement.executeQuery("SELECT * FROM " + TEACHER_TABLE_NAME);
            while(results.next()){
                ArrayList<Integer> teacherClasses = getClasses(results.getInt(TEACHER_ID));
                for(int i = 0; i < teacherClasses.size(); i++){
                    if(teacherClasses.get(i) == id){
                        System.out.println("Removed " + classroomName + " from " + results.getString(TEACHER_NAME));
                        teacherClasses.remove(i);
                        String result = "";
                        for(int j = 0; j < teacherClasses.size(); j++){
                            result+=teacherClasses.get(j);
                            result+= ",";
                        }
                        secondStatement.execute("UPDATE " + TEACHER_TABLE_NAME + " SET " + TEACHER_CLASSES + " = '" + result + "' WHERE " + TEACHER_ID + " = " + results.getInt(TEACHER_ID));
                        if(teacherClasses.size() == 0){
                            System.out.println(results.getString(TEACHER_NAME) + " has no more classes, would you like to delete them?");
                            if(UserSelectionDecider.getDecision()){
                                System.out.println(results.getString(TEACHER_NAME) + " has been successfully deleted");
                                thirdStatement.execute("DELETE FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + results.getInt(TEACHER_ID));
                            }else{
                                System.out.println(results.getString(TEACHER_NAME) + " has not been deleted");
                            }
                        }
                    }
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static boolean alreadyExists(int id){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT * FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID +" = " + id);
            if(results.next() == false) {
                return false;
            }else{
                return true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
