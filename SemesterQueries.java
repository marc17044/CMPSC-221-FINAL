/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author acv
 */
public class SemesterQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addSemester;
    private static PreparedStatement getSemesterList;
    private static PreparedStatement dropClass;
    private static ResultSet resultSet;
    
    public static void addSemester(String name)
    {
        connection = DBConnection.getConnection();
        try
        {
            addSemester = connection.prepareStatement("insert into app.semester (semester) values (?)");
            addSemester.setString(1, name);
            addSemester.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getSemesterList()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> semester = new ArrayList<String>();
        try
        {
            getSemesterList = connection.prepareStatement("select semester from app.semester order by semester");
            resultSet = getSemesterList.executeQuery();
            
            while(resultSet.next())
            {
                semester.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return semester;
        
    }
    
    
    
}
