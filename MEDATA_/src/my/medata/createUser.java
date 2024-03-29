package my.medata;

import java.sql.*;

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
 */
public class createUser {

    public static void processInput(String lastName, String firstName, String middleName, int age, String dateOfBirth, String address, String contact, String email, String sex, String civilStatus, int height, int weight, String username, String password, String role, Boolean isActivated, int doctorID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medata", "root", "");
            String sql = "INSERT INTO userinfo (lastName, firstName, middleName, age, dateOfBirth, address, contact, email, sex, civilStatus, height, weight, username, password, role, isActivated, doctorID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            statement.setString(3, middleName);
            statement.setInt(4, age);
            statement.setString(5, dateOfBirth);
            statement.setString(6, address);
            statement.setString(7, contact);
            statement.setString(8, email);
            statement.setString(9, sex);
            statement.setString(10, civilStatus);
            statement.setInt(11, height);
            statement.setInt(12, weight);
            statement.setString(13, username);
            statement.setString(14, password);
            statement.setString(15, role);
            statement.setBoolean(16, isActivated);
             statement.setInt(17, doctorID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");

            } else {
                System.out.println("Failed to insert data.");
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
