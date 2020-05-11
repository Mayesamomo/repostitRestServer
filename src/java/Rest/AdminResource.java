/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAO.CommentDAO;
import DAO.CommunityDAO;
import DAO.PostDAO;
import DAO.UserDAO;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author admin
 */
@Path("Admin")
public class AdminResource {

    @Context
    private UriInfo context;
    private final PostDAO postDB = new PostDAO("repostit");
    private final UserDAO userDB = new UserDAO("repostit");
    private final CommentDAO commentDB = new CommentDAO("repostit");
    private final CommunityDAO commDB = new CommunityDAO("repostit");

    /**
     * Creates a new instance of AdminResource
     */
    public AdminResource() {
    }

    /**
     * Retrieves representation of an instance of Rest.AdminResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/removeUser/{user_id}/{user_type}")
    public boolean deleteUser(@PathParam("user_id") String user_id, @PathParam("user_type") String user_type) {
        boolean output = false;
        int user_idNum = Integer.parseInt(user_id);
        int user_typeNum = Integer.parseInt(user_type);
        try {

            output = userDB.removeUser(user_idNum, user_typeNum);

        } catch (Exception e) {
            System.out.println("Exception is Customer POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);

        }

        return output;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/countPost/{user_type}")
    public int countPosts(@PathParam("user_type") String user_type) {
        int output = 0;
        int user_typeNum = Integer.parseInt(user_type);
        try {

            output = postDB.getPostCount(user_typeNum);

        } catch (Exception e) {
            System.out.println("Exception is Customer POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);

        }

        return output;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/countComment/{user_type}")
    public int countComment(@PathParam("user_type") String user_type) {
        int output = 0;
        int user_typeNum = Integer.parseInt(user_type);
        try {

            output = commentDB.getCommentCount(user_typeNum);

        } catch (Exception e) {
            System.out.println("Exception is Customer POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);

        }

        return output;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/countCommunity/{user_type}")
    public int countCommunity(@PathParam("user_type") String user_type) {
        int output = 0;
        int user_typeNum = Integer.parseInt(user_type);
        try {

            output = commDB.getCommunityCount(user_typeNum);

        } catch (Exception e) {
            System.out.println("Exception is Customer POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);

        }

        return output;
    }
    


    /**
     * PUT method for updating or creating an instance of AdminResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
