package com.Karayvansky.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class University {
    private Connection connection;
    private static University instance;

    private University() {
        try {
            isConnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static University getInstance() {
        if (instance == null)
            instance = new University();
        return instance;
    }

    private boolean isConnect() throws IOException, SQLException {
       // Properties properties = loadProperties();

        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tictactoe?serverTimezone=UTC&useSSL=false", "root", "123456");

        return true;
    }

//    private Properties loadProperties() throws IOException {
//        Properties properties = new Properties();
//        InputStream stream = getClass().getResourceAsStream("db.properties");
//        properties.load(stream);
//        return properties;
//    }

    public List<Student> getStudents() throws SQLException {
        String sql = "SELECT lastname, firstname, age FROM students";
        Statement statement = connection.createStatement();
        statement.execute(sql);

        ResultSet resultSet = statement.getResultSet();
        List<Student> students = new ArrayList<Student>();

        while (resultSet.next()) {

            String lastname = resultSet.getString("lastname");
            String firstname = resultSet.getString("firstname");
            int age = resultSet.getInt("age");

            students.add(new Student(lastname, firstname, age));
        }

        return students;
    }
}

