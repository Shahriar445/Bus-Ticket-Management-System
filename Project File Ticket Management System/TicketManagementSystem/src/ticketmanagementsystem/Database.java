/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticketmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.SQLException;

/**
 *
 * @author Shipon
 */
public class Database {
    public static Connection connectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Database link ,USERNAME, PASSWORD ROOT IS THE DEFAULT USERNAME
            Connection connect;
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/busdata" , "root","");
            return connect;
        }catch(ClassNotFoundException | SQLException e){e.printStackTrace();
}
        return null;
        
    }
}
