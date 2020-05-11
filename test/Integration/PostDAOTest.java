/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import DAO.PostDAO;
import DTO.Post;
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
public class PostDAOTest {
    
    public PostDAOTest() {
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
     * Test of getAllPosts method, of class PostDAO.
     */
    @Test
    public void testGetAllPosts() {
        System.out.println("getAllPosts");
        PostDAO instance = null;
        ArrayList<Post> expResult = null;
        ArrayList<Post> result = instance.getAllPosts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostByUser method, of class PostDAO.
     */
    @Test
    public void testGetPostByUser() {
        System.out.println("getPostByUser");
        int user = 0;
        PostDAO instance = null;
        ArrayList<Post> expResult = null;
        ArrayList<Post> result = instance.getPostByUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostsByCommunity method, of class PostDAO.
     */
    @Test
    public void testGetPostsByCommunity() {
        System.out.println("getPostsByCommunity");
        int community = 0;
        PostDAO instance = null;
        ArrayList<Post> expResult = null;
        ArrayList<Post> result = instance.getPostByCommName(baseUrl);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostByTitle method, of class PostDAO.
     */
    @Test
    public void testGetPostByTitle() {
        System.out.println("getPostByTitle");
        String title = "";
        PostDAO instance = null;
        ArrayList<Post> expResult = null;
        ArrayList<Post> result = instance.getPostByTitle(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostById method, of class PostDAO.
     */
    @Test
    public void testGetPostById() {
        System.out.println("getPostById");
        int id = 0;
        PostDAO instance = null;
        ArrayList<Post> expResult = null;
        ArrayList<Post> result = instance.getPostById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPostText method, of class PostDAO.
     */
    @Test
    public void testAddPostText() {
        System.out.println("addPostText");
        Post p = null;
        PostDAO instance = null;
        boolean expResult = false;
        boolean result = instance.addPostText(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePost method, of class PostDAO.
     */
    @Test
    public void testRemovePost() {
        System.out.println("removePost");
        int post_id = 0;
        int user_type = 0;
        PostDAO instance = null;
        boolean expResult = false;
        boolean result = instance.removePost(post_id, user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePost method, of class PostDAO.
     */
    @Test
    public void testUpdatePost() {
        System.out.println("updatePost");
        Post p = null;
        int user_type = 0;
        PostDAO instance = null;
        boolean expResult = false;
        boolean result = instance.updatePost(p, user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostCount method, of class PostDAO.
     */
    @Test
    public void testGetPostCount() {
        System.out.println("getPostCount");
        int user_type = 0;
        PostDAO instance = null;
        int expResult = 0;
        int result = instance.getPostCount(user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPrivlage method, of class PostDAO.
     */
    @Test
    public void testCheckPrivlage() {
        System.out.println("checkPrivlage");
        int user_type = 0;
        PostDAO instance = null;
        boolean expResult = false;
        boolean result = instance.checkPrivlage(user_type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
