/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author micha
 */
public class User {

    private int user_id;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String usertype;
    private int status;
    private String date;

    public User() {
    }
    
    

    public User(int user_id, String fullName, String username, String email, String password, String usertype, int status, String date) {
        this.user_id = user_id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
        this.status = status;
        this.date = date;
    }
    
    
    public User(String fullName, String username, String email, String password) {
  
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
     
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    } 

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype.toString();
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", fullName=" + fullName + ", username=" + username + ", email=" + email + ", password=" + password + ", usertype=" + usertype + ", status=" + status + ", date=" + date + '}';
    }

    
    
    
    
    
}
