package com.company;


import javax.sql.DataSource;

import java.sql.*;
public class Main {
    public static void main(String[] args) {
        SchoolQueries.createSchoolTableIfNotExist();
        TeacherQueries.createTeacherTableIfNotExist();
        ClassroomQueries.createClassroomTableIfNotExist();
        StudentQueries.createStudentsTableIfNotExist();
        UserSelectionDecider.addDefaultInformation();
        //starts the program and continues running it
        GuiMessages.printNotice();
        GuiMessages.printWelcomeMessage();
        GuiMessages.printMainSelectionScreen();
        int firstChoice = UserSelectionDecider.mainScreenPrompter(UserSelectionDecider.selectionScreensIntInput(0,4));
        UserSelectionDecider.secondScreenPrompter(firstChoice);
    }
}
