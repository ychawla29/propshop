/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.*;

/**
 *
 * @author Yogesh Chawla
 */
public class DBConnection {
    
    private final Connection con;
    private static DBConnection instance = null;
    
    private DBConnection() throws ClassNotFoundException, SQLException{
        Class.forName(DBConfig.DB_DRIVER);
        con = DriverManager.getConnection(DBConfig.DB_URL,DBConfig.DB_USER,DBConfig.DB_PWD);
    }
    
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException{
        if(instance == null)
            instance = new DBConnection();
        return instance;
    }
    
    public Connection getConnection() throws SQLException{
        return con;
    }
}

