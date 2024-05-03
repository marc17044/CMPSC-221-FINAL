
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Marc
 */
public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addCourse;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement dropClass;
    private static ResultSet resultSet;

    static void addScheduleEntry(ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("INSERT INTO app.schedule (semester, coursecode, studentid, status, timestamp) VALUES (?, ?, ?, ?, ?)");
            addCourse.setString(1, entry.getSemester());
            addCourse.setString(2, entry.getCourseCode());
            addCourse.setString(3, entry.getStudentID());
            addCourse.setString(4, entry.getStatus());
            addCourse.setTimestamp(5, entry.getTimestamp());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> scheduleEntrys = new ArrayList<ScheduleEntry>();
        
        try
        {
            getScheduleByStudent = connection.prepareStatement("SELECT * FROM app.schedule WHERE semester = ? AND studentID = ?");
            getScheduleByStudent.setString(1,semester);
            getScheduleByStudent.setString(2,studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {       
                ScheduleEntry entry = new ScheduleEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getTimestamp(5));
 
                        
                scheduleEntrys.add(entry);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduleEntrys;
    }
    
    
    static int getScheduledStudentCount(String semester, String courseCode){
        connection = DBConnection.getConnection();
        int rowCount = 0;
        try
        {
            getScheduleByStudent = connection.prepareStatement("SELECT COUNT(*) FROM app.schedule WHERE semester = ? AND coursecode = ? AND status = 'scheduled'");
            getScheduleByStudent.setString(1,semester);
            getScheduleByStudent.setString(2,courseCode);
            resultSet = getScheduleByStudent.executeQuery();
            
            resultSet.next();
            rowCount = resultSet.getInt(1);

            return rowCount;
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rowCount;
    }
    
    static ArrayList<String> getScheduledStudentByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<String> studentIDs = new ArrayList<String>();
        
        try
        {
            getScheduleByStudent = connection.prepareStatement("SELECT studentID FROM app.schedule WHERE semester = ? AND courseCode = ? AND status = ?");
            getScheduleByStudent.setString(1,semester);
            getScheduleByStudent.setString(2,courseCode);
            getScheduleByStudent.setString(3,"scheduled");
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {        
                        
                studentIDs.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentIDs;
    }
    static ArrayList<String> getWaitlistedStudentByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<String> studentIDs = new ArrayList<String>();
        
        try
        {
            getScheduleByStudent = connection.prepareStatement("SELECT studentid FROM app.schedule WHERE semester = ? and coursecode = ? and status = 'waitlisted' ORDER BY timestamp ASC");
            getScheduleByStudent.setString(1,semester);
            getScheduleByStudent.setString(2,courseCode);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {        
                        
                studentIDs.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentIDs;
    }
    
    
    static void setStatusToScheduled(String studentId){
        connection = DBConnection.getConnection();
        
        try
        {
            getScheduleByStudent = connection.prepareStatement("UPDATE app.schedule SET status = 'scheduled' WHERE studentID = ?");
            getScheduleByStudent.setString(1,studentId);
            getScheduleByStudent.executeUpdate();

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    static void dropSchedule(String studentId,String semester){
        connection = DBConnection.getConnection();
        
        try
        {
            getScheduleByStudent = connection.prepareStatement("DELETE FROM app.schedule WHERE studentID = ? and semester = ?");
            getScheduleByStudent.setString(1,studentId);
            getScheduleByStudent.setString(2,semester);
            getScheduleByStudent.executeUpdate();

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static void dropClass(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        System.out.print("drop");
        try
        {
            dropClass = connection.prepareStatement("DELETE FROM app.SCHEDULE WHERE studentid = ? AND semester = ? AND courseCode = ?");
            dropClass.setString(1, studentID);
            dropClass.setString(2, semester);
            dropClass.setString(3, courseCode);
            
            dropClass.executeUpdate(); // Add this line to execute the deletion query
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}