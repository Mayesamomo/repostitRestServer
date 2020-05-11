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
public interface CommunityDAOInterface {
    ArrayList<Community> getAllCommunitys();
    ArrayList<Community> getCommunityByUser(int user_id);
    ArrayList<Community> getCommunityByTitle(String title);
    boolean createCommunity(Community comm);
    boolean removeCommunity(int comm_id,int user_type);
    int getCommunityCount(int user_type);
    int getCommunityIdByTitle(String commName);
    
    
   
    
}
