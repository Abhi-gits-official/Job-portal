package com.jobportal.model;

import java.util.Date;

public class Job {
    private int id;
    private String title;
    private String description;
    private String location;
    private Date postedDate;

    public Job() {}

    public Job(int id, String title, String description, String location, Date postedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.postedDate = postedDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Date getPostedDate() { return postedDate; }
    public void setPostedDate(Date postedDate) { this.postedDate = postedDate; }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
