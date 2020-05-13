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
    Post p4 = new Post(4, "Laravel", "Laravel is a PHP framework and I love it", "15/04/2020", 3, 3, "filepath", "Paul", "Programmers");
    //ArrayList<Post> expResult = new ArrayList();

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
        ArrayList<Post> expResult = new ArrayList();
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
        ArrayList<Post> expResult = new ArrayList();
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
        ArrayList<Post> expResult = new ArrayList();
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);
        ArrayList<String> result = instance.getBannedWords();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPostByUser method, of class PostDAO.
     */
    @Test
    public void testGetPostByUser() throws SQLException {
        System.out.println("getPostByUser");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select u.user_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path, p.community_id from post p inner join user u on u.user_id = p.user_id where p.user_id=?")).thenReturn(ps);
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

        int user = 1;
        PostDAO instance = new PostDAO(dbConn);
        ArrayList<Post> expResult = new ArrayList();
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);

        ArrayList<Post> result = instance.getPostByUser(user);
        assertEquals(expResult, result);

    }

    /**
     * Test of getPostsByCommunity method, of class PostDAO.
     */
    @Test
    public void testGetPostsByCommunity() throws SQLException {
        System.out.println("getPostsByCommunity");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select u.user_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path, p.community_id from post p inner join user u on u.user_id = p.user_id where p.community_id=(select community_id from community where community_name=?) ")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);
        // Fill in the resultset
        when(rs.getInt("post_id")).thenReturn(p4.getPost_id());
        when(rs.getString("post_title")).thenReturn(p4.getPost_title());
        when(rs.getString("post_description")).thenReturn(p4.getPost_desc());
        when(rs.getString("post_date")).thenReturn(p4.getPost_date());
        when(rs.getInt("user_id")).thenReturn(p4.getUser_id());
        when(rs.getInt("community_id")).thenReturn(p4.getCommunity_id());
        when(rs.getString("file_path")).thenReturn(p4.getFilePath());
        when(rs.getString("user_name")).thenReturn(p4.getUserName());
        when(rs.getString("community_name")).thenReturn(p4.getCommunity_name());
        String community = "Programmers";
        PostDAO instance = new PostDAO(dbConn);
        ArrayList<Post> expResult = new ArrayList();
        expResult.add(p4);
        ArrayList<Post> result = instance.getPostsByCommunity(community);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPostByTitle method, of class PostDAO.
     */
    @Test
    public void testGetPostByTitle() throws SQLException {

        System.out.println("getPostByTitle");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select u.user_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path, p.community_id from post p inner join user u on u.user_id = p.user_id where p.post_title=?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("post_id")).thenReturn(p1.getPost_id());
        when(rs.getString("post_title")).thenReturn(p1.getPost_title());
        when(rs.getString("post_description")).thenReturn(p1.getPost_desc());
        when(rs.getString("post_date")).thenReturn(p1.getPost_date());
        when(rs.getInt("user_id")).thenReturn(p1.getUser_id());
        when(rs.getInt("community_id")).thenReturn(p1.getCommunity_id());
        when(rs.getString("file_path")).thenReturn(p1.getFilePath());
        when(rs.getString("user_name")).thenReturn(p1.getUserName());

        String title = "Java";
        PostDAO instance = new PostDAO(dbConn);
        ArrayList<Post> expResult = new ArrayList();
        expResult.add(p1);
        ArrayList<Post> result = instance.getPostByTitle(title);
        assertEquals(expResult, result);

    }

    /**
     * Test of getPostById method, of class PostDAO.
     */
    @Test
    public void testGetPostById() throws SQLException {
        System.out.println("getPostById");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select u.user_name,c.community_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path from post p inner join user u on u.user_id = p.user_id join community c on p.community_id=c.community_id where p.post_id=?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("post_id")).thenReturn(p1.getPost_id());
        when(rs.getString("post_title")).thenReturn(p1.getPost_title());
        when(rs.getString("post_description")).thenReturn(p1.getPost_desc());
        when(rs.getString("post_date")).thenReturn(p1.getPost_date());
        when(rs.getInt("user_id")).thenReturn(p1.getUser_id());
        when(rs.getInt("community_id")).thenReturn(p1.getCommunity_id());
        when(rs.getString("file_path")).thenReturn(p1.getFilePath());
        when(rs.getString("user_name")).thenReturn(p1.getUserName());
        int id = 1;
        PostDAO instance = new PostDAO(dbConn);
        ArrayList<Post> expResult = new ArrayList();
        expResult.add(p1);
        ArrayList<Post> result = instance.getPostById(id);
        assertEquals(expResult, result);

    }

    /**
     * Test of addPostText method, of class PostDAO.
     */
    @Test
    public void testAddPostText() throws SQLException {
        System.out.println("addPostText");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("INSERT INTO post(post_title, post_description,file_Path,user_id, community_id) VALUES (?,?,?,?,(SELECT community_id from community WHERE community_name = ?))")).thenReturn(ps);
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
        Post p = p1;
        PostDAO instance = new PostDAO(dbConn);
        boolean expResults = true;
        boolean result = instance.addPostText(p);
        assertEquals(expResults, result);

    }

    /**
     * Test of removePost method, of class PostDAO.
     */
    @Test
    public void testRemovePost() throws SQLException {
        System.out.println("removePost");
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("DELETE FROM post WHERE post_id = ?")).thenReturn(ps);
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
        int post_id = 1;
        int user_type = 1;
        PostDAO instance = new PostDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.removePost(post_id, user_type);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of updatePost method, of class PostDAO.
     */
   
    @Test
    public void testUpdatePost() throws SQLException {
        System.out.println("updatePost");
         // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("UPDATE post SET post_title=?,post_description=? WHERE post_id = ?")).thenReturn(ps);
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
        Post p = p1;
        int user_type = 1;
        PostDAO instance = new PostDAO(dbConn);
        boolean expResult = false;
        boolean result = instance.updatePost(p, user_type);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPostCount method, of class PostDAO.
     */
  
    @Test
    public void testGetPostCount() throws SQLException {
        System.out.println("getPostCount");
          // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select count(post_id) from post")).thenReturn(ps);
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
         int numProductsInTable = 3;
        int user_type = 1;
         PostDAO instance = new PostDAO(dbConn);
        int expResult = 3;
        int result = instance.getPostCount(user_type);
        
        //assertEquals(expResult, result);
        assertEquals(numProductsInTable, result);

    }

    /**
     * Test of getPostByCommName method, of class PostDAO.
     */
    @Ignore
    @Test
    public void testGetPostByCommName() throws SQLException {
        System.out.println("getPostByCommName");
         // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select u.user_name,c.community_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path from post p inner join user u on u.user_id = p.user_id join community c on p.community_id=c.community_id where c.community_name=?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        // Fill in the resultset
        when(rs.getInt("post_id")).thenReturn(p4.getPost_id());
        when(rs.getString("post_title")).thenReturn(p4.getPost_title());
        when(rs.getString("post_description")).thenReturn(p4.getPost_desc());
        when(rs.getString("post_date")).thenReturn(p4.getPost_date());
        when(rs.getInt("user_id")).thenReturn(p4.getUser_id());
        when(rs.getInt("community_id")).thenReturn(p4.getCommunity_id());
        when(rs.getString("file_path")).thenReturn(p4.getFilePath());
        when(rs.getString("user_name")).thenReturn(p4.getUserName());
        when(rs.getString("community_name")).thenReturn(p4.getCommunity_name());
        String commname = "Programmers";
         PostDAO instance = new PostDAO(dbConn);
         ArrayList<Post> expResult = new ArrayList();
        expResult.add(p4);
        ArrayList<Post> result = instance.getPostByCommName(commname);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPostLikes method, of class PostDAO.
     */
    @Ignore
    @Test
    public void testGetPostLikes() throws SQLException {
        System.out.println("getPostLikes");
          // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("SELECT COUNT(post_id) FROM postLike WHERE likevalue = 1 OR likevalue = 0 AND post_id = ?")).thenReturn(ps);
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
        int post_id = 3;
        PostDAO instance = new PostDAO(dbConn);
        int expResult = 3;
        int result = instance.getPostLikes(post_id);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of insertPostLike method, of class PostDAO.
     */
    @Ignore
    @Test
    public void testInsertPostLike() throws SQLException {
        System.out.println("insertPostLike");
          // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("INSERT INTO postlike (`like_id`, post_id, user_id, likevalue) VALUES (NULL, ?, ?, ?)")).thenReturn(ps);
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
        int post_id = 1;
        int user_id = 2;
        int likeValue = 2;
         PostDAO instance = new PostDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.insertPostLike(post_id, user_id, likeValue);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of checkPrivlage method, of class PostDAO.
     */
    @Ignore
    @Test
    public void testCheckPrivlage() {
        System.out.println("checkPrivlage");
        int user_type = 1;
         PostDAO instance = new PostDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.checkPrivlage(user_type);
        assertEquals(expResult, result);
        
    }

}
