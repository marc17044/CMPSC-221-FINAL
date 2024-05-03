/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *Void
ArrayList<String>
Int
void
addClass(ClassEntry class)
getAllCourseCodes(String semester)
getClassSeats(String semester, String courseCode)
dropClass(String semester, String courseCode) 
 * @author Marc
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ClassQueries {
    
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addClass;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getClassSeats;
    private static ResultSet resultSet;
    
    static void addClass(ClassEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addClass = connection.prepareStatement("INSERT INTO app.class (semester, coursecode, seats) VALUES (?, ?, ?)");
            addClass.setString(1, entry.getSemester());
            addClass.setString(2, entry.getCourseCode());
            addClass.setInt(3, entry.getSeats());
            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {

            getAllCourseCodes = connection.prepareStatement("SELECT Coursecode FROM app.class WHERE semester = ? order by Coursecode");
            getAllCourseCodes.setString(1, semester);
            resultSet = getAllCourseCodes.executeQuery();
            while(resultSet.next())
            {
                courseCodes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;
        
    }
    
    public static int getClassSeats(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<Integer> seats = new ArrayList<Integer>();
        try
        {
            getClassSeats = connection.prepareStatement("SELECT seats FROM app.class WHERE semester = ? and coursecode = ?");
            getClassSeats.setString(1, semester);
            getClassSeats.setString(2, courseCode);
            resultSet = getClassSeats.executeQuery();
            while(resultSet.next())
            {
                seats.add(resultSet.getInt(1));
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return seats.get(0);
        
    }
    public static void dropStudent(String studentID){
        
    }
    
}