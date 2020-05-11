/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import DAO.CommunityDAO;
import DTO.Community;
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
public class CommunityDAOTest {
    
    public CommunityDAOTest() {
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
     * Test of getAllCommunitys method, of class CommunityDAO.
     */
    @Test
    public void testGetAllCommunitys() {
        System.out.println("getAllCommunitys");
        CommunityDAO instance = null;
        ArrayList<Community> expResult = null;
        ArrayList<Community> result = instance.getAllCommunitys();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommunityByUser method, of class CommunityDAO.
     */
    @Test
    public void testGetCommunityByUser() {
        System.out.println("getCommunityByUser");
        int user_id = 0;
        CommunityDAO instance = null;
        ArrayList<Community> expResult = null;
        ArrayList<Community> result = instance.getCommunityByUser(user_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommunityByTitle method, of class CommunityDAO.
     */
    @Test
    public void testGetCommunityByTitle() {
        System.out.println("getCommunityByTitle");
        String title = "";
        CommunityDAO instance = null;
        ArrayList<Community> expResult = null;
        ArrayList<Community> result = instance.getCommunityByTitle(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCommunity method, of class CommunityDAO.
     */
    @Test
    public void testCreateCommunity() {
        System.out.println("createCommunity");
        Community comm = null;
        CommunityDAO instance = null;
        boolean expResult = false;
        boolean result = instance.createCommunity(comm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCommunity method, of class CommunityDAO.
     */
    @Test
    public void testRemoveCommunity() {
        System.out.println("removeCommunity");
        int comm_id = 0;
        int user_type = 0;
        CommunityDAO instance = null;
        boolean expResult = false;
        boolean result = instance.removeCommunity(comm_id, user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommunityCount method, of class CommunityDAO.
     */
    @Test
    public void testGetCommunityCount() {
        System.out.println("getCommunityCount");
        int user_type = 0;
        CommunityDAO instance = null;
        int expResult = 0;
        int result = instance.getCommunityCount(user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPrivlage method, of class CommunityDAO.
     */
    @Test
    public void testCheckPrivlage() {
        System.out.println("checkPrivlage");
        int user_type = 0;
        CommunityDAO instance = null;
        boolean expResult = false;
        boolean result = instance.checkPrivlage(user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
