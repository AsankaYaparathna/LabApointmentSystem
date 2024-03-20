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

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "root";

    public DBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading JDBC driver: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            return null;
        }
    }
}