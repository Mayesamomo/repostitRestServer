/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author admin
 */
public class Post {

   private int post_id;
    private String post_title;
    private String post_desc;
    private String post_date;
    private int user_id;
    private int community_id;
    private String filePath;
    private String userName;
    private String community_name;

    public Post() {

    }

    public Post(String post_title, String post_desc, int user_id, String community_name,String file_path) {
        this.post_title = post_title;
        this.post_desc = post_desc;
        this.user_id = user_id;
        this.community_name = community_name;
        this.filePath=file_path;
    }

    public Post(int post_id, String post_title, String post_desc, String post_date, int user_id, int community_id, String filePath, String userName, String community_name) {
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_desc = post_desc;
        this.post_date = post_date;
        this.user_id = user_id;
        this.community_id = community_id;
        this.filePath = filePath;
        this.userName = userName;
        this.community_name = community_name;
    }
    
  
    
     public Post(int post_id, String post_title, String post_desc) {
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_desc = post_desc;

    }
    



    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    @Override
    public String toString() {
        return "Post{" + "post_id=" + post_id + ", post_title=" + post_title + ", post_desc=" + post_desc + ", post_date=" + post_date + ", user_id=" + user_id + ", community_id=" + community_id + ", filePath=" + filePath + ", userName=" + userName + ", community_name=" + community_name + '}';
    }

    

}
