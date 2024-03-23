/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Reports;

/**
 *
 * @author shakayapa
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

public class ReportRepo {
    private final DBUtils dbConn;

    public ReportRepo() {
        dbConn = new DBUtils();
    }

    public boolean addReport(Report report) {
        try {
            try (Connection conn = dbConn.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO Reports (PatientID, TestID, ReportDate, ReportDetails) VALUES (?, ?, ?, ?);",
                         Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, report.getPatientID());
                stmt.setInt(2, report.getTestID());
                stmt.setObject(3, report.getReportDate());
                stmt.setString(4, report.getReportDetails());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    report.setReportID(rs.getInt(1));
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
    
    public Report getReportByID(int reportID) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Reports WHERE ReportID = ?")) {
                stmt.setInt(1, reportID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Report report = new Report();
                    report.setReportID(rs.getInt("ReportID"));
                    report.setPatientID(rs.getInt("PatientID"));
                    report.setTestID(rs.getInt("TestID"));
                    report.setReportDate(rs.getObject("ReportDate", LocalDateTime.class));
                    report.setReportDetails(rs.getString("ReportDetails"));
                    return report;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateReport(Report report) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "UPDATE Reports SET PatientID = ?, TestID = ?, ReportDate = ?, ReportDetails = ? WHERE ReportID = ?")) {
                stmt.setInt(1, report.getPatientID());
                stmt.setInt(2, report.getTestID());
                stmt.setObject(3, report.getReportDate());
                stmt.setString(4, report.getReportDetails());
                stmt.setInt(5, report.getReportID());
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

    public boolean deleteReport(int reportID) {
        try {
            try (Connection conn = dbConn.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM Reports WHERE ReportID = ?")) {
                stmt.setInt(1, reportID);
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

    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();
        try {
            try (Connection conn = dbConn.getConnection(); 
                    Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Reports");
                while (rs.next()) {
                    Report report = new Report();
                    report.setReportID(rs.getInt("ReportID"));
                    report.setPatientID(rs.getInt("PatientID"));
                    report.setTestID(rs.getInt("TestID"));
                    report.setReportDate(rs.getObject("ReportDate", LocalDateTime.class));
                    report.setReportDetails(rs.getString("ReportDetails"));
                    reports.add(report);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }
}

