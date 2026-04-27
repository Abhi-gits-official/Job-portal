package com.jobportal.model;

public class UserSkill {
    private int userId;
    private int skillId;

    public UserSkill() {}

    public UserSkill(int userId, int skillId) {
        this.userId = userId;
        this.skillId = skillId;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getSkillId() { return skillId; }
    public void setSkillId(int skillId) { this.skillId = skillId; }

    @Override
    public String toString() {
        return "UserSkill{" +
                "userId=" + userId +
                ", skillId=" + skillId +
                '}';
    }
}
