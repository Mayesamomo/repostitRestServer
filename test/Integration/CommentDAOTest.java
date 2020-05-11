/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import DAO.CommentDAO;
import DTO.Comment;
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
public class CommentDAOTest {
    
    public CommentDAOTest() {
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
     * Test of getCommentByPost method, of class CommentDAO.
     */
    @Test
    public void testGetCommentByPost() {
        System.out.println("getCommentByPost");
        int post_id = 0;
        CommentDAO instance = null;
        ArrayList<Comment> expResult = null;
        ArrayList<Comment> result = instance.getCommentByPost(post_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommentByUser method, of class CommentDAO.
     */
    @Test
    public void testGetCommentByUser() {
        System.out.println("getCommentByUser");
        int user_id = 0;
        CommentDAO instance = null;
        ArrayList<Comment> expResult = null;
        ArrayList<Comment> result = instance.getCommentByUser(user_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeComment method, of class CommentDAO.
     */
    @Test
    public void testMakeComment() {
        System.out.println("makeComment");
        Comment c = null;
        CommentDAO instance = null;
        boolean expResult = false;
        boolean result = instance.makeComment(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeComment method, of class CommentDAO.
     */
    @Test
    public void testRemoveComment() {
        System.out.println("removeComment");
        int comment_id = 0;
        int user_type = 0;
        CommentDAO instance = null;
        boolean expResult = false;
        boolean result = instance.removeComment(comment_id, user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommentCount method, of class CommentDAO.
     */
    @Test
    public void testGetCommentCount() {
        System.out.println("getCommentCount");
        int user_type = 0;
        CommentDAO instance = null;
        int expResult = 0;
        int result = instance.getCommentCount(user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPrivlage method, of class CommentDAO.
     */
    @Test
    public void testCheckPrivlage() {
        System.out.println("checkPrivlage");
        int user_type = 0;
        CommentDAO instance = null;
        boolean expResult = false;
        boolean result = instance.checkPrivlage(user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
