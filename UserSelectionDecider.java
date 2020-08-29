package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserSelectionDecider {

    public static void addDefaultInformation(){
            SchoolQueries.addToSchoolToSchoolTableWithoutPromptingUser(7, "Hogwarts School of Witchcraft and Wizardry", "Hogwarts Castle", "Highlands", "Scotlands", "Great Britain");
            ClassroomQueries.addClassroomToClassroomTableWithoutPromptingUser(1, "Transfiguration", "Minerva McGonagall", "Hogwarts School of Witchcraft and Wizardry");
            ClassroomQueries.addClassroomToClassroomTableWithoutPromptingUser(2, "Charms", "Filius Flitwick", "Hogwarts School of Witchcraft and Wizardry");
            ClassroomQueries.addClassroomToClassroomTableWithoutPromptingUser(3, "Potions", "Severus Snape", "Hogwarts School of Witchcraft and Wizardry");
            TeacherQueries.removeTeacherWithoutPromptingUser(1);
            TeacherQueries.removeTeacherWithoutPromptingUser(2);
            TeacherQueries.removeTeacherWithoutPromptingUser(3);
            StudentQueries.removeStudentWithoutPromptingUser(1);
            StudentQueries.removeStudentWithoutPromptingUser(2);
            StudentQueries.removeStudentWithoutPromptingUser(3);
            TeacherQueries.addTeacherToTeacherTableWithoutPromptingUser(1, "Minerva McGonagall", "Hogwarts School of Witchcraft and Wizardry", "1,");
            TeacherQueries.addTeacherToTeacherTableWithoutPromptingUser(2, "Filius Flitwick", "Hogwarts School of Witchcraft and Wizardry", "2,");
            TeacherQueries.addTeacherToTeacherTableWithoutPromptingUser(3, "Severus Snape", "Hogwarts School of Witchcraft and Wizardry", "3,");
            StudentQueries.addStudentToStudentTableWithoutPromptingUser(1, "Harry Potter", 11, "Male", 1, "Hogwarts School of Witchcraft and Wizardry", "1,2,3,");
            StudentQueries.addStudentToStudentTableWithoutPromptingUser(2, "Hermione Granger", 11, "Female", 1, "Hogwarts School of Witchcraft and Wizardry", "1,2,3,");
            StudentQueries.addStudentToStudentTableWithoutPromptingUser(3, "Ron Weasley", 11, "Male", 1, "Hogwarts School of Witchcraft and Wizardry", "1,2,3,");
            makeSpace();
    }
    public static int getIntegerInput(){
        Scanner scanner = new Scanner(System.in);
        while(!scanner.hasNextInt()){
            System.out.println("Incorrect input,try again\n" +
                    "Enter a number:");
            scanner.next();
        }
        return scanner.nextInt();
    }
    public static String getStringInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    /*will decide what to choose on selection screens or in decisions depending on the user input
    within the upper bound/lower bound
     */
    public static int selectionScreensIntInput(int lowerBound,int upperBound){
        System.out.println("Enter a number:");
        int num = 0;
        //while they don't input a valid choice(lower bound-upper bound
        while(num == 0) {
            try {
                int input = getIntegerInput();
                if (input >= lowerBound && input <= upperBound) {
                    num = input;
                }else{
                    System.out.println("That is not a valid number, please try again\n" +
                            "Enter a number:");
                }
            } catch (InputMismatchException e) {
                System.out.println("That is not a valid number, please try again\n" +
                        "Enter a number:");
            }
        }
        return num;
    }
    //first main screen they see (schools,teachers,classrooms,students)
    public static int mainScreenPrompter(int choice){
        makeSpace();
        switch (choice){
            case 1:
                System.out.println("You have chosen schools\n" +
                        "-----------------------");
                GuiMessages.printSchoolSelectionScreen();
                return 1;
            case 2:
                System.out.println("You have chosen teachers\n" +
                        "------------------------");
                GuiMessages.printTeacherSelectionScreen();
                return 2;
            case 3:
                System.out.println("You have chosen classrooms\n" +
                        "--------------------------");
                GuiMessages.printClassroomSelectionScreen();
                return 3;
            case 4:
                System.out.println("You have chosen students\n" +
                        "------------------------");
                GuiMessages.printStudentSelectionScreen();
                return 4;
            default:
                System.out.println("Incorrect choice\n" +
                        "----------------");
                return -1;
        }
    }
    //will prompt the users decision in the second screen they see
    public static int secondScreenPrompter(int choice){
        switch (choice){
            //school
            case 1:
                int a = selectionScreensIntInput(1,8);
                schoolScreenPrompter(a);
                return 1;
            case 2:
                //teacher
                int b = selectionScreensIntInput(1,7);
                teacherScreenPrompter(b);
                return 2;
            case 3:
                int c = selectionScreensIntInput(1,6);
                classroomScreenPrompter(c);
                return 3;
            case 4:
                int d = selectionScreensIntInput(1,5);
                studentsScreenPrompter(d);
                return 4;
            default:
                return -1;
        }
    }
    //screen after they choose schools®
    public static void schoolScreenPrompter(int choice){
        makeSpace();
        switch (choice){
            case 1:
                System.out.println("You have chosen to add a school\n" +
                        "-------------------------------");
                SchoolImplementations.addSchool();
                break;
            case 2:
                System.out.println("You have chosen to get a list of schools\n" +
                        "----------------------------------------");
                SchoolImplementations.getListOfSchools();
                break;
            case 3:
                System.out.println("You have chosen to get information about a school\n" +
                        "-------------------------------------------------");
                SchoolImplementations.getInformationAboutSchool();
                break;
            case 4:
                System.out.println("You have chosen to get teachers in a school\n" +
                        "-------------------------------------------");
                SchoolImplementations.getTeachersInSchool();
               break;
            case 5:
                System.out.println("You have chosen to get classrooms in a school\n" +
                        "---------------------------------------------");
                SchoolImplementations.getClassroomsInSchool();
                break;
            case 6:
                System.out.println("You have chosen to get students in a school\n" +
                        "-------------------------------------------");
                SchoolImplementations.getStudentsInSchool();
                break;
            case 7:
                System.out.println("You have chosen to remove a school\n" +
                        "-------------------------------------------");
                SchoolImplementations.removeSchool();
                break;
            case 8:
                System.out.println("You have chosen to exit\n"+
                        "-----------------------");
                exitToMainScreen();
                break;
            default:
                System.out.println("Incorrect choice\n" +
                        "----------------");
                break;
        }
        pressEnter();
        makeSpace();
        System.out.println("------------------------");
        GuiMessages.printSchoolSelectionScreen();
        secondScreenPrompter(1);
    }
    //screen after they choose teachers
    public static void teacherScreenPrompter(int choice){
        makeSpace();
        switch (choice){
            case 1:
                System.out.println("You have chosen to add a teacher\n" +
                        "--------------------------------");
                TeacherImplementations.addTeacher();
                break;
            case 2:
                System.out.println("You have chosen to get a list of teachers\n" +
                        "-----------------------------------------");
                TeacherImplementations.getListOfTeachers();
                break;
            case 3:
                System.out.println("You have chosen to get information about a teacher\n" +
                        "--------------------------------------------------");
                TeacherImplementations.getInformationAboutTeacher();
                break;
            case 4:
                System.out.println("You have chosen to get a teachers classrooms\n" +
                        "--------------------------------------------");
                TeacherImplementations.getTeachersClassrooms();
                break;
            case 5:
                System.out.println("You have chosen to get a teachers students\n" +
                        "------------------------------------------");
                TeacherImplementations.getTeachersStudents();
                break;
            case 6:
                System.out.println("You have chosen to remove a teacher\n" +
                        "----------------------------------");
                TeacherImplementations.removeTeacher();
                break;
            case 7:
                System.out.println("You have chosen to exit\n"+
                        "-----------------------");
                exitToMainScreen();
                break;
            default:
                System.out.println("Incorrect choice\n" +
                        "----------------");
                break;
        }
        pressEnter();
        makeSpace();
        System.out.println("------------------------");
        GuiMessages.printTeacherSelectionScreen();
        secondScreenPrompter(2);
    }
    //screen after they choose classrooms
    public static void classroomScreenPrompter(int choice){
        makeSpace();
        switch (choice){
            case 1:
                System.out.println("You have chosen to add a classroom\n" +
                        "----------------------------------");
                ClassroomImplementations.addClassroom();
                break;
            case 2:
                System.out.println("You have chosen to get a list of classrooms\n"+
                        "-------------------------------------------");
                ClassroomImplementations.getListOfClassrooms();
                break;
            case 3:
                System.out.println("You have chosen to get the teacher which teaches a class\n" +
                        "--------------------------------------------------------");
                ClassroomImplementations.printTeacherTeachingClass();
                break;
            case 4:
                System.out.println("You have chosen to get students in a class\n" +
                        "------------------------------------------");
                ClassroomImplementations.getStudentsInClass();
                break;
            case 5:
                System.out.println("You have chosen to remove a classroom\n" +
                        "------------------------------------");
                ClassroomImplementations.removeClassroom();
                break;
            case 6:
                System.out.println("You have chosen to exit\n"+
                        "-----------------------");
                exitToMainScreen();
                break;
            default:
                System.out.println("Incorrect choice\n" +
                        "----------------");
                break;
        }
        pressEnter();
        makeSpace();
        System.out.println("------------------------");
        GuiMessages.printClassroomSelectionScreen();
        secondScreenPrompter(3);
    }
    //screen after they choose students
    public static void studentsScreenPrompter(int choice){
        makeSpace();
        switch (choice){
            case 1:
                System.out.println("You have chosen to add a student\n" +
                        "--------------------------------");
                StudentImplementations.addStudent();
                break;
            case 2:
                System.out.println("You have chosen to get a list of students\n" +
                        "-----------------------------------------");
                StudentImplementations.getListOfStudents();
                break;
            case 3:
                System.out.println("You have chosen to get information about a student\n" +
                        "--------------------------------------------------");
                StudentImplementations.getInformationAboutStudent();
                break;
            case 4:
                System.out.println("You have chosen to remove a student\n" +
                        "---------------------------------");
                StudentImplementations.removeStudent();
                break;
            case 5:
                System.out.println("You have chosen to exit\n"+
                        "-----------------------");
                exitToMainScreen();
                break;
            default:
                System.out.println("Incorrect choice\n" +
                        "----------------");
                break;
        }
        pressEnter();
        makeSpace();
        System.out.println("------------------------");
        GuiMessages.printStudentSelectionScreen();
        secondScreenPrompter(4);

    }
    public static void exitToMainScreen(){
        GuiMessages.printMainSelectionScreen();
        int selectionScreenInput = selectionScreensIntInput(1,4);
        mainScreenPrompter(selectionScreenInput);
        secondScreenPrompter(selectionScreenInput);
    }
    public static void pressEnter(){
        while(true){
            System.out.println("Please press enter to continue");
            if(getStringInput().isEmpty()){
                break;
            }else{
                System.out.println("Not correct key, please try again");
            }
        }
    }
    public static void makeSpace(){
        for(int i = 0; i < 30; i++){
            System.out.println();
        }
    }
    public static boolean getDecision(){
        System.out.println("Enter (yes/no)");
        while(true){
            String input = UserSelectionDecider.getStringInput().toLowerCase();
            if(input.equals("yes")){
                return true;
            }else if(input.equals("no")){
                return false;
            }else{
                System.out.println("Please try again (yes/no)");
            }
        }
    }
    public static String getSchoolNameToAddToDatabase(){
        makeSpace();
        System.out.println("Enter the school's id \n" +
                "Or enter -1 if you would like to add a new entry");
        String school = "";
        SchoolQueries.shortPrintAllSchoolsInSchoolTable();
        int decision = UserSelectionDecider.getIntegerInput();
        if(decision == -1){
            while(true) {
                System.out.println("Enter the new school's id:");
                int newSchoolId = UserSelectionDecider.getIntegerInput();
                if (SchoolQueries.alreadyExists(newSchoolId)) {
                    System.out.println("This school id already exists, would you like to add the school already in the database?");
                    if(UserSelectionDecider.getDecision()){
                        school = SchoolQueries.getSchoolName(newSchoolId);
                        break;
                    }else{
                        System.out.println("Try entering the school's id once again");
                    }
                }else{
                    System.out.println("Enter the school's name,address, city, state, and country");
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
                    SchoolQueries.addToSchoolToSchoolTableWithoutPromptingUser(newSchoolId,name,address,city,state,country);
                    school = name;
                    break;
                }
            }
        }else{
            //make sure that they can't enter a letter
            while(true) {
                school = SchoolQueries.getSchoolName(decision);
                if(school != ""){
                    break;
                }else{
                    System.out.println("Not correct school, try again\n" +
                            "Enter school id:");
                    decision = UserSelectionDecider.getIntegerInput();
                }
            }
        }
        return school;
    }
    public static String getClassesToAddToDatabase(String school){
        makeSpace();
        System.out.println("Enter the number of classes they have:");
        int amountOfClasses = UserSelectionDecider.selectionScreensIntInput(1,100);
        ArrayList<Integer> currentlyAddedClasses = new ArrayList<>(amountOfClasses);
        String classes = "";
        for(int i = 1; i <= amountOfClasses; i++){
            makeSpace();
            System.out.println("Enter the id of class #" + i + " :" );
            ClassroomQueries.printClassrooms();
            System.out.println("Enter the classroom's id \n" +
                    "Or enter -1 if you would like to add a new entry");
            int classroomDecision = UserSelectionDecider.getIntegerInput();
            if(classroomDecision == -1){
                while(true) {
                    System.out.println("Enter the new classroom's id:");
                    int newClassroomId = UserSelectionDecider.getIntegerInput();
                    boolean teacherAlreadyHasClass = false;
                    for(int j = 0; j < currentlyAddedClasses.size(); j++){
                        if(newClassroomId == currentlyAddedClasses.get(j)){
                            teacherAlreadyHasClass = true;
                            break;
                        }
                    }
                    if(!teacherAlreadyHasClass) {
                        if (ClassroomQueries.alreadyExists(newClassroomId)) {
                            System.out.println("This classroom id already exists, would you like to add the classroom already in the database?");
                            if (UserSelectionDecider.getDecision()) {
                                currentlyAddedClasses.add(newClassroomId);
                                classes += newClassroomId;
                                classes += ',';
                                System.out.println("Added " + ClassroomQueries.getClassroomName(newClassroomId));
                                break;
                            } else {
                                System.out.println("Try entering the classroom's id once again");
                            }
                        } else {
                            System.out.println("Enter the new classroom's name,teacher, and school");
                            System.out.println("Name:");
                            String name = UserSelectionDecider.getStringInput();
                            System.out.println("Teacher");
                            String teacher = getTeacherNameToAddToDatabase(school);
                            currentlyAddedClasses.add(newClassroomId);
                            classes+=newClassroomId;
                            classes+=',';
                            ClassroomQueries.addClassroomToClassroomTableWithoutPromptingUser(newClassroomId, name, teacher, school);
                            break;
                        }
                    }else{
                        System.out.println("The teacher already has this class\n" +
                                "Try again\n" +
                                "Id:");
                    }
                }
            }else{
                //make sure that they can't enter a letter
                String classroomName = "";
                while(true) {
                    classroomName = ClassroomQueries.getClassroomName(classroomDecision);
                    boolean teacherAlreadyHasClass = false;
                    for(int j = 0; j < currentlyAddedClasses.size(); j++){
                        if(classroomDecision == currentlyAddedClasses.get(j)){
                            teacherAlreadyHasClass = true;
                            break;
                        }
                    }
                    if(!teacherAlreadyHasClass) {
                        if (classroomName != "") {
                            currentlyAddedClasses.add(classroomDecision);
                            classes += classroomDecision;
                            classes += ',';
                            break;
                        } else {
                            System.out.println("Not correct classroom, try again\n" +
                                    "Enter classroom id:");
                            classroomDecision = UserSelectionDecider.getIntegerInput();
                        }
                    }else{
                        System.out.println("The teacher already has this class\n"+
                                "Try again\n" +
                                "Id:");
                        classroomDecision = UserSelectionDecider.getIntegerInput();
                    }
                }
            }
        }
        return classes;
    }
    public static String getTeacherNameToAddToDatabase(String school){
        makeSpace();
        String teacher = "";
        TeacherQueries.shortPrintAllTeachersInTeachersTable();
        System.out.println("Enter the teacher's id \n" +
                "Or enter -1 if you would like to add a new entry");
        int teacherDecision = UserSelectionDecider.getIntegerInput();
        if(teacherDecision == -1){
            while(true) {
                System.out.println("Enter the new teacher's id:");
                int newTeacherId = UserSelectionDecider.getIntegerInput();
                if (TeacherQueries.alreadyExists(newTeacherId)) {
                    System.out.println("This teacher id already exists, would you like to add the teacher already in the database?");
                    if(UserSelectionDecider.getDecision()){
                        teacher = TeacherQueries.getTeacherName(newTeacherId);
                        break;
                    }else{
                        System.out.println("Try entering the teacher's id once again");
                    }
                }else{
                    System.out.println("Enter the new teacher's name,school, and classes");
                    System.out.println("Name:");
                    String name = UserSelectionDecider.getStringInput();
                    System.out.println("School");
                    String classes = getClassesToAddToDatabase(school);
                    TeacherQueries.addTeacherToTeacherTableWithoutPromptingUser(newTeacherId,name,school,classes);
                    break;
                }
            }
        }else{
            //make sure that they can't enter a letter
            while(true) {
                teacher = TeacherQueries.getTeacherName(teacherDecision);
                if(teacher != ""){
                    break;
                }else{
                    System.out.println("Not correct teacher, try again\n" +
                            "Enter teacher id:");
                    teacherDecision = UserSelectionDecider.getIntegerInput();
                }
            }
        }
        return teacher;
    }
}
