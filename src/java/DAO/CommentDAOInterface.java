/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Comment;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface CommentDAOInterface {
    
    public ArrayList<Comment> getCommentByPost(int post_id);
    public ArrayList<Comment> getCommentByUser (int user_id);
    public boolean makeComment(Comment c);
    public boolean removeComment(int comment_id,int user_type);
    public int getCommentCount(int user_type);
}
