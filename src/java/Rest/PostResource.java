/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import DTO.Post;
import DAO.PostDAO;
import java.util.ArrayList;
import javax.ws.rs.PathParam;

import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * REST Web Service
 *
 * @author admin
 */
@Path("post")
public class PostResource {

    @Context
    private UriInfo context;
    private final PostDAO postDB = new PostDAO("repostit");

    /**
     * Creates a new instance of PostResource
     */
    public PostResource() {
    }

    /**
     * Retrieves representation of an instance of Rest.PostResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPosts")
    public String getPosts() {
        //Declaring vars
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();

        try {
            //Looping through all results from getAllPosts()
            for (Post p : this.postDB.getAllPosts()) {
                //Converting the Post to a json obj
                JSONObject obj = convertPostToJson(p);
                //Adding the json obj to the json array
                array.add(obj);

            }
            //Putting the array into a json obj
            response.put("Posts", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toString();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getReportedPosts")
    public String getReportedPosts() {
        //Declaring vars
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();

        try {
            //Looping through all results from getAllPosts()
            for (Post p : this.postDB.getReportedPosts()) {
                //Converting the Post to a json obj
                JSONObject obj = convertPostToJson(p);
                //Adding the json obj to the json array
                array.add(obj);

            }
            //Putting the array into a json obj
            response.put("Posts", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toString();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPostsByCommName/{commName}")
    public String getPostsByCommName(@PathParam("commName") String commName) {
        //Declaring vars
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();

        try {
            //Looping through all results from getAllPosts()
            for (Post p : this.postDB.getPostByCommName(commName)) {
                //Converting the Post to a json obj
                JSONObject obj = convertPostToJson(p);
                //Adding the json obj to the json array
                array.add(obj);

            }
            //Putting the array into a json obj
            response.put("Posts", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toString();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUserPosts/{userId}")
    public String getUsersPosts(@PathParam("userId") int userId) {
        //Declaring vars
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();

        try {
            //Looping through all results from getAllPosts()
            for (Post p : this.postDB.getPostByUser(userId)) {
                //Converting the Post to a json obj
                JSONObject obj = convertPostToJson(p);
                //Adding the json obj to the json array
                array.add(obj);

            }
            //Putting the array into a json obj
            response.put("Posts", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getCommunityPosts/{community}")
    public String getCommunityPosts(@PathParam("community") String community) {
        //Declaring vars
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();

        try {
            //Looping through all results from getAllPosts()
            for (Post p : this.postDB.getPostsByCommunity(community)) {
                //Converting the Post to a json obj
                JSONObject obj = convertPostToJson(p);
                //Adding the json obj to the json array
                array.add(obj);

            }
            //Putting the array into a json obj
            response.put("Posts", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPostTitle/{title}")
    public String getPostByTitle(@PathParam("title") String title) {
        //Declaring vars
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();

        try {
            //Looping through all results from getAllPosts()
            for (Post p : this.postDB.getPostByTitle(title)) {
                //Converting the Post to a json obj
                JSONObject obj = convertPostToJson(p);
                //Adding the json obj to the json array
                array.add(obj);

            }
            //Putting the array into a json obj
            response.put("Posts", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPostId/{id}")
    public String getPostById(@PathParam("id") String idStr) {
        //Declaring vars
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();
        int id = Integer.parseInt(idStr);
        try {
            //Looping through all results from getAllPosts()
            for (Post p : this.postDB.getPostById(id)) {
                //Converting the Post to a json obj
                JSONObject obj = convertPostToJson(p);
                //Adding the json obj to the json array
                array.add(obj);

            }
            //Putting the array into a json obj
            response.put("Posts", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPostLikes/{id}")
    public String getPostLikes(@PathParam("id") String idStr) {
        //Declaring vars
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();
        int id = Integer.parseInt(idStr);
        int likes = 0;
        try {
            likes = this.postDB.getPostLikes(id);

        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return String.valueOf(likes);

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/addLike/{post_id}/{user_id}/{likeValue}")
    public boolean addLike(@PathParam("post_id") String post_id, @PathParam("user_id") String user_id, @PathParam("likeValue") String likeVal) {
        boolean output = false;
        int post_idNum = Integer.parseInt(post_id);
        int user_idNum = Integer.parseInt(user_id);
        int likeValByn = Integer.parseInt(likeVal);

        try {

            output = postDB.insertPostLike(post_idNum, user_idNum, likeValByn);

        } catch (Exception e) {
            System.out.println("Exception is Customer POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);

        }

        return output;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/removePost/{post_id}/{user_type}")
    public boolean removePost(@PathParam("post_id") String post_id, @PathParam("user_type") String user_type) {
        boolean output = false;
        int post_idNum = Integer.parseInt(post_id);
        int user_typeNum = Integer.parseInt(user_type);
        try {

            output = postDB.removePost(post_idNum, user_typeNum);

        } catch (Exception e) {
            System.out.println("Exception is Customer POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);

        }

        return output;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/makePost")
    public boolean makePostText(String content) {
        Post p;
        boolean output = false;
        try {

            p = convertJsonStringToPostText(content);
            if (checkForBannedWord(p.getPost_desc()) == true) {
                output = false;
            } else {
                output = postDB.addPostText(p);
            }

        } catch (Exception e) {
            System.out.println("Exception is Customer POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);

        }

        return output;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/updatePost/{user_type}")
    public boolean updatePost(String content, @PathParam("user_type") String user_type) {
        Post p;
        int user_typeNum = Integer.parseInt(user_type);
        boolean output = false;
        try {
            p = convertJsonStringToPostTextUpdate(content);
            if (checkforNull(p) == true) {
                output = postDB.updatePost(p, user_typeNum);
            }
        } catch (Exception e) {
            System.out.println("Exception is Customer POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);

        }

        return output;
    }

    private boolean checkforNull(Post p) {
        boolean flag = false;
        if (p != null) {
            flag = true;
        }

        return flag;
    }

    private boolean checkForBannedWord(String postDesc) {
        boolean flag = false;
        ArrayList<String> bannedWords = new ArrayList();
        bannedWords = this.postDB.getBannedWords();

        for (int i = 0; i < bannedWords.size(); i++) {
            if (postDesc.contains(bannedWords.get(i))) {
                flag = true;
            }
        }

        return flag;
    }

    private Post convertJsonStringToPostText(String jsonString) {
        Post p = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            String title = (String) obj.get("post_title");
            String desc = (String) obj.get("post_description");
            String user_id = (String) obj.get("user_id");
            int user = Integer.parseInt(user_id);
            String community = (String) obj.get("community");
            String file_path = (String) obj.get("file_path");
            p = new Post(title, desc, user, community, file_path);
        } catch (ParseException exp) {
            System.out.println(exp);
        }
        return p;
    }

    private Post convertJsonStringToPostTextUpdate(String jsonString) {
        Post p = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            String title = (String) obj.get("post_title");
            String desc = (String) obj.get("post_description");
            String post_id = (String) obj.get("post_id");
            int postId = Integer.parseInt(post_id);
            p = new Post(postId, title, desc);
        } catch (ParseException exp) {
            System.out.println(exp);
        }
        return p;
    }

    public static JSONObject convertPostToJson(Post p) {
        JSONObject jObj = new JSONObject();
        jObj.put("post_id", p.getPost_id());
        jObj.put("post_title", p.getPost_title());
        jObj.put("post_description", p.getPost_desc());
        jObj.put("post_date", p.getPost_date());
        jObj.put("file_path", p.getFilePath());
        jObj.put("user_id", p.getUser_id());
        jObj.put("community", p.getCommunity_name());
        jObj.put("user_name", p.getUserName());
        return jObj;
    }

}
