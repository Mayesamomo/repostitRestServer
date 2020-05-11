/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import DAO.UserDAO;
import DTO.User;

/**
 *
 * @author admin
 */
public class userTesting {

    public static void main(String[] args) {
        UserDAO userDb = new UserDAO("repostit");

        System.out.println(userDb.promoteAdmin("Polo96"));
      
    }
}
