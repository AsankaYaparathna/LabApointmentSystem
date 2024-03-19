/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Payment;

/**
 *
 * @author shakyapa
 */
import com.mycompany.labappointment.service.resources.Db.DBUtils;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    private final DBUtils dbConn;

    public PaymentRepo() {
        dbConn = new DBUtils();
    }

    public boolean addPayment(Payment payment) {
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "INSERT INTO Payments (PatientID, Amount, PaymentDateTime, PaymentMethod) VALUES (?, ?, ?, ?);",
                            Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, payment.getPatientID());
                stmt.setBigDecimal(2, payment.getAmount());
                stmt.setObject(3, payment.getPaymentDateTime());
                stmt.setString(4, payment.getPaymentMethod());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    payment.setPaymentID(rs.getInt(1));
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

    public Payment getPaymentByID(int paymentID) {
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Payments WHERE PaymentID = ?")) {
                stmt.setInt(1, paymentID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Payment payment = new Payment();
                    payment.setPaymentID(rs.getInt("PaymentID"));
                    payment.setPatientID(rs.getInt("PatientID"));
                    payment.setAmount(rs.getBigDecimal("Amount"));
                    payment.setPaymentDateTime(rs.getObject("PaymentDateTime", LocalDateTime.class));
                    payment.setPaymentMethod(rs.getString("PaymentMethod"));
                    return payment;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePayment(Payment payment) {
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "UPDATE Payments SET PatientID = ?, Amount = ?, PaymentDateTime = ?, PaymentMethod = ? WHERE PaymentID = ?")) {
                stmt.setInt(1, payment.getPatientID());
                stmt.setBigDecimal(2, payment.getAmount());
                stmt.setObject(3, payment.getPaymentDateTime());
                stmt.setString(4, payment.getPaymentMethod());
                stmt.setInt(5, payment.getPaymentID());
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

    public boolean deletePayment(int paymentID) {
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM Payments WHERE PaymentID = ?")) {
                stmt.setInt(1, paymentID);
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

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Payments");
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setPaymentID(rs.getInt("PaymentID"));
                    payment.setPatientID(rs.getInt("PatientID"));
                    payment.setAmount(rs.getBigDecimal("Amount"));
                    payment.setPaymentDateTime(rs.getObject("PaymentDateTime", LocalDateTime.class));
                    payment.setPaymentMethod(rs.getString("PaymentMethod"));
                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }
}

