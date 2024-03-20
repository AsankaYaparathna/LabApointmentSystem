/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Appointment;

/**
 *
 * @author shakyapa
 */
import com.mycompany.labappointment.service.resources.Db.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepo {
    private final DBUtils dbConn;

    public AppointmentRepo() {
        dbConn = new DBUtils();
    }

    public boolean addAppointment(Appointment appointment) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "INSERT INTO Appointments (PatientID, TestID, TechnicianID, DoctorID, AppointmentDateTime) VALUES (?, ?, ?, ?, ?);",
                            Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, appointment.getPatientID());
                stmt.setInt(2, appointment.getTestID());
                stmt.setInt(3, appointment.getTechnicianID());
                stmt.setInt(4, appointment.getDoctorID());
                stmt.setObject(5, appointment.getAppointmentDateTime());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    appointment.setAppointmentID(rs.getInt(1));
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

    public Appointment getAppointmentByID(int appointmentID) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appointments WHERE AppointmentID = ?")) {
                stmt.setInt(1, appointmentID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setAppointmentID(rs.getInt("AppointmentID"));
                    appointment.setPatientID(rs.getInt("PatientID"));
                    appointment.setTestID(rs.getInt("TestID"));
                    appointment.setTechnicianID(rs.getInt("TechnicianID"));
                    appointment.setDoctorID(rs.getInt("DoctorID"));
                    appointment.setAppointmentDateTime(rs.getObject("AppointmentDateTime", LocalDateTime.class));
                    return appointment;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAppointment(Appointment appointment) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "UPDATE Appointments SET PatientID = ?, TestID = ?, TechnicianID = ?, DoctorID = ?, AppointmentDateTime = ? WHERE AppointmentID = ?")) {
                stmt.setInt(1, appointment.getPatientID());
                stmt.setInt(2, appointment.getTestID());
                stmt.setInt(3, appointment.getTechnicianID());
                stmt.setInt(4, appointment.getDoctorID());
                stmt.setObject(5, appointment.getAppointmentDateTime());
                stmt.setInt(6, appointment.getAppointmentID());
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

    public boolean deleteAppointment(int appointmentID) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM Appointments WHERE AppointmentID = ?")) {
                stmt.setInt(1, appointmentID);
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

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try {
            try (Connection conn = dbConn.getConnection(); 
                    Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Appointments");
                while (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setAppointmentID(rs.getInt("AppointmentID"));
                    appointment.setPatientID(rs.getInt("PatientID"));
                    appointment.setTestID(rs.getInt("TestID"));
                    appointment.setTechnicianID(rs.getInt("TechnicianID"));
                    appointment.setDoctorID(rs.getInt("DoctorID"));
                    appointment.setAppointmentDateTime(rs.getObject("AppointmentDateTime", LocalDateTime.class));
                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointments;
    }
}

