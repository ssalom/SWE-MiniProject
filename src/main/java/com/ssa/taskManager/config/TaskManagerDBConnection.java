package com.ssa.taskManager.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TaskManagerDBConnection {
    private static final String PROTOCOL = "jdbc:mysql";
    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "taskManager";
    private static final String OPTIONS = "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String URL = PROTOCOL + "://" + HOST + ":" + PORT + "/" + DATABASE + "?" + OPTIONS;
    private static final String USER = "application";
    private static final String PASSWORD = "1234";

    public static Connection openConnection () {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static void closeConnection (Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
