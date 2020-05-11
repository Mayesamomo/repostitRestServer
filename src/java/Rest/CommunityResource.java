/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAO.CommunityDAO;
import DTO.Community;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author admin
 */
@Path("community")
public class CommunityResource {

    @Context
    private UriInfo context;
    private CommunityDAO communityDB = new CommunityDAO("repostit");

    /**
     * Creates a new instance of CommunityResource
     */
    public CommunityResource() {
    }

    private Community convertJsonStringToCommunity(String jsonString) {
        Community c = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            String commName = ((String) obj.get("community_name"));
            String commDesc = ((String) obj.get("community_desc"));
            String user_id = (String) obj.get("user_id");
            int user = Integer.parseInt(user_id);
            c = new Community(commName, commDesc, user);

        } catch (ParseException exp) {
            System.out.println(exp);
        }
        return c;
    }

    private Community convertJsonStringToCommunityUpdate(String jsonString) {
        Community c = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            String commName = ((String) obj.get("community_name"));
            String commDesc = ((String) obj.get("community_desc"));
            String community_id = (String) obj.get("community_id");
            int communityId = Integer.parseInt(community_id);
            c = new Community(commName, commDesc, communityId);

        } catch (ParseException exp) {
            System.out.println(exp);
        }
        return c;
    }

    private JSONObject convertCommunityToJson(Community c) {
        JSONObject jObj = new JSONObject();
        jObj.put("community_id", c.getCommunity_id());
        jObj.put("community_name", c.getCommunity_name());
        jObj.put("community_desc", c.getCommunity_desc());
        jObj.put("community_status", c.getCommunity_status());
        jObj.put("user_id", c.getUser_id());
        return jObj;
    }

    @GET
    @Path("/getAllCommunitys")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCommunitys() {
        //Declaring vars
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();

        try {
            //Looping through all results from getAllPosts()
            for (Community c : this.communityDB.getAllCommunitys()) {
                //Converting the Post to a json obj
                JSONObject obj = convertCommunityToJson(c);
                //Adding the json obj to the json array
                array.add(obj);

            }
            //Putting the array into a json obj
            response.put("Communitys", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getCommIdByName/{comm_name}")
    public String getCommunityById(@PathParam("comm_name") String commName) {
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();
        int resp=0;
        String commId ="";
        try {
           resp =this.communityDB.getCommunityIdByTitle(commName);
            System.out.println(resp);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        
        commId=String.valueOf(resp);
        return commId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUserCommunity/{user_id}")
    public String getCommunityByUser(@PathParam("user_id") int userId) {
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();

        try {
            for (Community c : this.communityDB.getCommunityByUser(userId)) {
                JSONObject obj = convertCommunityToJson(c);
                array.add(c);
            }
            response.put("Community", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return response.toJSONString();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/removeCommunity/{comm_id}/{user_type}")
    public boolean removeCommunity(@PathParam("comm_id") String comm_id, @PathParam("user_type") String user_type) {
        boolean output = false;
        int comm_idNum = Integer.parseInt(comm_id);
        int user_typeNum = Integer.parseInt(user_type);
        try {

            output = communityDB.removeCommunity(comm_idNum, user_typeNum);

        } catch (Exception e) {
            System.out.println("Exception is Customer POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);

        }

        return output;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getCommByTitle/{title}")
    public String getCommunityByTitle(@PathParam("title") String title) {
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();

        try {
            for (Community c : this.communityDB.getCommunityByTitle(title)) {
                JSONObject obj = convertCommunityToJson(c);
                array.add(c);
            }
            response.put("Community", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return response.toJSONString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @POST
    @Path("/CreateCommunity")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean createCommunity(String community) {
        Community c;
        boolean output = false;
        try {
            c = convertJsonStringToCommunity(community);
            if (checkforNull(c) == true) {
                output = communityDB.createCommunity(c);
            }
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return output;
    }

    

    private boolean checkforNull(Community c) {
        boolean flag = false;
        if (c != null) {
            flag = true;
        }
        return flag;
    }
}
