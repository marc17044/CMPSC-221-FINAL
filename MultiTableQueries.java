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
public class MultiTableQueries {
    
    private static Connection connection;
    private static PreparedStatement getAllClassDescriptions;
    private static ResultSet resultSet;
    
    
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> descriptions = new ArrayList<ClassDescription>();
        ArrayList<String> courseCodes = ClassQueries.getAllCourseCodes(semester);
        try
        {
            
            for (String code:courseCodes){
                getAllClassDescriptions = connection.prepareStatement("SELECT description FROM app.course WHERE coursecode = ?");
                getAllClassDescriptions.setString(1, code);
                resultSet = getAllClassDescriptions.executeQuery();
                while(resultSet.next())
                {
                    ClassDescription classObj = new ClassDescription();
                    classObj.setDescription(resultSet.getString(1));
                    classObj.setCourseCode(code);
                    classObj.setSeats(ClassQueries.getClassSeats(semester, code));
                    
                    descriptions.add(classObj);
                }
                
            }
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return descriptions;
        
    }
    
  
    
}