/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Community;
import DTO.Post;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class CommunityDAO extends DAO implements CommunityDAOInterface {

    public CommunityDAO(String database) {
        super(database);
    }

    public CommunityDAO(Connection conn) {
        super(conn);
    }

    @Override
    public ArrayList<Community> getAllCommunitys() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Community> community = new ArrayList();
        try {
            con = getConnection();
            String query = "Select * from community where community_status =1";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Community comm = new Community(
                        rs.getInt("community_id"),
                        rs.getString("community_name"),
                        rs.getString("community_desc"),
                        rs.getInt("community_status"),
                        rs.getInt("user_id")
                );
                community.add(comm);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUser(Users use) method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the   method: " + e.getMessage());
            }
        }
        return community;
    }

    @Override
    public int getCommunityIdByTitle(String commName) {
        int commId = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Community> community = new ArrayList();
        try {
            con = getConnection();
            String query = "select community_id from community where community_name=?";
            ps = con.prepareStatement(query);
            ps.setString(1, commName);
            rs = ps.executeQuery();

            while (rs.next()) {

                commId = rs.getInt("community_id");
                

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getCommunityIdByTitlemethod: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the   method: " + e.getMessage());
            }
        }

        return commId;
    }

    @Override
    public ArrayList<Community> getCommunityByUser(int user_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Community> community = new ArrayList();
        try {
            con = getConnection();
            String query = "Select * from community where user_id =?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Community comm = new Community(
                        rs.getInt("community_id"),
                        rs.getString("community_name"),
                        rs.getString("community_desc"),
                        rs.getInt("community_status"),
                        rs.getInt("user_id")
                );
                community.add(comm);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUser(Users use) method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the   method: " + e.getMessage());
            }
        }
        return community;
    }

    @Override
    public ArrayList<Community> getCommunityByTitle(String title) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Community> community = new ArrayList();

        try {
            con = getConnection();
            String query = "Select * from community where community_name =?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();

            while (rs.next()) {
                Community comm = new Community(
                        rs.getInt("community_id"),
                        rs.getString("community_name"),
                        rs.getString("community_desc"),
                        rs.getInt("community_status"),
                        rs.getInt("user_id")
                );
                community.add(comm);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUser(Users use) method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the   method: " + e.getMessage());
            }
        }
        return community;
    }

    @Override
    public boolean createCommunity(Community comm) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean output = false;
        try {

            if (comm != null) {
                conn = getConnection();
                // Can also do:
                String query = "INSERT INTO community(user_id,community_name, community_desc) VALUES (?,?,?);";
                ps = conn.prepareStatement(query);
                ps.setInt(1, comm.getUser_id());
                ps.setString(2, comm.getCommunity_name());
                ps.setString(3, comm.getCommunity_desc());
                ps.executeUpdate();
                output = true;
            }

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the result set, statement and the connection
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close the PreparedStatement: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close the Connection: " + ex.getMessage());
                }
            }
        }

        return output;
    }

    @Override
    public boolean removeCommunity(int comm_id, int user_type) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean output = false;

        try {

            conn = getConnection();
            String query = "DELETE FROM community WHERE community_id =?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, comm_id);
            output = true;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the result set, statement and the connection
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close the PreparedStatement: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close the Connection: " + ex.getMessage());
                }
            }
        }

        return output;
    }

    @Override
    public int getCommunityCount(int user_type) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (checkPrivlage(user_type) == true) {
                conn = getConnection();
                String query = "SELECT COUNT(community_id) FROM community";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("count(post_id)");

                }
            }

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close the PreparedStatement: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close the Connection: " + ex.getMessage());
                }
            }
        }
        return count;
    }

    public boolean checkPrivlage(int user_type) {
        boolean flag = false;

        if (user_type == 1 || user_type == 2) {
            flag = true;
        }

        return flag;
    }

}
