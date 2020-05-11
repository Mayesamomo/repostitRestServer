/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author micha
 */
public class UserDAO extends DAO implements UserInterface {

    private static int workload = 12;

    public UserDAO(String database) {
        super(database);
    }

    @Override
    public boolean register(String userName, String password, String email, String fullName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            con = getConnection();

            String query = "select user_name from user where user_name=?";
            ps = con.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            if (count == 0)//rs is 0, there is no duplicates of this username, therefore:
            {
                String hashedPassword = hashPassword(password);
                ps = con.prepareStatement("insert into user ( user_name, password) values (?, ?)");
                ps.setString(1, userName);
                ps.setString(2, hashedPassword);
                ps.executeUpdate();
                ps = con.prepareStatement("INSERT INTO userinfo (full_name,email,user_id) VALUES (?,?,(select user_id from user where user_name = ?));");
                ps.setString(1, fullName);
                ps.setString(2, email);
                ps.setString(3, userName);
                ps.executeUpdate();
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the register() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the register() method: " + e.getMessage());
            }
        }
        return flag;
    }
    
    @Override
    public boolean promoteAdmin(String username){
        Connection con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            con = getConnection();

            String query = "UPDATE user SET type_id = 1 WHERE user.user_name =?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.executeUpdate();
            flag=true;

          

        } catch (SQLException e) {
            System.out.println("Exception occured in the login() method: " + e.getMessage());
        } finally {
            try {
                
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the login() method: " + e.getMessage());
            }
        }

        return flag;
    }

    @Override
    public ArrayList<User> login(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        String dbUser_name = "";
        String dbPassword = "";
        User u = new User();
        ArrayList<User> users = new ArrayList();
        int user_id = 0;
        try {
            con = getConnection();

            String query = "Select user.user_id, user_name, password ,user.type_id user_type,full_name,email,user_status,user_date from user join type on (user.type_id=type.type_id) join userinfo on (user.user_id = userinfo.user_id) where user_name = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                user_id = rs.getInt("user_id");
                dbUser_name = rs.getString("user_name");
                dbPassword = rs.getString("password");
                u.setUser_id(user_id);
                u.setUsername(dbUser_name);
                u.setUsertype(rs.getString("user_type"));
                u.setFullName(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setStatus(rs.getInt("user_status"));
                u.setDate(rs.getString("user_date"));
            }

            if (username.equals(dbUser_name)) {
                if (checkPassword(password, dbPassword)) {
                    u.setPassword("true");
                    users.add(u);
                }

            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the login() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the login() method: " + e.getMessage());
            }
        }

        return users;

    }

    @Override
    public boolean removeUser(int user_id, int user_type) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            if (checkPrivlage(user_type) == true) {
                conn = getConnection();
                String query = " DELETE FROM user WHERE user_id = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, user_id);
                ps.executeUpdate();
                flag = true;
            }

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close the PreparedStatement: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close the Connection: " + ex.getMessage());
                }
            }
        }
        return flag;
    }
    
    @Override
    public User getUserById(int user_id){
          Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        String dbUser_name = "";
        String dbPassword = "";
        User u = new User();
        try {
            con = getConnection();

            String query = "Select user.user_id, user_name ,user.type_id user_type,full_name,email,user_status,user_date from user join type on (user.type_id=type.type_id) join userinfo on (user.user_id = userinfo.user_id) where user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                user_id = rs.getInt("user_id");
                dbUser_name = rs.getString("user_name");
                u.setUser_id(user_id);
                u.setUsername(dbUser_name);
                u.setUsertype(rs.getString("user_type"));
                u.setFullName(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setStatus(rs.getInt("user_status"));
                u.setDate(rs.getString("user_date"));
            }


        } catch (SQLException e) {
            System.out.println("Exception occured in the login() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the login() method: " + e.getMessage());
            }
        }

        return u;
    }
    

    public static String hashPassword(String password_plaintext) {
        int workload = 13;
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }

    public boolean checkPrivlage(int user_type) {
        boolean flag = false;

        if (user_type == 1 || user_type == 2) {
            flag = true;
        }

        return flag;
    }

   //check if User exist
    @Override
    public boolean checkIfExist(String username, String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        // ArrayList<User> users = new ArrayList();
        boolean flag;
        try {
            con = getConnection();
//checking to see if username already exist in the database
            String query = "SELECT USER.user_id, USER.user_name, userinfo.user_id, userinfo.email FROM USER INNER JOIN userinfo ON USER.user_id = userinfo.user_id WHERE user.user_name =? AND userinfo.email =?";
            ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2, email);
            rs = ps.executeQuery();
            //set a counter to loop through the available users in the database
            //int count = 0;
            if (rs.next()) {
                 flag = true;
                return flag;
            } else {
                flag = false;
                return flag;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the checkifExist() method: " + e.getMessage());
        }
        flag = false;
        return flag;

    }
}
