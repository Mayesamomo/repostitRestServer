/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Post;
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
import org.junit.Ignore;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author micha
 */
public class PostDAOTest {

    public PostDAOTest() {
    }
    // Create expected results
    Post p1 = new Post(1, "JavaScript", "JavaScript is essential", "25/03/2020", 1, 1, "filepath", "Bob");
    Post p2 = new Post(2, "Deno", "Deno is a JavaScript server side language", "01/04/2020", 2, 2, "filepath", "Miguel");
    Post p3 = new Post(3, "Laravel", "Laravel is a PHP framework and I love it", "15/04/2020", 3, 3, "filepath", "Paul");
    ArrayList<Post> expResult = new ArrayList();

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
     * Test of getAllPosts method, of class PostDAO.
     */
    @Test
    public void testGetAllPosts() throws SQLException {
        System.out.println("getAllPosts");

        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select u.user_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path, p.community_id from post p inner join user u on u.user_id = p.user_id")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("post_id")).thenReturn(p1.getPost_id(), p2.getPost_id(), p3.getPost_id());
        when(rs.getString("post_title")).thenReturn(p1.getPost_title(), p2.getPost_title(), p3.getPost_title());
        when(rs.getString("post_description")).thenReturn(p1.getPost_desc(), p2.getPost_desc(), p3.getPost_desc());
        when(rs.getString("post_date")).thenReturn(p1.getPost_date(), p2.getPost_date(), p3.getPost_date());
        when(rs.getInt("user_id")).thenReturn(p1.getUser_id(), p2.getUser_id(), p3.getUser_id());
        when(rs.getInt("community_id")).thenReturn(p1.getCommunity_id(), p2.getCommunity_id(), p3.getCommunity_id());
        when(rs.getString("file_path")).thenReturn(p1.getFilePath(), p2.getFilePath(), p3.getFilePath());
        when(rs.getString("user_name")).thenReturn(p1.getUserName(), p2.getUserName(), p3.getUserName());

        // int numPostsInTable = 3;
        PostDAO poDao = new PostDAO(dbConn);
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);
        ArrayList<Post> result = poDao.getAllPosts();

        System.out.println(result);
        System.out.println(expResult);

        //assertEquals(numPostsInTable, result.size());
        assertEquals(expResult, result);

    }

    /**
     * Test of getReportedPosts method, of class PostDAO.
     */
    @Ignore
    @Test
    public void testGetReportedPosts() throws SQLException {
        System.out.println("getReportedPosts");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select u.user_name,c.community_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path from post p inner join user u on u.user_id = p.user_id join community c on p.community_id=c.community_id where p.reports > 5")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("post_id")).thenReturn(p1.getPost_id(), p2.getPost_id(), p3.getPost_id());
        when(rs.getString("post_title")).thenReturn(p1.getPost_title(), p2.getPost_title(), p3.getPost_title());
        when(rs.getString("post_description")).thenReturn(p1.getPost_desc(), p2.getPost_desc(), p3.getPost_desc());
        when(rs.getString("post_date")).thenReturn(p1.getPost_date(), p2.getPost_date(), p3.getPost_date());
        when(rs.getInt("user_id")).thenReturn(p1.getUser_id(), p2.getUser_id(), p3.getUser_id());
        when(rs.getInt("community_id")).thenReturn(p1.getCommunity_id(), p2.getCommunity_id(), p3.getCommunity_id());
        when(rs.getString("file_path")).thenReturn(p1.getFilePath(), p2.getFilePath(), p3.getFilePath());
        when(rs.getString("user_name")).thenReturn(p1.getUserName(), p2.getUserName(), p3.getUserName());
        PostDAO instance = new PostDAO(dbConn);
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);
        expResult.add(p1);

        ArrayList<Post> result = instance.getReportedPosts();
        assertEquals(expResult, result);

    }

    /**
     * Test of getBannedWords method, of class PostDAO.
     */
 
    @Test
    public void testGetBannedWords() throws SQLException {
        System.out.println("getBannedWords");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("SELECT * FROM bannedwords ")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("post_id")).thenReturn(p1.getPost_id(), p2.getPost_id(), p3.getPost_id());
        when(rs.getString("post_title")).thenReturn(p1.getPost_title(), p2.getPost_title(), p3.getPost_title());
        when(rs.getString("post_description")).thenReturn(p1.getPost_desc(), p2.getPost_desc(), p3.getPost_desc());
        when(rs.getString("post_date")).thenReturn(p1.getPost_date(), p2.getPost_date(), p3.getPost_date());
        when(rs.getInt("user_id")).thenReturn(p1.getUser_id(), p2.getUser_id(), p3.getUser_id());
        when(rs.getInt("community_id")).thenReturn(p1.getCommunity_id(), p2.getCommunity_id(), p3.getCommunity_id());
        when(rs.getString("file_path")).thenReturn(p1.getFilePath(), p2.getFilePath(), p3.getFilePath());
        when(rs.getString("user_name")).thenReturn(p1.getUserName(), p2.getUserName(), p3.getUserName());
        PostDAO instance = new PostDAO(dbConn);

        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);
        ArrayList<String> result = instance.getBannedWords();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPostByUser method, of class PostDAO.
     */
    @Ignore
    @Test
    public void testGetPostByUser() throws SQLException {
        System.out.println("getPostByUser");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("SELECT * FROM bannedwords ")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("post_id")).thenReturn(p1.getPost_id(), p2.getPost_id(), p3.getPost_id());
        when(rs.getString("post_title")).thenReturn(p1.getPost_title(), p2.getPost_title(), p3.getPost_title());
        when(rs.getString("post_description")).thenReturn(p1.getPost_desc(), p2.getPost_desc(), p3.getPost_desc());
        when(rs.getString("post_date")).thenReturn(p1.getPost_date(), p2.getPost_date(), p3.getPost_date());
        when(rs.getInt("user_id")).thenReturn(p1.getUser_id(), p2.getUser_id(), p3.getUser_id());
        when(rs.getInt("community_id")).thenReturn(p1.getCommunity_id(), p2.getCommunity_id(), p3.getCommunity_id());
        when(rs.getString("file_path")).thenReturn(p1.getFilePath(), p2.getFilePath(), p3.getFilePath());
        when(rs.getString("user_name")).thenReturn(p1.getUserName(), p2.getUserName(), p3.getUserName());
       
        int user = 0;
        PostDAO instance = new PostDAO(dbConn);
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
        String community = "";
        PostDAO instance = null;
        ArrayList<Post> expResult = null;
        ArrayList<Post> result = instance.getPostsByCommunity(community);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostByTitle method, of class PostDAO.
     */
    @Ignore
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
    @Ignore
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
    @Ignore
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
    @Ignore
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
    @Ignore
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
    @Ignore
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
     * Test of getPostByCommName method, of class PostDAO.
     */
    @Ignore
    @Test
    public void testGetPostByCommName() {
        System.out.println("getPostByCommName");
        String commname = "";
        PostDAO instance = null;
        ArrayList<Post> expResult = null;
        ArrayList<Post> result = instance.getPostByCommName(commname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostLikes method, of class PostDAO.
     */
    @Ignore
    @Test
    public void testGetPostLikes() {
        System.out.println("getPostLikes");
        int post_id = 0;
        PostDAO instance = null;
        int expResult = 0;
        int result = instance.getPostLikes(post_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertPostLike method, of class PostDAO.
     */
    @Ignore
    @Test
    public void testInsertPostLike() {
        System.out.println("insertPostLike");
        int post_id = 0;
        int user_id = 0;
        int likeValue = 0;
        PostDAO instance = null;
        boolean expResult = false;
        boolean result = instance.insertPostLike(post_id, user_id, likeValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPrivlage method, of class PostDAO.
     */
    @Ignore
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
