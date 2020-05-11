/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAO.CommentDAO;
import DTO.Comment;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
@Path("comment")
public class CommentResource {

    @Context
    private UriInfo context;
    private CommentDAO commentDB = new CommentDAO("repostit");

    /**
     * Creates a new instance of CommunityResource
     */
    public CommentResource() {
    }

    private JSONObject convertCommentToJson(Comment c) {
        JSONObject jObj = new JSONObject();
        jObj.put("comment_id", c.getComment_id());
        jObj.put("comment_text", c.getComment_text());
        jObj.put("comment_date", c.getComment_date());
        jObj.put("post_id", c.getPost_id());
        jObj.put("user_id", c.getUser_id());
        jObj.put("username",c.getUsername());
        return jObj;
    }

    private Comment convertJsonStringToComment(String jsonString) {
        Comment c = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            String comment_Name = ((String) obj.get("comment_Name"));
            String user_id = ((String) obj.get("user_id"));
            int userId = Integer.parseInt(user_id);
            String post_id = (String) obj.get("post_id");
            int postId = Integer.parseInt(post_id);
            c = new Comment(comment_Name, userId, postId);

        } catch (ParseException exp) {
            System.out.println(exp);
        }
        return c;
    }

    private Comment convertJsonStringToCommentUpdate(String jsonString) {
        Comment c = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            String cText = ((String) obj.get("comment_text"));
            String cid = ((String) obj.get("comment_id"));
            int comment_id = Integer.parseInt(cid);

            c = new Comment(cText, comment_id);

        } catch (ParseException exp) {
            System.out.println(exp);
        }
        return c;
    }

    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUserComment/{user_id}")
    public String getCommentByUser(@PathParam("user_id") int userId) {
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();

        try {
            for (Comment c : this.commentDB.getCommentByUser(userId)) {
                JSONObject obj = convertCommentToJson(c);
                array.add(c);
            }
            response.put("Comment", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return response.toJSONString();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/removePost/{comment_id}/{user_type}")
    public boolean removePost(@PathParam("comment_id") String comment_id, @PathParam("user_type") String user_type) {
        boolean output = false;
        int comment_idNum = Integer.parseInt(comment_id);
        int user_typeNum = Integer.parseInt(user_type);
        try {

            output = commentDB.removeComment(comment_idNum, user_typeNum);

        } catch (Exception e) {
            System.out.println("Exception is Customer POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);

        }

        return output;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPostComment/{post_id}")
    public String getCommentByPost(@PathParam("post_id") String postId) {
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();
        int comm_id=Integer.parseInt(postId);

        try {
            for (Comment c : this.commentDB.getCommentByPost(comm_id)) {
                JSONObject obj = convertCommentToJson(c);
                array.add(obj);
            }
           
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return array.toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @POST
    @Path("/CreateComment")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean createComment(String comment) {
        Comment c;
        boolean output = false;
        try {
            c = convertJsonStringToComment(comment);
            if (checkforNull(c) == true) {
                output = commentDB.makeComment(c);
            } else {
                output = false;
            }
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return output;
    }

   

    private boolean checkforNull(Comment c) {
        boolean flag = false;
        if (c != null) {
            flag = true;
        }
        return flag;
    }
}
