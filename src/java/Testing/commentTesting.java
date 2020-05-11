/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import DAO.*;
import DTO.Comment;

/**
 *
 * @author admin
 */
public class commentTesting {

    public static void main(String[] args) {
        CommentDAO commentDB = new CommentDAO("repostit");
        
        Comment c1 = new Comment("text",3);
        c1.setUsername("sean");
        System.out.println(c1.getUsername());
        System.out.println(commentDB.getCommentByPost(50));
        
    }
}
