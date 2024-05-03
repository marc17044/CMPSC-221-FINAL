/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Marc
 */
public class StudentEntry {
   
    private String studentID;
    private String firstName;
    private String lastName;
    
    public void setStudentID(String id) {
        this.studentID = id;
    }
    public void setFirstName(String first) {
        this.firstName = first;
    }
    public void setLastName(String last) {
        this.lastName = last;
    }
    
    
    public String getStudentID() {
        return studentID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

}