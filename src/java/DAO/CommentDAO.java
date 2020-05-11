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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author admin
 */
public class CommentDAO extends DAO implements CommentDAOInterface {

    public CommentDAO(String database) {
        super(database);
    }

    public CommentDAO(Connection conn) {
        super(conn);
    }

    @Override
    public ArrayList<Comment> getCommentByPost(int post_id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Comment> comments = new ArrayList();

        try {
            conn = getConnection();
            String query = "select comment_id,comment_text,c.user_id,comment_date ,post_id,u.user_name from comment c join user u on c.user_id=u.user_id where c.post_id=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, post_id);
            rs = ps.executeQuery();

            // Loop through the result set
            while (rs.next()) {
                Comment c = new Comment();
                // Get the pieces of a customer from the resultset
                c.setComment_id(rs.getInt("comment_id"));
                c.setComment_text(rs.getString("comment_text"));
                c.setUsername(rs.getString("user_name"));
                c.setUser_id(rs.getInt("user_id"));
                c.setPost_id(rs.getInt("post_id"));
                c.setComment_date(rs.getString("comment_date"));
                comments.add(c);

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
        return comments;
    }

    @Override
    public ArrayList<Comment> getCommentByUser(int user_id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Comment> comments = new ArrayList();

        try {
            conn = getConnection();
            String query = "SELECT * FROM comment where user_id=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();

            // Loop through the result set
            while (rs.next()) {
                Comment c = new Comment();
                // Get the pieces of a customer from the resultset
                c.setComment_id(rs.getInt("comment_id"));
                c.setComment_text(rs.getString("comment_text"));
                c.setUser_id(rs.getInt("user_id"));
                c.setPost_id(rs.getInt("post_id"));
                c.setComment_date(rs.getString("comment_date"));
                comments.add(c);

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
        return comments;
    }

    @Override
    public boolean makeComment(Comment c) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        // Create variables used to interact with database 
        // We need them created here so we can close them in the finally block
        try {
            if (c != null) {
                conn = getConnection();

                // Can also do:
                ps = conn.prepareStatement("INSERT INTO comment(comment_text,user_id, post_id) "
                        + "VALUES (?,?,?);");

                // Get a statement from the connection
                ps.setString(1, c.getComment_text());
                ps.setInt(2, c.getUser_id());
                ps.setInt(3, c.getPost_id());

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
    public boolean removeComment(int comment_id, int user_type) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            if (checkPrivlage(user_type) == true) {
                conn = getConnection();
                String query = "DELETE FROM comment WHERE comment_id = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, comment_id);
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
    public int getCommentCount(int user_type) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (checkPrivlage(user_type) == true) {
                conn = getConnection();
                String query = "SELECT COUNT(comment_id) FROM comment";
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
