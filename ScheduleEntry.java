/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
/**
 **Semester (String)
*CourseCode (String)
*StudentID (String)
Status (String) – “S” or “W”
Timestamp (Timestamp)

 * @author Marc
 */
public class ScheduleEntry {
    private String semester;
    private String courseCode;
    private String studentID;
    private String status;
    private java.sql.Timestamp timestamp;
    
    public ScheduleEntry(String semester, String courseCode,String studentID,String status,java.sql.Timestamp timestamp) {
        this.semester = semester;
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.status = status;
        this.timestamp = timestamp;
    }
    
    public ScheduleEntry(String semester, String courseCode,String studentID,String status) {
        this.semester = semester;
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.status = status;
        this.timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setTimestamp(){
        this.timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }

    
    
    
    
    public String getSemester() {
        return semester;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public String getStudentID() {
        return studentID;
    }
    public String getStatus() {
        return status;
    }
    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }

    
}