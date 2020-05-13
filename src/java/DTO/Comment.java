/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author admin
 */
public class Comment {

    private int comment_id;
    private String comment_text;
    private int user_id;
    private int post_id;
    private String comment_date;
    private String username;

    public Comment(int comment_id, String comment_text, int user_id, int post_id, String comment_date,String username) {
        this.comment_id = comment_id;
        this.comment_text = comment_text;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_date = comment_date;
        this.username=username;
    }

    public Comment() {
    }

    public Comment(String comment_text, int post_id) {
        this.comment_text = comment_text;
        this.post_id = post_id;
    }

    public Comment(String comment_text, int user_id, int post_id) {
        this.comment_text = comment_text;
        this.user_id = user_id;
        this.post_id = post_id;

    }

    public Comment(int comment_id, String comment_text, int user_id, int post_id, String comment_date) {
      this.comment_id = comment_id;
        this.comment_text = comment_text;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_date = comment_date;
    }

 

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Comment{" + "comment_id=" + comment_id + ", comment_text=" + comment_text + ", user_id=" + user_id + ", post_id=" + post_id + ", comment_date=" + comment_date + ", username=" + username + '}';
    }
    
    

  
// needed the equals and hashcode for testing
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (this.comment_id != other.comment_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.post_id != other.post_id) {
            return false;
        }
        if (!Objects.equals(this.comment_text, other.comment_text)) {
            return false;
        }
        return Objects.equals(this.comment_date, other.comment_date);
    }

}
