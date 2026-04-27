package com.jobportal.dao;

import com.jobportal.model.UserSkill;
import com.jobportal.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDAO {
    public boolean insertUserSkill(UserSkill userSkill) {
        String sql = "INSERT INTO user_skills (user_id, skill_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userSkill.getUserId());
            ps.setInt(2, userSkill.getSkillId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<UserSkill> getSkillsByUserId(int userId) {
        List<UserSkill> userSkills = new ArrayList<>();
        String sql = "SELECT * FROM user_skills WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userSkills.add(new UserSkill(
                    rs.getInt("user_id"),
                    rs.getInt("skill_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userSkills;
    }

    public boolean deleteUserSkill(int userId, int skillId) {
        String sql = "DELETE FROM user_skills WHERE user_id=? AND skill_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, skillId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
