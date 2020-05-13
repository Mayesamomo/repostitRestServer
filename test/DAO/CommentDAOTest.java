/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Comment;
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
public class CommentDAOTest {

    public CommentDAOTest() {
    }

    // Create expected results
    Comment C1 = new Comment(1, "hello word", 1, 1, "02/04/2020");
    Comment C2 = new Comment(2, "hello Ireland", 2, 2, "03/04/2020");
    Comment C3 = new Comment(3, "hola Mallorca", 2, 3, "04/04/2020");
    ArrayList<Comment> expResult;
    // Create mock objects
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
     * Test of getCommentByPost method, of class CommentDAO.
     */
    @Test
    public void testGetCommentByPost() throws SQLException {
        System.out.println("getCommentByPost");

        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select * from Comment where post_id=?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("comment_id")).thenReturn(C1.getComment_id());
        when(rs.getString("comment_text")).thenReturn(C1.getComment_text());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id());
        when(rs.getInt("post_id")).thenReturn(C1.getPost_id());
        when(rs.getString("comment_date")).thenReturn(C1.getComment_date());

        int post_id = 1;
        //int numCommentsInTable = 3;
        CommentDAO instance = new CommentDAO(dbConn);
        expResult = new ArrayList();
        expResult.add(C1);
        ArrayList<Comment> result = instance.getCommentByPost(post_id);
        assertEquals(expResult, result);

    }

    /**
     * Test of getCommentByUser method, of class CommentDAO.
     */
    @Test
    public void testGetCommentByUser() throws SQLException {
        System.out.println("getCommentByUser");

        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select * from Comment where user_id=?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("comment_id")).thenReturn(C1.getComment_id());
        when(rs.getString("comment_text")).thenReturn(C1.getComment_text());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id());
        when(rs.getInt("post_id")).thenReturn(C1.getPost_id());
        when(rs.getString("comment_date")).thenReturn(C1.getComment_date());
        int user_id = 1;
        CommentDAO instance = new CommentDAO(dbConn);
        expResult = new ArrayList();
        expResult.add(C1);
        ArrayList<Comment> result = instance.getCommentByUser(user_id);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of makeComment method, of class CommentDAO.
     */
    @Test
    public void testMakeComment() throws SQLException {
        System.out.println("makeComment");
         // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("INSERT INTO comment(comment_text,user_id, post_id) "
                        + "VALUES (?,?,?);")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("comment_id")).thenReturn(C1.getComment_id(), C2.getComment_id(), C3.getComment_id());
        when(rs.getString("comment_text")).thenReturn(C1.getComment_text(), C2.getComment_text(), C3.getComment_text());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id(), C2.getUser_id(), C3.getUser_id());
        when(rs.getInt("post_id")).thenReturn(C1.getPost_id(), C2.getPost_id(), C3.getPost_id());
        when(rs.getString("comment_date")).thenReturn(C1.getComment_date(), C2.getComment_date(), C3.getComment_date());
      
        Comment c = C1;
//         Comment c2 = C2;
//          Comment c3 = C3;
        CommentDAO instance = new CommentDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.makeComment(c);
//        boolean result = instance.makeComment(c2);
//        boolean result = instance.makeComment(c3);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of removeComment method, of class CommentDAO.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRemoveComment() throws SQLException {
        System.out.println("removeComment");
         // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("DELETE FROM comment WHERE comment_id = ?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("comment_id")).thenReturn(C1.getComment_id());
        when(rs.getString("comment_text")).thenReturn(C1.getComment_text());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id());
        when(rs.getInt("post_id")).thenReturn(C1.getPost_id());
        when(rs.getString("comment_date")).thenReturn(C1.getComment_date());
        int comment_id = 1;
        int user_type = 1;
        CommentDAO instance = new CommentDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.removeComment(comment_id, user_type);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getCommentCount method, of class CommentDAO.
     */
    @Test
    public void testGetCommentCount() throws SQLException {
        System.out.println("getCommentCount");
         // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("SELECT COUNT(comment_id) FROM comment")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("comment_id")).thenReturn(C1.getComment_id());
        when(rs.getString("comment_text")).thenReturn(C1.getComment_text());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id());
        when(rs.getInt("post_id")).thenReturn(C1.getPost_id());
        when(rs.getString("comment_date")).thenReturn(C1.getComment_date());
        int user_type = 1;
        CommentDAO instance = null;
        int expResult = 1;
        int result = instance.getCommentCount(user_type);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of checkPrivlage method, of class CommentDAO.
     */
    @Test
    public void testCheckPrivlage() throws SQLException {
        System.out.println("checkPrivlage");
         // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("SELECT type_id, user_type FROM type WHERE type_id=?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("comment_id")).thenReturn(C1.getComment_id());
        when(rs.getString("comment_text")).thenReturn(C1.getComment_text());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id());
        when(rs.getInt("post_id")).thenReturn(C1.getPost_id());
        when(rs.getString("comment_date")).thenReturn(C1.getComment_date());
        int user_type = 0; //returns true user type exist , false if the type does not exist. 0 is not a user type id so it should return false
          //int user_type = 1;
        CommentDAO instance = new CommentDAO(dbConn);
        boolean expResult = false;
       // boolean expResult = true;
        boolean result = instance.checkPrivlage(user_type);
        assertEquals(expResult, result);
        
    }

}
