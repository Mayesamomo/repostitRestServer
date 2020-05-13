/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import DAO.PostDAO;
import DTO.Post;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author admin
 */
public class PostTesting {
    public static void main(String[] args) {
        PostDAO postDB = new PostDAO("repostit");
    
      System.out.println(postDB.getAllPosts());
        

    }
    
     public static boolean checkForBannedWord(String postDesc,ArrayList<String> words){
        boolean flag=false;
        ArrayList<String> bannedWords = new ArrayList();
        bannedWords = words;
        
        for (int i = 0; i < bannedWords.size(); i++) {
            if (postDesc.contains(bannedWords.get(i))) {
                flag=true;
            }
        }
          //System.out.println(flag);
        return flag;
       
        
    }
}
