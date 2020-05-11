/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public interface UserInterface {

    public boolean register(String userName, String password, String email, String fullName);

    public ArrayList<User> login(String username, String password);
    
    public boolean removeUser(int user_id,int user_type);
    
    public User getUserById(int userid);
    
    public boolean promoteAdmin(String username);
      public boolean checkIfExist(String username,String email);
}
