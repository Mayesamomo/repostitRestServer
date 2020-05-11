/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Post;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//TODO:Add null check to both methods where there is user input
/**
 *
 * @author admin
 */
public class PostDAO extends DAO implements PostDAOInterface {

    public PostDAO(String database) {
        super(database);
    }

    public PostDAO(Connection conn) {
        super(conn);
    }

    //Returning all posts that exist in the db
    @Override
    public ArrayList<Post> getAllPosts() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Post> posts = new ArrayList();

        try {
            conn = getConnection();
            String query = "Select u.user_name,c.community_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path from post p inner join user u on u.user_id = p.user_id join community c on p.community_id=c.community_id";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            // Loop through the result set
            while (rs.next()) {
                Post p = new Post();

                // Get the pieces of a customer from the resultset
                p.setPost_id(rs.getInt("post_id"));
                p.setPost_title(rs.getString("post_title"));
                p.setPost_desc(rs.getString("post_description"));
                p.setPost_date(rs.getString("post_date"));
                p.setUserName(rs.getString("user_name"));
                p.setFilePath(rs.getString("file_path"));
                p.setCommunity_name(rs.getString("community_name"));
                p.setUser_id(rs.getInt("user_id"));
                posts.add(p);

            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the result set, statement and the connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close ResultSet: " + ex.getMessage());
                }
            }
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
        return posts;
    }
    
    @Override
    public ArrayList<Post> getReportedPosts(){
         Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Post> posts = new ArrayList();

        try {
            conn = getConnection();
            String query = "Select u.user_name,c.community_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path from post p inner join user u on u.user_id = p.user_id join community c on p.community_id=c.community_id where p.reports > 5";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            // Loop through the result set
            while (rs.next()) {
                Post p = new Post();

                // Get the pieces of a customer from the resultset
                p.setPost_id(rs.getInt("post_id"));
                p.setPost_title(rs.getString("post_title"));
                p.setPost_desc(rs.getString("post_description"));
                p.setPost_date(rs.getString("post_date"));
                p.setUserName(rs.getString("user_name"));
                p.setFilePath(rs.getString("file_path"));
                p.setCommunity_name(rs.getString("community_name"));
                p.setUser_id(rs.getInt("user_id"));
                posts.add(p);

            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the result set, statement and the connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close ResultSet: " + ex.getMessage());
                }
            }
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
        return posts;
    }
    
    @Override
    public ArrayList<String> getBannedWords(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> bannedWords = new ArrayList();

        try {
            conn = getConnection();
            String query = "SELECT * FROM bannedwords ";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            // Loop through the result set
            while (rs.next()) {
                bannedWords.add(rs.getString("Word"));
            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the result set, statement and the connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close ResultSet: " + ex.getMessage());
                }
            }
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
        return bannedWords;
    }

    //Takes in username and returns all posts by that user
    @Override
    public ArrayList<Post> getPostByUser(int user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Post> posts = new ArrayList();

        try {
            conn = getConnection();
            String query = "Select u.user_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path, p.community_id from post p inner join user u on u.user_id = p.user_id where p.user_id=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, user);
            rs = ps.executeQuery();

            // Loop through the result set
            while (rs.next()) {
                Post p = new Post();

                // Get the pieces of a customer from the resultset
                p.setPost_id(rs.getInt("post_id"));
                p.setPost_title(rs.getString("post_title"));
                p.setPost_desc(rs.getString("post_description"));
                p.setPost_date(rs.getString("post_date"));
                p.setUserName(rs.getString("user_name"));
                p.setCommunity_id(rs.getInt("community_id"));
                p.setUser_id(rs.getInt("user_id"));
                posts.add(p);

            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the result set, statement and the connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close ResultSet: " + ex.getMessage());
                }
            }
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
        return posts;
    }

    //Takes community name and returns all the posts in that community 
    //Not 1st iteration
    @Override
    public ArrayList<Post> getPostsByCommunity(String community) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Post> posts = new ArrayList();

        try {
            conn = getConnection();
            String query = "Select u.user_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path, p.community_id from post p inner join user u on u.user_id = p.user_id where p.community_id=(select community_id from community where community_name=?) ";
            ps = conn.prepareStatement(query);
            ps.setString(1, community);
            rs = ps.executeQuery();

            // Loop through the result set
            while (rs.next()) {
                Post p = new Post();

                // Get the pieces of a customer from the resultset
                p.setPost_id(rs.getInt("post_id"));
                p.setPost_title(rs.getString("post_title"));
                p.setPost_desc(rs.getString("post_description"));
                p.setPost_date(rs.getString("post_date"));
                p.setUserName(rs.getString("user_name"));
                p.setCommunity_id(rs.getInt("community_id"));
                p.setFilePath(rs.getString("file_path"));
                p.setUser_id(rs.getInt("user_id"));
                posts.add(p);

            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the result set, statement and the connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close ResultSet: " + ex.getMessage());
                }
            }
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
        return posts;
    }

    //Takes title of Post and returns an arraylist of all posts matching that title
    //Not 1st Iteration
    @Override
    public ArrayList<Post> getPostByTitle(String title) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Post> posts = new ArrayList();

        try {
            conn = getConnection();
            String query = "Select u.user_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path, p.community_id from post p inner join user u on u.user_id = p.user_id where p.post_title=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();

            // Loop through the result set
            while (rs.next()) {
                Post p = new Post();

                // Get the pieces of a customer from the resultset
                p.setPost_id(rs.getInt("post_id"));
                p.setPost_title(rs.getString("post_title"));
                p.setPost_desc(rs.getString("post_description"));
                p.setPost_date(rs.getString("post_date"));
                p.setUserName(rs.getString("user_name"));
                p.setCommunity_id(rs.getInt("community_id"));
                p.setUser_id(rs.getInt("user_id"));
                posts.add(p);

            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the result set, statement and the connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close ResultSet: " + ex.getMessage());
                }
            }
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
        return posts;
    }

    @Override
    public ArrayList<Post> getPostById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Post> posts = new ArrayList();

        try {
            conn = getConnection();
            String query = "Select u.user_name,c.community_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path from post p inner join user u on u.user_id = p.user_id join community c on p.community_id=c.community_id where p.post_id=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            // Loop through the result set
            while (rs.next()) {
                Post p = new Post();

                // Get the pieces of a customer from the resultset
                p.setPost_id(rs.getInt("post_id"));
                p.setPost_title(rs.getString("post_title"));
                p.setPost_desc(rs.getString("post_description"));
                p.setPost_date(rs.getString("post_date"));
                p.setUserName(rs.getString("user_name"));
                p.setCommunity_name(rs.getString("community_name"));
                p.setUser_id(rs.getInt("user_id"));
                p.setFilePath(rs.getString("file_path"));
                posts.add(p);

            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the result set, statement and the connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close ResultSet: " + ex.getMessage());
                }
            }
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
        return posts;
    }

    @Override
    public boolean addPostText(Post p) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flag = false;

        try {

            if (p != null) {
                conn = getConnection();

                // Can also do:
                 ps = conn.prepareStatement("INSERT INTO post(post_title, post_description,file_Path,user_id, community_id) VALUES (?,?,?,?,(SELECT community_id from community WHERE community_name = ?))");

                // Get a statement from the connection
                ps.setString(1, p.getPost_title());
                ps.setString(2, p.getPost_desc());
                ps.setString(3, p.getFilePath());
                ps.setInt(4, p.getUser_id());
                ps.setString(5, p.getCommunity_name());

                // Execute the query
                int n = ps.executeUpdate();
                flag = true;
            } else {
                flag = false;
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
        return flag;
    }

    @Override
    public boolean removePost(int post_id, int user_type) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            if (checkPrivlage(user_type) == true) {
                conn = getConnection();
                String query = "DELETE FROM post WHERE post_id = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, post_id);
                ps.executeUpdate();
                flag = true;
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
        return flag;
    }

    @Override
    public boolean updatePost(Post p, int user_type) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            if (checkPrivlage(user_type) == true) {
                conn = getConnection();
                String query = "UPDATE post SET post_title=?,post_description=? WHERE post_id = ?";
                ps = conn.prepareStatement(query);
                ps.setString(1, p.getPost_title());
                ps.setString(2, p.getPost_desc());
                ps.setInt(3, p.getPost_id());
                ps.executeUpdate();
                flag = true;
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
        return flag;
    }

    @Override
    public int getPostCount(int user_type) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (checkPrivlage(user_type) == true) {
                conn = getConnection();
                String query = "Select count(post_id) from post";
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
    
    @Override
    public ArrayList<Post> getPostByCommName(String commname){
         Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Post> posts = new ArrayList();

        try {
            conn = getConnection();
            String query = "Select u.user_name,c.community_name, p.post_id, p.user_id, p.post_title, p.post_description, p.post_date, p.file_path from post p inner join user u on u.user_id = p.user_id join community c on p.community_id=c.community_id where c.community_name=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, commname);
            rs = ps.executeQuery();

            // Loop through the result set
            while (rs.next()) {
                Post p = new Post();

                // Get the pieces of a customer from the resultset
                p.setPost_id(rs.getInt("post_id"));
                p.setPost_title(rs.getString("post_title"));
                p.setPost_desc(rs.getString("post_description"));
                p.setPost_date(rs.getString("post_date"));
                p.setUserName(rs.getString("user_name"));
                p.setCommunity_name(rs.getString("community_name"));
                p.setUser_id(rs.getInt("user_id"));
                p.setFilePath(rs.getString("file_path"));
                posts.add(p);

            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the result set, statement and the connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close ResultSet: " + ex.getMessage());
                }
            }
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
        return posts;
    }
         

    @Override
    public int getPostLikes(int post_id) {
        int likes=0;
        int dislikes=0;
        int overallLike=0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;

        try {

            conn = getConnection();
            String query = "select count(post_id) from postLike where likevalue=1 and post_id=?";
            String query2 = "select count(post_id) from postLike where likevalue=0 and post_id=?";
            ps = conn.prepareStatement(query);
            ps2= conn.prepareStatement(query2);
            ps.setInt(1, post_id);
            ps2.setInt(1, post_id);
            rs = ps.executeQuery();
            rs2= ps2.executeQuery();
            while (rs.next()) {
                likes = rs.getInt("count(post_id)");

            }
            while (rs2.next()) {
                dislikes = rs2.getInt("count(post_id)");

            }
            
            overallLike = likes-dislikes;

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
        
        return overallLike;
    }
    
    public boolean insertPostLike(int post_id, int user_id, int likeValue){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flag = false;

        try {
                conn = getConnection();
                ps = conn.prepareStatement("INSERT INTO postlike (`like_id`, post_id, user_id, likevalue) VALUES (NULL, ?, ?, ?)");

                ps.setInt(1, post_id);
                ps.setInt(2, user_id);
                ps.setInt(3, likeValue);
                int n = ps.executeUpdate();
                flag=true;
           

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
        return flag;
    }

    public boolean checkPrivlage(int user_type) {
        boolean flag = false;

        if (user_type == 1 || user_type == 2) {
            flag = true;
        }

        return flag;
    }

}
