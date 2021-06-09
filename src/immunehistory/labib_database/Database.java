/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.labib_database;


import immunehistory.doctor.Doctor;
import immunehistory.lab_test.Lab_Test;
import immunehistory.labib_constants.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab_assistant.Assistant;

public class Database {
    
    public static Connection connection;
    
    public static void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=immune_history;selectMethod=cursor", "sa", "labib9130608");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void insert_into_doctor( Doctor doctor ){        
        try {
            if( connection == null ){
                connectDB();
            }            
            Statement statement = connection.createStatement();                      
            statement
                    .execute("INSERT INTO doctor "
                            +"VALUES("+
                            "'"+doctor.getDoctor_name()+"',"+
                            "'"+doctor.getDegree()+"',"+
                            "'"+doctor.getHospital()+"',"+
                            "'"+doctor.getEmail()+"',"+
                            "'"+doctor.getAddress()+"',"+
                            "'"+doctor.getMobile()+"',"+                            
                            "'"+doctor.getZipcode()+"',"+
                            "'"+doctor.getCategory()+"',"+
                            "'"+doctor.getPassword()+"'"
                            +")"            
                    );
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean authenticate_doctor( String email, String password ){
        try {
            if( connection == null ){
                connectDB();
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery( "SELECT * FROM doctor WHERE email = '"+email+"' AND password = '"+password+"'" );
            if( resultSet.next() == true ){
                Constants.doctor_id = resultSet.getInt("doctor_id");
                return true;
            }
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public static int authenticate_assistant( String email, String password ){
        try {
            if( connection == null ){
                connectDB();
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery( "SELECT * FROM assistant WHERE email = '"+email+"' AND password = '"+password+"'" );
            if( resultSet.next() == true )
                return resultSet.getInt("assistant_id");
            else
                return -1;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static void insert_into_assistant( Assistant assistant ){        
        try {
            if( connection == null ){
                connectDB();
            }            
            Statement statement = connection.createStatement();                      
            statement
                    .execute("INSERT INTO assistant "
                            +"VALUES("+
                            "'"+assistant.getAssistant_name()+"',"+
                            "'"+assistant.getMobile()+"',"+
                            "'"+assistant.getDegree()+"',"+
                            "'"+assistant.getHospital()+"',"+
                            "'"+assistant.getEmail()+"',"+
                            "'"+assistant.getAddress()+"',"+
                            "'"+assistant.getZipcode()+"',"+
                            "'"+assistant.getPassword()+"'"
                            +")"            
                    );
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void insert_into_lab_test( Lab_Test lab_test ){        
        try {
            if( connection == null ){
                connectDB();
            }            
            Statement statement = connection.createStatement();                      
            statement
                    .execute("INSERT INTO lab_test "
                            +"VALUES("+
                            "'"+lab_test.getRef_id()+"',"+
                            "'"+lab_test.getConsult_id()+"',"+
                            "'"+lab_test.getAssistant_id()+"',"+
                            "'"+lab_test.getTest_name()+"',"+
                            "'"+lab_test.getReport()+"',"+
                            "'"+lab_test.getTest_center()+"',"+
                            "'"+lab_test.getDate_of_test()+"',"+
                            "'"+lab_test.getDate_of_result()+"'"
                            +")"            
                    );
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ResultSet select_from_lab_test(  ){        
        try {
            if( connection == null ){
                connectDB();
            }            
            Statement statement = connection.createStatement();                      
            ResultSet result = statement
                    .executeQuery("SELECT * FROM lab_test");
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static ResultSet select_query( String query ){        
        try {
            if( connection == null ){
                connectDB();
            }            
            Statement statement = connection.createStatement();                      
            ResultSet result = statement
                    .executeQuery(query);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void insert_query( String query ){        
        try {
            if( connection == null ){
                connectDB();
            }            
            Statement statement = connection.createStatement();                      
            statement
                    .execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}