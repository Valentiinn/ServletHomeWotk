package com.Karayvansky.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class University {

    static final String URL = "jdbc:mysql://localhost:3306/university?serverTimezone=UTC&useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "123456";

    private Connection connection;
    private static University instance;

    private University() {
        try {
            isConnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static University getInstance() {
        if (instance == null)
            instance = new University();
        return instance;
    }

    private boolean isConnect() throws IOException, SQLException, ClassNotFoundException {
        Properties properties = loadProperties();
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
//        connection = DriverManager.
//                getConnection(properties.getProperty("url"),
//                        properties.getProperty("username"),
//                        properties.getProperty("password"));

        return true;
    }

    private Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream stream = getClass().getResourceAsStream("db.properties");
        properties.load(stream);
        return properties;
    }

    public List<Student> getStudents() throws SQLException {
        String sql = "SELECT lastname, firstname, age FROM students;";
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

