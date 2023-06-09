/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.medata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Neo
 */
public class createSchedule {
    
        public static void processInput(String firstName, String lastName, String date, String time, int doctorsID, String patientUsername) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medata", "root", "");
            String sql = "INSERT INTO schedule (firstName, lastName, date, time, doctorsID, patientUsername ) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, date);
            statement.setString(4, time);
            statement.setInt(5, doctorsID);
            statement.setString(6, patientUsername);
  
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
