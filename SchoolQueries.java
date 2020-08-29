package com.company;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class SchoolQueries {
    public static final String SCHOOL_ID = "id";
    public static final String SCHOOL_NAME = "name";
    public static final String SCHOOL_ADDRESS = "address";
    public static final String SCHOOL_CITY = "city";
    public static final String SCHOOL_STATE = "state";
    public static final String SCHOOL_COUNTRY = "country";

    public static final String DB_NAME = "schools.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:";

    public static final String SCHOOL_TABLE_NAME = "schools";
    public static final String SCHOOL_TABLE_CREATOR= "CREATE TABLE IF NOT EXISTS " + SCHOOL_TABLE_NAME +  " ( " +
            SCHOOL_ID + " INTEGER , " +
            SCHOOL_NAME + " TEXT, " +
            SCHOOL_ADDRESS + " TEXT, " +
            SCHOOL_CITY + " TEXT, " +
            SCHOOL_STATE + " TEXT, " +
            SCHOOL_COUNTRY + " TEXT " +
            ")";

    public static void createSchoolTableIfNotExist(){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            schoolStatement.execute(SCHOOL_TABLE_CREATOR);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void addSchoolToSchoolTable(int id, String name, String address, String city, String state, String country){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT * FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID +" = " + id);
            if(results.next() == false) {
                System.out.println("Are you sure you want to add " + name + "?");
                if(UserSelectionDecider.getDecision()) {
                    schoolStatement.execute("INSERT INTO " + SCHOOL_TABLE_NAME + "(" + SCHOOL_ID + "," + SCHOOL_NAME + "," + SCHOOL_ADDRESS + "," +
                            SCHOOL_CITY + "," + SCHOOL_STATE + "," + SCHOOL_COUNTRY + ") VALUES ('"
                            + id + "','" + name + "','" + address + "','" + city + "','" + state + "','" + country + "')");
                    System.out.println("Successfully added " + name + "\n" +
                            "-------------------------");
                }else{
                    System.out.println(name + " has not been added");
                }
            }else{
                System.out.println("Error:ID found. School already exists in database");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void addToSchoolToSchoolTableWithoutPromptingUser(int id, String name, String address, String city, String state, String country){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT * FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID +" = " + id);
            if(results.next() == false) {
                    schoolStatement.execute("INSERT INTO " + SCHOOL_TABLE_NAME + "(" + SCHOOL_ID + "," + SCHOOL_NAME + "," + SCHOOL_ADDRESS + "," +
                            SCHOOL_CITY + "," + SCHOOL_STATE + "," + SCHOOL_COUNTRY + ") VALUES ('"
                            + id + "','" + name + "','" + address + "','" + city + "','" + state + "','" + country + "')");
                    System.out.println("Successfully added " + name + "\n" +
                            "-------------------------");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void getInformationAboutSchool(int id){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT * FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID + " = " + id);
            while(results.next()){
                System.out.println("Name:" + results.getString(SCHOOL_NAME) + "\n" + "Address:" + results.getString(SCHOOL_ADDRESS) + "\n" + "City,State Country:"+ results.getString(SCHOOL_CITY) + "," +
                        results.getString(SCHOOL_STATE) + " " + results.getString(SCHOOL_COUNTRY));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void printTeachersInSchool(int id){
        String schoolName = "";
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT " + SCHOOL_NAME + " FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID + " = " + id);
            schoolName = results.getString(SCHOOL_NAME);
            TeacherQueries.printTeachersInSchool(schoolName);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void printClassroomsInSchool(int id){
        String schoolName = "";
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT " + SCHOOL_NAME + " FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID + " = " + id);
            schoolName = results.getString(SCHOOL_NAME);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        ClassroomQueries.printClassroomsInSchool(schoolName);
    }
    public static void printStudentsInSchool(int id){
        String schoolName = "";
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT " + SCHOOL_NAME + " FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID + " = " + id);
            schoolName = results.getString(SCHOOL_NAME);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        StudentQueries.printStudentsInSchool(schoolName);
    }
    public static void shortPrintAllSchoolsInSchoolTable(){
        System.out.println("School List");
        System.out.println("-----------");
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT * FROM " + SCHOOL_TABLE_NAME);
            while(results.next()){
                System.out.println(SCHOOL_ID + ":" + results.getInt(SCHOOL_ID)) ;
                System.out.println("Name:" + results.getString(SCHOOL_NAME));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static boolean removeSchool(int id){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT * FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID + " = " + id);
            if(results.next() == true){
                System.out.println("Are you sure you want to delete " + results.getString(SCHOOL_NAME) + "? All students/classrooms/teachers in this school will also be deleted.");
                if(UserSelectionDecider.getDecision()) {
                    System.out.println(results.getString(SCHOOL_NAME) + " has been successfully deleted");
                    schoolStatement.execute("DELETE FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID + " = " + id);
                    return true;
                }else{
                    System.out.println(results.getString(SCHOOL_NAME) + " has not been deleted");
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
    public static boolean removeSchoolWithoutPromptingUser(int id){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT * FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID + " = " + id);
            if(results.next() == true){
                    System.out.println(results.getString(SCHOOL_NAME) + " has been successfully deleted");
                    schoolStatement.execute("DELETE FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID + " = " + id);
                    return true;
                }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static String getSchoolName(int id){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT " + SCHOOL_NAME + " FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID + " = " + id);
            return results.getString(SCHOOL_NAME);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    public static boolean alreadyExists(int id){
        try(Connection schoolConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement schoolStatement = schoolConnection.createStatement();
        ){
            ResultSet results = schoolStatement.executeQuery("SELECT * FROM " + SCHOOL_TABLE_NAME + " WHERE " + SCHOOL_ID +" = " + id);
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
