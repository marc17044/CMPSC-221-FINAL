/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentQueries {
    
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudentsID;
    private static PreparedStatement getStudentID;
    private static PreparedStatement getAllStudentsLast;
    private static PreparedStatement getStudent;
    private static ResultSet resultSet;
    private static ResultSet resultSetFirst;
    private static ResultSet resultSetLast;
    
    static void addStudent(StudentEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("INSERT INTO app.student (studentid, firstname, lastname) VALUES (?, ?, ?)");
            addStudent.setString(1, entry.getStudentID());
            addStudent.setString(2, entry.getFirstName());
            addStudent.setString(3, entry.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> studentEntrys = new ArrayList<StudentEntry>();
        try
        {
            getAllStudentsID = connection.prepareStatement("select * from app.student order by studentid");
            resultSet = getAllStudentsID.executeQuery();
            
            
            while(resultSet.next())
            {
                StudentEntry entry = new StudentEntry();
                entry.setStudentID(resultSet.getString(1));
                entry.setFirstName(resultSet.getString(2));
                entry.setLastName(resultSet.getString(3));
                
                studentEntrys.add(entry);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentEntrys;
        
    }
    
    public static String getStudentID(String name)
    {
        
        connection = DBConnection.getConnection();
        ArrayList<String> ids = new ArrayList<String>();
        
        String[] parts = name.split(", ");
        String lastname = parts[0]; // last
        String firstname = parts[1]; // first
        try
        {
            getStudentID = connection.prepareStatement("SELECT studentid FROM app.student WHERE firstname = ? AND lastname = ?");
            getStudentID.setString(1,firstname);
            getStudentID.setString(2,lastname);
            resultSet = getStudentID.executeQuery();
            while(resultSet.next())
            {       
                ids.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return ids.get(0);
    }
    
    public static StudentEntry getStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        StudentEntry entry = new StudentEntry();
        try
        {
            getStudent = connection.prepareStatement("SELECT * FROM app.student WHERE studentID = ?");
            getStudent.setString(1,studentID);
            resultSet = getStudent.executeQuery();
            
            while(resultSet.next())
            {       
                
                entry.setFirstName(resultSet.getString(2));
                entry.setLastName(resultSet.getString(3));
                entry.setStudentID(resultSet.getString(1));
                        
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return entry;
    }
    public static void dropStudent(String studentID){
        connection = DBConnection.getConnection();
        System.out.print("drop");
        try
        {
            getStudent = connection.prepareStatement("DELETE FROM app.student WHERE studentID = ?");
            getStudent.setString(1, studentID);
            getStudent.executeUpdate(); // Add this line to execute the deletion query
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    
}