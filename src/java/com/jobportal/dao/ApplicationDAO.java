package com.jobportal.dao;

import com.jobportal.model.Application;
import com.jobportal.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
    public boolean insertApplication(Application app) {
        String sql = "INSERT INTO applications (user_id, job_id, applied_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, app.getUserId());
            ps.setInt(2, app.getJobId());
            ps.setTimestamp(3, new java.sql.Timestamp(app.getAppliedDate().getTime()));
            ps.setString(4, app.getStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Application> getApplicationsByUserId(int userId) {
        List<Application> apps = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                apps.add(new Application(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("job_id"),
                    rs.getTimestamp("applied_date"),
                    rs.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apps;
    }

    public boolean updateApplicationStatus(int appId, String status) {
        String sql = "UPDATE applications SET status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, appId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteApplication(int appId) {
        String sql = "DELETE FROM applications WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, appId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
