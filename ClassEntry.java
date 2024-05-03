/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Marc
 */
public class ClassEntry {
    private String semester;
    private String courseCode;
    private Integer seats;
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setSeats(Integer seats) {
        this.seats = seats;
    }
    public String getSemester() {
        return semester;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public Integer getSeats() {
        return seats;
    }

    
}