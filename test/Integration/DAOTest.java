/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import DAO.DAO;
import java.sql.Connection;
import java.sql.SQLException;
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
public class DAOTest {
    
    public DAOTest() {
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
     * Test of getConnection method, of class DAO.
     */
    @Test
    public void testGetConnection() throws SQLException {
        System.out.println("getConnection");
        DAO instance = null;
        Connection expResult = null;
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of freeConnection method, of class DAO.
     */
    @Test
    public void testFreeConnection() throws SQLException {
        System.out.println("freeConnection");
        Connection con = null;
        DAO instance = null;
        instance.freeConnection(con);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
