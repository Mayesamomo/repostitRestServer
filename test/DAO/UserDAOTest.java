/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author micha
 */
public class UserDAOTest {
    
    public UserDAOTest() {
    }
    
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
    public void testRegister() {
        System.out.println("register");
        String userName = "";
        String password = "";
        String email = "";
        String fullName = "";
        UserDAO instance = null;
        boolean expResult = false;
        boolean result = instance.register(userName, password, email, fullName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of promoteAdmin method, of class UserDAO.
     */
    @Test
    public void testPromoteAdmin() {
        System.out.println("promoteAdmin");
        String username = "";
        UserDAO instance = null;
        boolean expResult = false;
        boolean result = instance.promoteAdmin(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class UserDAO.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "";
        String password = "";
        UserDAO instance = null;
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.login(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeUser method, of class UserDAO.
     */
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        int user_id = 0;
        int user_type = 0;
        UserDAO instance = null;
        boolean expResult = false;
        boolean result = instance.removeUser(user_id, user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserById method, of class UserDAO.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        int user_id = 0;
        UserDAO instance = null;
        User expResult = null;
        User result = instance.getUserById(user_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashPassword method, of class UserDAO.
     */
    @Test
    public void testHashPassword() {
        System.out.println("hashPassword");
        String password_plaintext = "";
        String expResult = "";
        String result = UserDAO.hashPassword(password_plaintext);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPassword method, of class UserDAO.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        String password_plaintext = "";
        String stored_hash = "";
        boolean expResult = false;
        boolean result = UserDAO.checkPassword(password_plaintext, stored_hash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPrivlage method, of class UserDAO.
     */
    @Test
    public void testCheckPrivlage() {
        System.out.println("checkPrivlage");
        int user_type = 0;
        UserDAO instance = null;
        boolean expResult = false;
        boolean result = instance.checkPrivlage(user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfExist method, of class UserDAO.
     */
    @Test
    public void testCheckIfExist() {
        System.out.println("checkIfExist");
        String username = "";
        String email = "";
        UserDAO instance = null;
        boolean expResult = false;
        boolean result = instance.checkIfExist(username, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
