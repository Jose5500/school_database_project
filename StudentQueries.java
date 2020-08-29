package com.company;

import java.sql.*;
import java.util.ArrayList;

public class StudentQueries {
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_AGE = "age";
    public static final String STUDENT_SEX = "sex";
    public static final String STUDENT_GRADE = "grade";
    public static final String STUDENT_SCHOOL = "school";
    public static final String STUDENT_CLASSES = "classes";

    public static final String DB_NAME = "students.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:";

    public static final String STUDENT_TABLE_NAME = "students";
    public static final String STUDENT_TABLE_CREATOR= "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE_NAME +  " ( " +
            STUDENT_ID + " INTEGER, " +
            STUDENT_NAME + " TEXT, " +
            STUDENT_AGE + " INTEGER, " +
            STUDENT_SEX + " TEXT, " +
            STUDENT_GRADE + " INTEGER, " +
            STUDENT_SCHOOL + " TEXT, " +
            STUDENT_CLASSES + " TEXT " +
            ")";
    public static void createStudentsTableIfNotExist(){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            studentStatement.execute(STUDENT_TABLE_CREATOR);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void getInformationAboutStudent(int id){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID + " = " + id);
            while(results.next()){
                System.out.println(STUDENT_ID + ":" + results.getInt(STUDENT_ID)) ;
                System.out.println("Name:" + results.getString(STUDENT_NAME) + "\n" + "Age:" + results.getInt(STUDENT_AGE) + "\n" + "Sex:"+ results.getString(STUDENT_SEX) + "\n" +
                        "Grade:" + results.getInt(STUDENT_GRADE) + "\n" + "School:" + results.getString(STUDENT_SCHOOL));
                printClasses(id);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void addStudentToStudentTable(int id, String name, int age, String sex, int grade, String school, String classes){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID +" = " + id);
            if(results.next() == false) {
                System.out.println("Are you sure you want to add " + name + "?");
                if(UserSelectionDecider.getDecision()) {
                    studentStatement.execute("INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_ID + "," + STUDENT_NAME + "," + STUDENT_AGE + "," +
                            STUDENT_SEX + "," + STUDENT_GRADE + "," + STUDENT_SCHOOL + "," + STUDENT_CLASSES + ") VALUES ('"
                            + id + "','" + name + "','" + age + "','" + sex + "','" + grade + "','" + school + "','" + classes + "')");
                    System.out.println("Successfully added " + name + "\n" +
                            "-------------------");
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
    public static void addStudentToStudentTableWithoutPromptingUser(int id, String name, int age, String sex, int grade, String school, String classes){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID +" = " + id);
            if(results.next() == false) {
                    studentStatement.execute("INSERT INTO " + STUDENT_TABLE_NAME + "(" + STUDENT_ID + "," + STUDENT_NAME + "," + STUDENT_AGE + "," +
                            STUDENT_SEX + "," + STUDENT_GRADE + "," + STUDENT_SCHOOL + "," + STUDENT_CLASSES + ") VALUES ('"
                            + id + "','" + name + "','" + age + "','" + sex + "','" + grade + "','" + school + "','" + classes + "')");
                    System.out.println("Successfully added " + name + "\n" +
                            "-------------------");

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void shortPrintAllStudentsInStudentTable(){
        System.out.println("Student List");
        System.out.println("-----------");
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT * FROM " + STUDENT_TABLE_NAME);
            while(results.next()){
                System.out.println(STUDENT_ID + ":" + results.getInt(STUDENT_ID)) ;
                System.out.println("Name:" + results.getString(STUDENT_NAME));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void printClasses(int studentId){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT " + STUDENT_CLASSES + " FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID + " = " + studentId);
            String classes = results.getString(STUDENT_CLASSES);
                System.out.println("Classes\n" +
                        "--------------------------");
                ArrayList<String> classesNums = new ArrayList<>();
                String classesString = "";
                for(int i = 0; i < classes.length(); i++){
                    if(classes.charAt(i) == ','){
                        classesNums.add(classesString);
                        classesString = "";
                    }else{
                        classesString+=classes.charAt(i);
                    }
                }
                if(classesNums.isEmpty()){
                    System.out.println(results.getString(STUDENT_NAME + " has no classes"));
                }else{
                    for(int i = 0; i < classesNums.size(); i++){
                        System.out.print("Class:");
                        System.out.println(ClassroomQueries.getClassroomName((Integer.parseInt(classesNums.get(i)))));
                    }
                }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Integer> getClasses(int studentId){
        ArrayList<Integer> result = new ArrayList<>();
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT " + STUDENT_CLASSES + " FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID + " = " + studentId);
            String classes = results.getString(STUDENT_CLASSES);
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
    public static void printStudentsInSchool(String school){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT *  FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_SCHOOL + " = '" + school + "'");
            while(results.next()){
                System.out.println(STUDENT_ID + ":" + results.getInt(STUDENT_ID)) ;
                System.out.println("Name:" + results.getString(STUDENT_NAME));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Integer> getStudentsInSchool(String school){
        ArrayList<Integer> result = new ArrayList<>();
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT *  FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_SCHOOL + " = '" + school + "'");
            while(results.next()){
                result.add(results.getInt(STUDENT_ID));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
   public static void printStudentsWithTeacher(String teacher){
       try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
           Statement studentStatement = studentConnection.createStatement()
       ){
           ResultSet results = studentStatement.executeQuery("SELECT * FROM " + STUDENT_TABLE_NAME);
           while(results.next()){
               ArrayList<Integer> studentClasses = getClasses(results.getInt(STUDENT_ID));
               for(int i = 0; i < studentClasses.size(); i++){
                   if(ClassroomQueries.getClassroomTeacher(studentClasses.get(i)).equals(teacher)){
                       System.out.println("Id:" + results.getInt(STUDENT_ID) + "\n" +
                               "Name:" + results.getString(STUDENT_NAME));
                   }
               }
           }
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
    }
    public static void printStudentsInClass(int id){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT * FROM " + STUDENT_TABLE_NAME);
            ArrayList<String> students = new ArrayList<>();
            while(results.next()){
                String classes = results.getString(STUDENT_CLASSES);
                String currId = "";
                for(int i = 0; i < classes.length(); i++){
                    if(classes.charAt(i) == ','){
                        if((Integer.toString(id).equals(currId))){
                            students.add(Integer.toString(results.getInt(STUDENT_ID)));
                            students.add(results.getString(STUDENT_NAME));
                        }
                        currId = "";
                    }else{
                        currId+= classes.charAt(i);
                    }
                }
            }
            if(students.isEmpty()){
                System.out.println("There are currently no students in this class");
            }else{
                for(int i = 0; i < students.size(); i+=2){
                    System.out.println("Id:" + students.get(i) + "\n" +
                            "Name:" + students.get(i+1));
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void removeStudent(int id){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID + " = " + id);
            if(results.next() == true){
                System.out.println("Are you sure you want to delete " + results.getString(STUDENT_NAME) + "?");
                if(UserSelectionDecider.getDecision()) {
                    System.out.println(results.getString(STUDENT_NAME) + " has been successfully deleted");
                    studentStatement.execute("DELETE FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID + " = " + id);
                }else{
                    System.out.println(results.getString(STUDENT_NAME) + " has not been deleted");
                }
            }else{
                System.out.println("Error. School doesn't exist");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void removeStudentWithoutPromptingUser(int id){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
        ){
            ResultSet results = studentStatement.executeQuery("SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID + " = " + id);
            if(results.next() == true){
                    System.out.println(results.getString(STUDENT_NAME) + " has been successfully deleted");
                    studentStatement.execute("DELETE FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID + " = " + id);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void removeClassroomFromStudentWithTeacher(String teacher){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
            Statement secondStatement = studentConnection.createStatement();
            Statement thirdStatement = studentConnection.createStatement()
        ){
            ResultSet results = studentStatement.executeQuery("SELECT * FROM " + STUDENT_TABLE_NAME);
            while(results.next()){
                ArrayList<Integer> studentClasses = getClasses(results.getInt(STUDENT_ID));
                for(int i = 0; i < studentClasses.size(); i++){
                    if(ClassroomQueries.getClassroomTeacher(studentClasses.get(i)).equals(teacher)){
                        System.out.println("Removed " + teacher + " from " + results.getString(STUDENT_NAME));
                        studentClasses.remove(i);
                        String result = "";
                        for(int j = 0; j < studentClasses.size(); j++){
                            result+=studentClasses.get(j);
                            result+= ",";
                        }
                        secondStatement.execute("UPDATE " + STUDENT_TABLE_NAME + " SET " + STUDENT_CLASSES + " = '" + result + "' WHERE " + STUDENT_ID + " = " + results.getInt(STUDENT_ID));
                        if(studentClasses.size() == 0){
                            System.out.println(results.getString(STUDENT_NAME) + " has no more classes, would you like to delete them?");
                            if(UserSelectionDecider.getDecision()){
                                System.out.println(results.getString(STUDENT_NAME) + " has been successfully deleted");
                                thirdStatement.execute("DELETE FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID + " = " + results.getInt(STUDENT_ID));
                            }else{
                                System.out.println(results.getString(STUDENT_NAME) + " has not been deleted");
                            }
                        }
                    }
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void removeClassroomFromStudent(int id,String classroomName){
        try(Connection studentConnection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
            Statement studentStatement = studentConnection.createStatement();
            Statement secondStatement = studentConnection.createStatement();
            Statement thirdStatement = studentConnection.createStatement()
        ){
            ResultSet results = studentStatement.executeQuery("SELECT * FROM " + STUDENT_TABLE_NAME);
            while(results.next()){
                ArrayList<Integer> studentClasses = getClasses(results.getInt(STUDENT_ID));
                for(int i = 0; i < studentClasses.size(); i++){
                    if(studentClasses.get(i) == id){
                        System.out.println("Removed " + classroomName + " from " + results.getString(STUDENT_NAME));
                        studentClasses.remove(i);
                        String result = "";
                        for(int j = 0; j < studentClasses.size(); j++){
                            result+=studentClasses.get(j);
                            result+= ",";
                        }
                        secondStatement.execute("UPDATE " + STUDENT_TABLE_NAME + " SET " + STUDENT_CLASSES + " = '" + result + "' WHERE " + STUDENT_ID + " = " + results.getInt(STUDENT_ID));
                        if(studentClasses.size() == 0){
                            System.out.println(results.getString(STUDENT_NAME) + " has no more classes, would you like to delete them?");
                            if(UserSelectionDecider.getDecision()){
                                System.out.println(results.getString(STUDENT_NAME) + " has been successfully deleted");
                                thirdStatement.execute("DELETE FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_ID + " = " + results.getInt(STUDENT_ID));
                            }else{
                                System.out.println(results.getString(STUDENT_NAME) + " has not been deleted");
                            }
                        }
                    }
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
