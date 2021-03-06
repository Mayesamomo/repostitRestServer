/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DAO {

    private String database;
    private Connection con;

    public DAO(String database) {
        this.database = database;
    }

    public DAO(Connection con){
        this.con=con;
    }
//    
//     public Connection getConnection() throws SQLException {
//        String driver = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/" + database;
//        String username = "root";
//        String password = "";
//        Connection con =null;
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, username, password);
//
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Connection failed " + ex.getMessage());
//            System.exit(1);
//
//        }
//        return con;
//    }
      public Connection getConnection()
    {
        // If there was no connection provided, make one
        if(con == null){
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/" + database;
            String username = "root";
            String password = "";
            Connection tempCon = null;
            try {
                Class.forName(driver);
                tempCon = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException ex1) {
                System.out.println("Failed to find driver class " + ex1.getMessage());
                System.exit(1);
            } catch (SQLException ex2) {
                System.out.println("Connection failed " + ex2.getMessage());
            }
            return tempCon;
        }
        else
            // If there was a connection provided, use that one
            return con;
    }

    public void freeConnection(Connection con) throws SQLException {
        try {
            if (con != null) {
                con.close();
                con = null;

            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }

    }
}
