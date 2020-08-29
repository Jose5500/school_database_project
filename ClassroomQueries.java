package com.company;

import java.sql.*;
import java.util.ArrayList;

public class ClassroomQueries {
    public static final String CLASSROOM_ID = "id";
    public static final String CLASSROOM_NAME = "name";
    public static final String CLASSROOM_TEACHER = "teacher";
    public static final String CLASSROOM_SCHOOL = "school";

    public static final String DB_NAME = "classrooms.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:";

    public static final String CLASSROOM_TABLE_NAME = "classrooms";
    public static final String CLASSROOM_TABLE_CREATOR= "CREATE TABLE IF NOT EXISTS " + CLASSROOM_TABLE_NAME +  " ( " +
            CLASSROOM_ID + " INTEGER, " +
            CLASSROOM_NAME + " TEXT, " +
            CLASSROOM_TEACHER + " TEXT, " +
            CLASSROOM_SCHOOL + " TEXT " +
            ")";
    public static void createClassroomTableIfNotExist(){
        try(Connection classroomConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement classroomStatement = classroomConnection.createStatement();
        ){
            classroomStatement.execute(CLASSROOM_TABLE_CREATOR);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void printClassrooms(){
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = teacherConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT *  FROM " + CLASSROOM_TABLE_NAME);
            while(results.next()){
                System.out.println(CLASSROOM_ID + ":" + results.getInt(CLASSROOM_ID)) ;
                System.out.println("Name:" + results.getString(CLASSROOM_NAME));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void addClassroomToClassroomTable(int id,String name, String teacher,String school){
        try(Connection classroomConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement classroomStatement = classroomConnection.createStatement();
        ){
            ResultSet results = classroomStatement.executeQuery("SELECT * FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_ID +" = " + id);
            if(results.next() == false) {
                System.out.println("Are you sure you want to add " + name + "?");
                if(UserSelectionDecider.getDecision()) {
                    classroomStatement.execute("INSERT INTO " + CLASSROOM_TABLE_NAME + "(" + CLASSROOM_ID + "," + CLASSROOM_NAME + "," + CLASSROOM_TEACHER + "," +
                            CLASSROOM_SCHOOL + ") VALUES ('"
                            + id + "','" + name + "','" + teacher + "','" + school + "')");
                    System.out.println("Successfully added " + name + " with " + teacher + "\n" +
                            "-----------------------");
                }else{
                    System.out.println(name + " has not been added");
                }
            }else{
                System.out.println("Error:ID found. Student already exists in database");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void addClassroomToClassroomTableWithoutPromptingUser(int id,String name, String teacher,String school){
        try(Connection classroomConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement classroomStatement = classroomConnection.createStatement();
        ){
            ResultSet results = classroomStatement.executeQuery("SELECT * FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_ID +" = " + id);
            if(results.next() == false) {
                    classroomStatement.execute("INSERT INTO " + CLASSROOM_TABLE_NAME + "(" + CLASSROOM_ID + "," + CLASSROOM_NAME + "," + CLASSROOM_TEACHER + "," +
                            CLASSROOM_SCHOOL + ") VALUES ('"
                            + id + "','" + name + "','" + teacher + "','" + school + "')");
                    System.out.println("Successfully added " + name + " with " + teacher + "\n" +
                            "-----------------------");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void printClassroomTeacher(int id){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT " + CLASSROOM_TEACHER +" FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_ID + " = " + id);
            while(results.next()){
                System.out.println("Teacher:" + results.getString(CLASSROOM_TEACHER));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static String getClassroomTeacher(int id){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT " + CLASSROOM_TEACHER +" FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_ID + " = " + id);
            while(results.next()){
                return results.getString(CLASSROOM_TEACHER);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    public static String getClassroomName(int id){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT " + CLASSROOM_NAME +" FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_ID + " = " + id);
            while(results.next()){
                return results.getString(CLASSROOM_NAME);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    public static void printClassroomsInSchool(String school){
        try(Connection teacherConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = teacherConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT *  FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_SCHOOL + " = '" + school + "'");
            while(results.next()){
                System.out.println(CLASSROOM_ID + ":" + results.getInt(CLASSROOM_ID)) ;
                System.out.println("Name:" + results.getString(CLASSROOM_NAME));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Integer> getClassroomsInSchool(String school){
        ArrayList<Integer> result = new ArrayList<>();
        try(Connection classroomConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement classroomStatement = classroomConnection.createStatement();
        ){
            ResultSet results = classroomStatement.executeQuery("SELECT *  FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_SCHOOL + " = '" + school + "'");
            while(results.next()){
                result.add(results.getInt(CLASSROOM_ID));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static boolean removeClassroom(int id){
        try(Connection classroomConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement classroomStatement = classroomConnection.createStatement();
        ){
            ResultSet results = classroomStatement.executeQuery("SELECT * FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_ID + " = " + id);
            if(results.next() == true){
                System.out.println("Are you sure you want to delete " + results.getString(CLASSROOM_NAME) + "?");
                if(UserSelectionDecider.getDecision()) {
                    System.out.println(results.getString(CLASSROOM_NAME) + " has been successfully deleted");
                    classroomStatement.execute("DELETE FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_ID + " = " + id);
                    return true;
                }else{
                    System.out.println(results.getString(CLASSROOM_NAME) + " has not been deleted");
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
    public static void removeClassroomWithoutPromptingUser(int id){
        try(Connection classroomConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement classroomStatement = classroomConnection.createStatement();
        ){
            ResultSet results = classroomStatement.executeQuery("SELECT * FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_ID + " = " + id);
            if(results.next() == true){
                    System.out.println(results.getString(CLASSROOM_NAME) + " has been successfully deleted");
                    classroomStatement.execute("DELETE FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_ID + " = " + id);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void removeClassroomWithTeacher(String name){
        try(Connection classroomConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement classroomStatement = classroomConnection.createStatement();
        ){
            ResultSet results = classroomStatement.executeQuery("SELECT * FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_TEACHER + " = '" + name + "'");
            if(results.next() == true){
                System.out.println(results.getString(CLASSROOM_NAME) + " has been successfully deleted");
                classroomStatement.execute("DELETE FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_TEACHER + " = '" + name + "'");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static boolean alreadyExists(int id){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT * FROM " + CLASSROOM_TABLE_NAME + " WHERE " + CLASSROOM_ID +" = " + id);
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
