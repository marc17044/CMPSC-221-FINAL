/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Marc
 */
public class CourseQueries {

    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addCourse;
    private static PreparedStatement getSemesterList;
    private static ResultSet resultSet;
    
    static void addCourse(CourseEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("INSERT INTO app.course (coursecode, description) VALUES (?, ?)");
            addCourse.setString(1, entry.getCourseCode());
            addCourse.setString(2, entry.getDescription());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllCourseCodes()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courses = new ArrayList<String>();
        try
        {
            getSemesterList = connection.prepareStatement("select coursecode from app.course order by coursecode");
            resultSet = getSemesterList.executeQuery();
            
            while(resultSet.next())
            {
                courses.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courses;
        
    }
    
    
}


