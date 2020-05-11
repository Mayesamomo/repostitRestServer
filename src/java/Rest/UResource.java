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

import DTO.User;
import DAO.UserDAO;
import javax.ws.rs.PathParam;

import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * REST Web Service
 *
 * @author admin
 */
@Path("User")
public class UResource {

    @Context
    //Declaring all global vars for the user res
    private UriInfo context;
    private final UserDAO db = new UserDAO("repostit");
    private User u = new User();

    /**
     * Creates a new instance of UserResource
     */
    public UResource() {
    }

    private User convertJsonStringToUser(String jsonString) {
        User u = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            u = new User();
            u.setFullName((String) obj.get("fullName"));
            u.setUsername((String) obj.get("user_name"));
            u.setPassword((String) obj.get("password"));
            u.setEmail((String) obj.get("email"));

        } catch (ParseException exp) {
            System.out.println(exp);
        }
        return u;
    }

    private User convertJsonStringToUserLogin(String jsonString) {
        User u = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            u = new User();
            u.setUsername((String) obj.get("user_name"));
            u.setPassword((String) obj.get("password"));

        } catch (ParseException exp) {
            System.out.println(exp);
        }
        return u;
    }

    //converting user object to JSONobject
    private JSONObject convertUserToJson(User u) {
        JSONObject jObj = new JSONObject();
        jObj.put("user_id", u.getUser_id());
        jObj.put("full_name", u.getFullName());
        jObj.put("user_name", u.getUsername());
        jObj.put("email", u.getEmail());
        jObj.put("password", u.getPassword());
        jObj.put("type_id", u.getUsertype());
        jObj.put("user_status", u.getStatus());
        jObj.put("date", u.getDate());
        return jObj;
    }

    private boolean validateUser(User u) {
        boolean flag = false;
        if (u == null) {
            return flag;
        } else {
            flag = true;
            return flag;
        }
    }

    /**
     * Retrieves representation of an instance of REST.UserResource
     *
     * @return an instance of java.lang.String
     */
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/promoteAdmin/{user_name}")
    public String promoteAdmin(@PathParam("user_name") String user_name) {
        //Declaring vars
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();
        String output="";
        try {
            this.db.promoteAdmin(user_name);
            output="Promoted";
            
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return output;

    }
    
    @POST
    @Path("/Login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String Login(String content) {
        JSONArray array = new JSONArray();
        JSONObject response = new JSONObject();
        u = convertJsonStringToUserLogin(content);
        boolean flag;
        boolean exists =db.checkIfExist(u.getUsername(), u.getEmail());
        try {
            u = convertJsonStringToUserLogin(content);
            for (User u : db.login(u.getUsername(), u.getPassword())) {
                //Converting the Post to a json obj
                JSONObject obj = convertUserToJson(u);
                //Adding the json obj to the json array
                if(u.getUsername()==null && u.getPassword()== null){
                    response.put("error", "please enter username and password");
                }
                if(!exists){
                     response.put("error", "invalid credentials! ,please check and try again");
                }
                array.add(obj);

            }
//            Putting the array into a json obj
            response.put("User", array);
        } catch (Exception e) {
            System.out.println(e);

            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();

    }

    /**
     * PUT method for updating or creating an instance of UserResource
     *
     * @param content representation for the resource
     */
    @PUT
    //@Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

//    @POST
//    @Path("/Register")
//    @Consumes(MediaType.TEXT_PLAIN)
//    public boolean registerUser(String a) {
//        u = convertJsonStringToUser(a);
//        boolean flag = false;
//
//        try {
//            u = convertJsonStringToUser(a);
//
//            flag = db.register(u.getUsername(), u.getPassword(), u.getEmail(), u.getFullName());
//        } catch (Exception e) {
//
//            System.out.println(e);
//        }
//
//        return flag;
//
//    }

    @POST
    @Path("/Register")
    @Consumes(MediaType.TEXT_PLAIN)
    public boolean registerUser(String a) {
        u = convertJsonStringToUser(a);
        boolean flag;
        boolean exists =db.checkIfExist(u.getUsername(), u.getEmail());
        try {
            //u = convertJsonStringToUser(a);
            if(!exists){
            flag = db.register(u.getUsername(), u.getPassword(), u.getEmail(), u.getFullName());
            return flag;
            }else{
                System.out.println("user exist");
                flag =false;
                return flag;
            }
        } catch (Exception e) {

            System.out.println(e);
        }

       // return flag;
        return false;

    }
}
