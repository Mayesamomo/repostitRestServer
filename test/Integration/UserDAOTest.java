/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import DAO.UserDAO;
import DTO.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author micha
 */
public class UserDAOTest {
    
    public UserDAOTest() {
    }
     // If we want to use the @Before and @After methods to set up our driver
    // and our base URL, we need to create them as instance variables
    private String baseUrl;
    private WebDriver driver;
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
           // This can be used as a set up before each test. 
        // For example, you can use this method to set the System property
        // for where the browser driver can be found, and to set the base URL
        // Point the program to where the Chrome Driver executable can be found
        
        // When writing your own tests, remember to point this 
        // at where YOU store the Chromedriver.exe file
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\micha\\Documents\\NetBeansProjects\\repostitRestServer\\ChromeDriver");
        driver = new ChromeDriver();
        
        baseUrl = "http://localhost:8080/repostitRestServer/test-resbeans.html";
        // Load the page in the browser
        driver.get(baseUrl);
    }
    
    @After
    public void tearDown() {
        // This can be used as a tidy up after each test. 
        // For example, you can use this to quit the driver
        driver.quit();
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
    
}
