/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Community;
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
public class CommunityDAOTest {

    public CommunityDAOTest() {
    }
    // Create expected results
    Community C1 = new Community(1, "Java Community", "Here we talk about Java");
    Community C2 = new Community(2, "Python Community", "Here we talk about Python");
    Community C3 = new Community(3, "TypeScript Community", "Here we talk about TypeScript");
    Community C4 = new Community(4, "Node.Js Community", "Here we talk about Node.Js");
    ArrayList<Community> expResult = new ArrayList();
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
     * Test of getAllCommunitys method, of class CommunityDAO.
     */
    @Test
    public void testGetAllCommunitys() throws SQLException {
        
        System.out.println("getAllCommunitys");
          // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select * from Community")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 4 results in the resultset, so need true to be returned 4 times
        when(rs.next()).thenReturn(true, true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("community_id")).thenReturn(C1.getCommunity_id(),C2.getCommunity_id(),C3.getCommunity_id(),C4.getCommunity_id());
        when(rs.getString("community_name")).thenReturn(C1.getCommunity_name(),C2.getCommunity_name(),C3.getCommunity_name(),C4.getCommunity_name());
        when(rs.getInt("community_status")).thenReturn(C1.getCommunity_status(),C2.getCommunity_status(),C3.getCommunity_status(),C4.getCommunity_status());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id(),C2.getUser_id(),C3.getUser_id(),C4.getUser_id());
        when(rs.getString("community_desc")).thenReturn(C1.getCommunity_desc(),C2.getCommunity_desc(),C3.getCommunity_desc(),C4.getCommunity_desc());
        
        CommunityDAO instance = new   CommunityDAO(dbConn);
        expResult = new ArrayList();
        expResult = instance.getAllCommunitys();
        ArrayList<Community> result = instance.getAllCommunitys();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCommunityIdByTitle method, of class CommunityDAO.
     * redundant test
     */
//    @Test
//    public void testGetCommunityIdByTitle() throws SQLException {
//        System.out.println("getCommunityIdByTitle");
//         
//          // Fill mock objects with appropriatel dummy data
//        when(dbConn.prepareStatement("Select community_name from Community")).thenReturn(ps);
//        when(ps.executeQuery()).thenReturn(rs);
//        // Want 1 results in the resultset, so need true to be returned 1 times
//        when(rs.next()).thenReturn(true,false);
//        // Fill in the resultset
//        when(rs.getInt("community_id")).thenReturn(C1.getCommunity_id());
//        when(rs.getString("community_name")).thenReturn(C1.getCommunity_name());
//        when(rs.getInt("community_status")).thenReturn(C1.getCommunity_status());
//        when(rs.getInt("user_id")).thenReturn(C1.getUser_id());
//        when(rs.getString("community_desc")).thenReturn(C1.getCommunity_desc());
//        String commName = "Java";
//        CommunityDAO instance = new   CommunityDAO(dbConn);
//        int expResult = 1;
//        int result = instance.getCommunityIdByTitle(commName);
//        assertEquals(expResult, result);
//       
//    }

    /**
     * Test of getCommunityByUser method, of class CommunityDAO.
     */
    @Test
    public void testGetCommunityByUser() throws SQLException {
        System.out.println("getCommunityByUser");
          // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select *from Community where user_id =?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 1 results in the resultset, so need true to be returned 1 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("community_id")).thenReturn(C1.getCommunity_id());
        when(rs.getString("community_name")).thenReturn(C1.getCommunity_name());
        when(rs.getInt("community_status")).thenReturn(C1.getCommunity_status());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id());
        when(rs.getString("community_desc")).thenReturn(C1.getCommunity_desc());
        int user_id = 1;
        CommunityDAO instance = new   CommunityDAO(dbConn);
        expResult = new ArrayList();
        expResult = instance.getCommunityByUser(C1.getUser_id());
        ArrayList<Community> result = instance.getCommunityByUser(user_id);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCommunityByTitle method, of class CommunityDAO.
     */
    @Test
    public void testGetCommunityByTitle() throws SQLException {
        System.out.println("getCommunityByTitle");
          // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select community_name from Community")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 1 results in the resultset, so need true to be returned 1 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("community_id")).thenReturn(C1.getCommunity_id());
        when(rs.getString("community_name")).thenReturn(C1.getCommunity_name());
        when(rs.getInt("community_status")).thenReturn(C1.getCommunity_status());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id());
        when(rs.getString("community_desc")).thenReturn(C1.getCommunity_desc());
        String title = "Java";
        CommunityDAO instance = new   CommunityDAO(dbConn);
         expResult = new ArrayList();
        expResult = instance.getCommunityByTitle(C1.getCommunity_name());
       
        ArrayList<Community> result = instance.getCommunityByTitle(title);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of createCommunity method, of class CommunityDAO.
     */
    @Test
    public void testCreateCommunity() throws SQLException {
       
        System.out.println("createCommunity");
          // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("INSERT INTO Community(user_id,community_name, community_desc) VALUES(?,?,?)")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 1 results in the resultset, so need true to be returned 1 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("community_id")).thenReturn(C1.getCommunity_id());
        when(rs.getString("community_name")).thenReturn(C1.getCommunity_name());
        when(rs.getInt("community_status")).thenReturn(C1.getCommunity_status());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id());
        when(rs.getString("community_desc")).thenReturn(C1.getCommunity_desc());
        Community comm = C1;
        CommunityDAO instance = new   CommunityDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.createCommunity(comm);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of removeCommunity method, of class CommunityDAO.
     */
    @Test
    public void testRemoveCommunity() throws SQLException {
        System.out.println("removeCommunity");
           // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("DELETE FROM community WHERE community_id =?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 1 results in the resultset, so need true to be returned 1 times
        when(rs.next()).thenReturn(true,false);
        // Fill in the resultset
        when(rs.getInt("community_id")).thenReturn(C1.getCommunity_id());
        when(rs.getString("community_name")).thenReturn(C1.getCommunity_name());
        when(rs.getInt("community_status")).thenReturn(C1.getCommunity_status());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id());
        when(rs.getString("community_desc")).thenReturn(C1.getCommunity_desc());
        int comm_id = 1;
        int user_type = 2;
        CommunityDAO instance = new   CommunityDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.removeCommunity(comm_id, user_type);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCommunityCount method, of class CommunityDAO.
     */
    @Test
    public void testGetCommunityCount() throws SQLException {
        System.out.println("getCommunityCount");
            // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("SELECT COUNT(community_id) FROM community")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 1 results in the resultset, so need true to be returned 1 times
        when(rs.next()).thenReturn(true,false);
         // Fill in the resultset
        when(rs.getInt("community_id")).thenReturn(C1.getCommunity_id(),C2.getCommunity_id(),C3.getCommunity_id(),C4.getCommunity_id());
        when(rs.getString("community_name")).thenReturn(C1.getCommunity_name(),C2.getCommunity_name(),C3.getCommunity_name(),C4.getCommunity_name());
        when(rs.getInt("community_status")).thenReturn(C1.getCommunity_status(),C2.getCommunity_status(),C3.getCommunity_status(),C4.getCommunity_status());
        when(rs.getInt("user_id")).thenReturn(C1.getUser_id(),C2.getUser_id(),C3.getUser_id(),C4.getUser_id());
        when(rs.getString("community_desc")).thenReturn(C1.getCommunity_desc(),C2.getCommunity_desc(),C3.getCommunity_desc(),C4.getCommunity_desc());
        
        int user_type = 1;
        CommunityDAO instance = new   CommunityDAO(dbConn);
        int expResult = 3;
        int result = instance.getCommunityCount(user_type);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkPrivlage method, of class CommunityDAO.
     */
    @Test
    public void testCheckPrivlage() {
        System.out.println("checkPrivlage");
        int user_type = 1;
        CommunityDAO instance =  new   CommunityDAO(dbConn);
        boolean expResult = true;
        boolean result = instance.checkPrivlage(user_type);
        assertEquals(expResult, result);
       
    }

}
