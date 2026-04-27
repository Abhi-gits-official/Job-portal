package com.jobportal.model;

import java.util.Date;

public class Application {
    private int id;
    private int userId;
    private int jobId;
    private Date appliedDate;
    private String status;

    public Application() {}

    public Application(int id, int userId, int jobId, Date appliedDate, String status) {
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
        this.appliedDate = appliedDate;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }
    public Date getAppliedDate() { return appliedDate; }
    public void setAppliedDate(Date appliedDate) { this.appliedDate = appliedDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", userId=" + userId +
                ", jobId=" + jobId +
                ", status='" + status + '\'' +
                '}';
    }
}
