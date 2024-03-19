/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author shakyapa
 */
public class DBUtils {
    static final String DB_URL = "jdbc:mysql://localhost:3306/lab_appointment_system";
    static final String USER = "root";
    static final String PASS = "root";
    
    public DBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    public Connection GetConnection(){
       try {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
                return conn;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }
}
