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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author micha
 */
public class UserDAOTest {

    public UserDAOTest() {
    }
    // Create expected results
    User u1 = new User(1, "Bob Bobby", "bob", "bob123@hotmail.com", "password", "USER", 1, "1234567");
    User u2 = new User(2, "Dean Bobby", "Dean", "dean123@hotmail.com", "qwertyuio", "ADMIN", 1, "1234567");
    User u3 = new User(3, "Amy Bobby", "amy", "amy123@hotmail.com", "qwertyuio", "USER", 1, "1234567");
    ArrayList<User> expectedResults = new ArrayList();

    Connection dbConn = mock(Connection.class);
    PreparedStatement ps = mock(PreparedStatement.class);
    ResultSet rs = mock(ResultSet.class);

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of register method, of class UserDAO.
     */
    @Test
    public void testRegister() throws SQLException {
        System.out.println("register");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select username, password from user where user_id =?")).thenReturn(ps);
        when(dbConn.prepareStatement("INSERT INTO userinfo (full_name,email,user_id) VALUES (?,?,(select user_id from user where user_name = ?));")).thenReturn(ps);

        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);
        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUser_id());
        when(rs.getString("fullName")).thenReturn(u1.getFullName());
        when(rs.getString("username")).thenReturn(u1.getUsername());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("password")).thenReturn(u1.getPassword());
        when(rs.getString("usertype")).thenReturn((String) u1.getUsertype());
        when(rs.getString("date")).thenReturn(u1.getDate());

        String userName = "Jumbotron";
        String password = "Password123";
        String email = "jumbotron@gmail.com";
        String fullName = "Bootstrap Jumbotron";
        UserDAO instance = new UserDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.register(userName, password, email, fullName);
        assertEquals(expResult, result);

    }

    /**
     * Test of promoteAdmin method, of class UserDAO.
     */
    @Test
    public void testPromoteAdmin() throws SQLException {
        System.out.println("promoteAdmin");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("UPDATE user SET type_id = 1 WHERE user.user_name =?")).thenReturn(ps);
       
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true,  false);
        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUser_id());
        when(rs.getString("fullName")).thenReturn(u1.getFullName());
        when(rs.getString("username")).thenReturn(u1.getUsername());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("password")).thenReturn(u1.getPassword());
        when(rs.getString("usertype")).thenReturn((String) u1.getUsertype());
        when(rs.getString("date")).thenReturn(u1.getDate());
        String username = "bob";
        UserDAO instance = new UserDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.promoteAdmin(username);
        assertEquals(expResult, result);

    }

    /**
     * Test of login method, of class UserDAO.
     */
    @Test
    public void testLogin() throws SQLException {
        System.out.println("login");
         // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select user.user_id, user_name, password ,user.type_id user_type,full_name,email,user_status,user_date from user join type on (user.type_id=type.type_id) join userinfo on (user.user_id = userinfo.user_id) where user_name = ?")).thenReturn(ps);
       
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUser_id());
        when(rs.getString("fullName")).thenReturn(u1.getFullName());
        when(rs.getString("username")).thenReturn(u1.getUsername());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("password")).thenReturn(u1.getPassword());
        when(rs.getString("usertype")).thenReturn((String) u1.getUsertype());
        when(rs.getString("date")).thenReturn(u1.getDate());
        String username = "bob";
        String password = "password";
         UserDAO instance = new UserDAO(dbConn);
        ArrayList<User> expResult = new ArrayList();
        expResult.add(u1);
        ArrayList<User> result = instance.login(username, password);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of removeUser method, of class UserDAO.
     */
    @Test
    public void testRemoveUser() throws SQLException {
        System.out.println("removeUser");
       
         // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement(" DELETE FROM user WHERE user_id = ?")).thenReturn(ps);
       
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUser_id());
        when(rs.getString("fullName")).thenReturn(u1.getFullName());
        when(rs.getString("username")).thenReturn(u1.getUsername());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("password")).thenReturn(u1.getPassword());
        when(rs.getString("usertype")).thenReturn((String) u1.getUsertype());
        when(rs.getString("date")).thenReturn(u1.getDate());
        int user_id = 1;
        int user_type = 2;
        UserDAO instance = new UserDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.removeUser(user_id, user_type);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getUserById method, of class UserDAO.
     */
    @Test
    public void testGetUserById() throws SQLException {
        System.out.println("getUserById");
         // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select user.user_id, user_name ,user.type_id user_type,full_name,email,user_status,user_date from user join type on (user.type_id=type.type_id) join userinfo on (user.user_id = userinfo.user_id) where user_id = ?")).thenReturn(ps);
       
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUser_id());
        when(rs.getString("fullName")).thenReturn(u1.getFullName());
        when(rs.getString("username")).thenReturn(u1.getUsername());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("password")).thenReturn(u1.getPassword());
        when(rs.getString("usertype")).thenReturn((String) u1.getUsertype());
        when(rs.getString("date")).thenReturn(u1.getDate());
        int user_id = 1;
        UserDAO instance = new UserDAO(dbConn);
        User expResult = new User();
        expResult.equals(u1);
        User result = instance.getUserById(user_id);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of hashPassword method, of class UserDAO.
     */
    @Test
    public void testHashPassword() {
        System.out.println("hashPassword");
         
        String password_plaintext = "password";
        
        String expResult = UserDAO.hashPassword(u1.getPassword());
        String result = UserDAO.hashPassword(password_plaintext);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of checkPassword method, of class UserDAO.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        
        String password_plaintext = "password";
        String stored_hash = "password";
        boolean expResult = true;
        boolean result = UserDAO.checkPassword(password_plaintext, stored_hash);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkPrivlage method, of class UserDAO.
     */
    @Test
    public void testCheckPrivlage() {
        System.out.println("checkPrivlage");
        
        int user_type = 1;
        UserDAO instance = new UserDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.checkPrivlage(user_type);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of checkIfExist method, of class UserDAO.
     */
    @Test
    public void testCheckIfExist() throws SQLException {
        System.out.println("checkIfExist");
          // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("SELECT USER.user_id, USER.user_name, userinfo.user_id, userinfo.email FROM USER INNER JOIN userinfo ON USER.user_id = userinfo.user_id WHERE user.user_name =? AND userinfo.email =?")).thenReturn(ps);
       
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUser_id());
        when(rs.getString("fullName")).thenReturn(u1.getFullName());
        when(rs.getString("username")).thenReturn(u1.getUsername());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("password")).thenReturn(u1.getPassword());
        when(rs.getString("usertype")).thenReturn((String) u1.getUsertype());
        when(rs.getString("date")).thenReturn(u1.getDate());
        String username = "bob";
        String email = "bob123@hotmail.com";
          UserDAO instance = new UserDAO(dbConn);
        boolean expResult = false;
        boolean result = instance.checkIfExist(username, email);
        assertEquals(expResult, result);
        
    }

}
