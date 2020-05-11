/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import DAO.CommunityDAO;
import DTO.Community;

/**
 *
 * @author admin
 */
public class communnityTesting {
    public static void main(String[] args) {
        CommunityDAO commDB = new CommunityDAO("repostit");
        System.out.println(commDB.getCommunityIdByTitle("All"));
    }
}
