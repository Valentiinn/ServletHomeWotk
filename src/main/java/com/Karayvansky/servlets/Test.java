package com.Karayvansky.servlets;
import java.io.IOException;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws IOException, SQLException {

        University bd = University.getInstance();

        System.out.println("Get students from BD:");
        for (Student student : bd.getStudents()) {
            System.out.print(student.getLastname() + " / ");
            System.out.print(student.getFirstname() + " / ");
            System.out.print(student.getAge() + "\n");
        }

    }
}