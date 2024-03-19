/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Test;

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
import java.util.ArrayList;
import java.util.List;

public class TestRepo {
    private final DBUtils dbConn;

    public TestRepo() {
        dbConn = new DBUtils();
    }

    public boolean addTest(Test test) {
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "INSERT INTO Tests (TestName, Description, Price) VALUES (?, ?, ?);",
                            Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, test.getTestName());
                stmt.setString(2, test.getDescription());
                stmt.setBigDecimal(3, test.getPrice());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    test.setTestID(rs.getInt(1));
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

    public Test getTestByID(int testID) {
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tests WHERE TestID = ?")) {
                stmt.setInt(1, testID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Test test = new Test();
                    test.setTestID(rs.getInt("TestID"));
                    test.setTestName(rs.getString("TestName"));
                    test.setDescription(rs.getString("Description"));
                    test.setPrice(rs.getBigDecimal("Price"));
                    return test;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateTest(Test test) {
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(
                            "UPDATE Tests SET TestName = ?, Description = ?, Price = ? WHERE TestID = ?")) {
                stmt.setString(1, test.getTestName());
                stmt.setString(2, test.getDescription());
                stmt.setBigDecimal(3, test.getPrice());
                stmt.setInt(4, test.getTestID());
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

    public boolean deleteTest(int testID) {
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM Tests WHERE TestID = ?")) {
                stmt.setInt(1, testID);
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

    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        try {
            try (Connection conn = dbConn.GetConnection(); 
                    Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Tests");
                while (rs.next()) {
                    Test test = new Test();
                    test.setTestID(rs.getInt("TestID"));
                    test.setTestName(rs.getString("TestName"));
                    test.setDescription(rs.getString("Description"));
                    test.setPrice(rs.getBigDecimal("Price"));
                    tests.add(test);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tests;
    }
}

