package edu.northeastern.cs5200;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
	private static DatabaseConnection instance;
    private Connection connection;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "sowmika";
    private static final String PASSWORD = "Cs5200_19";
    private static final String SCHEMA = "cs5200_fall2019_mandava_asst3";
    private static final String DB_HOSTNAME = "cs5200-fall2019-mandava.cewykxpz5tqe.us-east-2.rds.amazonaws.com";
    private static final String URL = String.format("jdbc:mysql://%s/%s", DB_HOSTNAME, SCHEMA);
    private DatabaseConnection() throws SQLException{
        try{
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } 
        catch(ClassNotFoundException ex){
        	System.out.println(ex.getMessage());
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException{
        if (instance == null){
            instance = new DatabaseConnection();
        } 
        
        System.out.println(instance.getConnection());
        
        if(instance.getConnection().isClosed()){
            instance = new DatabaseConnection();
        }
        return instance;
    }
}