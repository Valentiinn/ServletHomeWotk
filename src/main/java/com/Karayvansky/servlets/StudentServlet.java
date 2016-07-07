package com.Karayvansky.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");

        writer.println(getStudentsTable());

        writer.println("</body>");
        writer.println("</html>");
    }

    private String getStudentsTable() {

        String str = "";

        try {
            University bd = University.getInstance();
            List<Student> students = bd.getStudents();

            str += "<table width=100% height=\"200px\" border=1>\n";

            str += "<tr>\n";
            str += "<td>Lastname</td>\n";
            str += "<td>Firstname</td>\n";
            str += "<td>Age</td>\n";
            str += "</tr>\n";

            for (Student student : students) {
                str += "<tr>\n";
                str += "<td>" + student.getLastname() + "</td>\n";
                str += "<td>" + student.getFirstname() + "</td>\n";
                str += "<td>" + student.getAge() + "</td>\n";
                str += "</tr>\n";
            }

            str += "</table>\n";

        } catch (SQLException e) {
            str = "<p>Error</p>";
        }

        return str;
    }

}