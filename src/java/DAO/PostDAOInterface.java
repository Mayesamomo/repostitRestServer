/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.*;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface PostDAOInterface {

    ArrayList<Post> getAllPosts();

    ArrayList<Post> getPostByUser(int user);

    ArrayList<Post> getPostsByCommunity(String community);

    ArrayList<Post> getPostByTitle(String title);

    ArrayList<Post> getPostById(int id);

    boolean addPostText(Post p);
    
    boolean removePost(int post_id,int user_id);
    
    boolean updatePost(Post p,int user_id);
    
    int getPostCount(int user_type);
    
    int getPostLikes(int post_id);
    
    boolean insertPostLike(int post_id, int user_id, int likeValue);
    
    ArrayList<Post> getPostByCommName(String commname);
   
    ArrayList<String> getBannedWords();
    
    ArrayList<Post> getReportedPosts();
    

}
