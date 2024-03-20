/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Technician;

import com.mycompany.labappointment.service.resources.Db.DBUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shakyapa
 */
public class TechnicianRepo {
    private final DBUtils dbConn;

    public TechnicianRepo() {
        dbConn = new DBUtils();
    }

    public boolean addTechnician(Technician technician) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "INSERT INTO Technicians (FirstName, LastName, ContactNumber, Email, UserName, Password) VALUES (?, ?, ?, ?, ?, ?);",
                            Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, technician.getFirstName());
                stmt.setString(2, technician.getLastName());
                stmt.setString(3, technician.getContactNumber());
                stmt.setString(4, technician.getEmail());
                stmt.setString(5, technician.getUserName());
                stmt.setString(6, technician.getPassword());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    technician.setTechnicianID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Technician getTechnicianByID(int technicianID) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Technicians WHERE TechnicianID = ?")) {
                stmt.setInt(1, technicianID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Technician technician = new Technician();
                    technician.setTechnicianID(rs.getInt("TechnicianID"));
                    technician.setFirstName(rs.getString("FirstName"));
                    technician.setLastName(rs.getString("LastName"));
                    technician.setContactNumber(rs.getString("ContactNumber"));
                    technician.setEmail(rs.getString("Email"));
                    technician.setUserName(rs.getString("UserName"));
                    technician.setPassword(rs.getString("Password"));
                    return technician;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateTechnician(Technician technician) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "UPDATE Technicians SET FirstName = ?, LastName = ?, ContactNumber = ?, Email = ?, UserName = ?, Password = ? WHERE TechnicianID = ?")) {
                stmt.setString(1, technician.getFirstName());
                stmt.setString(2, technician.getLastName());
                stmt.setString(3, technician.getContactNumber());
                stmt.setString(4, technician.getEmail());
                stmt.setString(5, technician.getUserName());
                stmt.setString(6, technician.getPassword());
                stmt.setInt(7, technician.getTechnicianID());
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTechnician(int technicianID) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM Technicians WHERE TechnicianID = ?")) {
                stmt.setInt(1, technicianID);
                int rowsDeleted = stmt.executeUpdate();
                return rowsDeleted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Technician> getAllTechnicians() {
        List<Technician> technicians = new ArrayList<>();
        try {
            try (Connection conn = dbConn.getConnection(); 
                    Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Technicians");
                while (rs.next()) {
                    Technician technician = new Technician();
                    technician.setTechnicianID(rs.getInt("TechnicianID"));
                    technician.setFirstName(rs.getString("FirstName"));
                    technician.setLastName(rs.getString("LastName"));
                    technician.setContactNumber(rs.getString("ContactNumber"));
                    technician.setEmail(rs.getString("Email"));
                    technician.setUserName(rs.getString("UserName"));
                    technician.setPassword(rs.getString("Password"));
                    technicians.add(technician);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return technicians;
    }
    
    public boolean login(String userName, String password) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                    "SELECT UserName, Password FROM Patients WHERE UserName = ? AND Password = ?")) {
                stmt.setString(1, userName);
                
                String encryptedPassword = encryptPassword(password);
                stmt.setString(2, encryptedPassword);
                
                //stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next(); // If there is a matching record, return true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
}
}

