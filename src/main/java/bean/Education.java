package bean;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Education {

    private String id;
    private String schoolName;
    private String degree;
    private String notes;
    private String activities;
    private String fieldOfStudy;
    private StartDate startDate;
    private EndDate endDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public StartDate getStartDate() {
        return startDate;
    }

    public void setStartDate(StartDate startDate) {
        this.startDate = startDate;
    }

    public EndDate getEndDate() {
        return endDate;
    }

    public void setEndDate(EndDate endDate) {
        this.endDate = endDate;
    }
}
