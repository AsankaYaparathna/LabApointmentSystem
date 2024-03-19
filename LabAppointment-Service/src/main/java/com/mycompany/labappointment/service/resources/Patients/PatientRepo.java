/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Patients;

import com.mycompany.labappointment.service.resources.Db.DBUtils;
import com.mycompany.labappointment.service.resources.Patients.Patient.Gender;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shakyapa
 */
public class PatientRepo {
    DBUtils dbConn;
    
    public PatientRepo(){
        try{
           dbConn = new DBUtils();
        }
        catch(Exception ex){}
    }
    
    public boolean addPatient(Patient patient) {
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Patients (FirstName, LastName, DateOfBirth, Gender, ContactNumber, Email, Address, UserName, Password) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
                stmt.setString(1, patient.getFirstName());
                stmt.setString(2, patient.getLastName());
                stmt.setDate(3, java.sql.Date.valueOf(patient.getDateOfBirth()));
                stmt.setString(4, patient.getGender().toString());
                stmt.setString(5, patient.getContactNumber());
                stmt.setString(6, patient.getEmail());
                stmt.setString(7, patient.getAddress());
                stmt.setString(8, patient.getUserName());
                stmt.setString(9, patient.getPassword());
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
    
    public Patient getPatientByID(int patientID) {
        try {
            try (Connection conn = dbConn.GetConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Patients WHERE PatientID = ?")) {
                stmt.setInt(1, patientID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Patient patient = new Patient();
                    patient.setPatientID(rs.getInt("PatientID"));
                    patient.setFirstName(rs.getString("FirstName"));
                    patient.setLastName(rs.getString("LastName"));
                    patient.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
                    patient.setGender(Gender.valueOf(rs.getString("Gender")));
                    patient.setContactNumber(rs.getString("ContactNumber"));
                    patient.setEmail(rs.getString("Email"));
                    patient.setAddress(rs.getString("Address"));
                    patient.setUserName(rs.getString("UserName"));
                    patient.setPassword(rs.getString("Password"));
                    return patient;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updatePatient(Patient patient) {
        try {
            try (Connection conn = dbConn.GetConnection(); PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE Patients SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, ContactNumber = ?, Email = ?, Address = ?, UserName = ?, Password = ? WHERE PatientID = ?;")) {
                stmt.setString(1, patient.getFirstName());
                stmt.setString(2, patient.getLastName());
                stmt.setDate(3, java.sql.Date.valueOf(patient.getDateOfBirth()));
                stmt.setString(4, patient.getGender().toString());
                stmt.setString(5, patient.getContactNumber());
                stmt.setString(6, patient.getEmail());
                stmt.setString(7, patient.getAddress());
                stmt.setString(8, patient.getUserName());
                stmt.setString(9, patient.getPassword());
                stmt.setInt(10, patient.getPatientID());
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

    public boolean deletePatient(int patientID) {
        try {
            try (Connection conn = dbConn.GetConnection(); PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM Patients WHERE PatientID = ?")) {
                stmt.setInt(1, patientID);
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

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Patients");
                while (rs.next()) {
                    Patient patient = new Patient();
                    patient.setPatientID(rs.getInt("PatientID"));
                    patient.setFirstName(rs.getString("FirstName"));
                    patient.setLastName(rs.getString("LastName"));
                    patient.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
                    patient.setGender(Gender.valueOf(rs.getString("Gender")));
                    patient.setContactNumber(rs.getString("ContactNumber"));
                    patient.setEmail(rs.getString("Email"));
                    patient.setAddress(rs.getString("Address"));
                    patient.setUserName(rs.getString("UserName"));
                    patient.setPassword(rs.getString("Password"));
                    patients.add(patient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public boolean login(String userName, String password) {
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                    "SELECT UserName, Password FROM Patients WHERE UserName = ? AND Password = ?")) {
                stmt.setString(1, userName);
                stmt.setString(2, password);
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

}
