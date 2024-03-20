/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Doctor;

import com.mycompany.labappointment.service.resources.Db.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author asank
 */
public class DoctorRepo {
    private final DBUtils dbConn;

    public DoctorRepo() {
        dbConn = new DBUtils();
    }

    public boolean addDoctor(Doctor doctor) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "INSERT INTO Doctors (FirstName, LastName, ContactNumber, Email) VALUES (?, ?, ?, ?);")) {
                stmt.setString(1, doctor.getFirstName());
                stmt.setString(2, doctor.getLastName());
                stmt.setString(3, doctor.getContactNumber());
                stmt.setString(4, doctor.getEmail());
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Doctor getDoctorByID(int doctorID) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Doctors WHERE DoctorID = ?")) {
                stmt.setInt(1, doctorID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Doctor doctor = new Doctor();
                    doctor.setDoctorID(rs.getInt("DoctorID"));
                    doctor.setFirstName(rs.getString("FirstName"));
                    doctor.setLastName(rs.getString("LastName"));
                    doctor.setContactNumber(rs.getString("ContactNumber"));
                    doctor.setEmail(rs.getString("Email"));
                    return doctor;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateDoctor(Doctor doctor) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "UPDATE Doctors SET FirstName = ?, LastName = ?, ContactNumber = ?, Email = ? WHERE DoctorID = ?;")) {
                stmt.setString(1, doctor.getFirstName());
                stmt.setString(2, doctor.getLastName());
                stmt.setString(3, doctor.getContactNumber());
                stmt.setString(4, doctor.getEmail());
                stmt.setInt(5, doctor.getDoctorID());
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

    public boolean deleteDoctor(int doctorID) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM Doctors WHERE DoctorID = ?")) {
                stmt.setInt(1, doctorID);
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

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            try (Connection conn = dbConn.getConnection(); 
                    Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Doctors");
                while (rs.next()) {
                    Doctor doctor = new Doctor();
                    doctor.setDoctorID(rs.getInt("DoctorID"));
                    doctor.setFirstName(rs.getString("FirstName"));
                    doctor.setLastName(rs.getString("LastName"));
                    doctor.setContactNumber(rs.getString("ContactNumber"));
                    doctor.setEmail(rs.getString("Email"));
                    doctors.add(doctor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
