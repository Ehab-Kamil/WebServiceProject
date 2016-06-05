/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import webServicesHandlers.Handler;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.json.JSONObject;
import pojo.User;

/**
 *
 * @author yoka
 */
@Path("/login")
public class LoginService {
    Gson gson=new Gson();
    Handler hand=new Handler();
  // @Path("/{user}/{pass}")
    @GET
    @Produces("application/json")
    public String LoginByUserName(@QueryParam("user") String user,@QueryParam("pass") String pass)
    {
     String ret="";
     User u=hand.login(user,pass);
        if(u!=null)
        {
             ret=gson.toJson(u);
           
              return ret;
        }
        
        else if(hand.userExists(user))
        {
           //password  
       JSONObject obj=new JSONObject();
        obj.append("error", "invalid password");
        return obj.toString();
        }
        else{
            //user
        JSONObject obj=new JSONObject();
        obj.append("error", "invalid user");
        return obj.toString();}
              
    }
     @Path("/email")
    @GET
    @Produces("application/json")
    public String LoginByEmail(@QueryParam("email") String email,@QueryParam("pass") String pass)
    {
        
       
        String ret="";
         User u=hand.loginByEmail(email,pass);
        if(u!=null)
        {
        ret=gson.toJson(u);
               return ret;
        }
         else if(hand.emailExists(email))
        {
           //password  
       JSONObject obj=new JSONObject();
        obj.append("error", "invalid password");
        return obj.toString();
        }
        else{
            //user
        JSONObject obj=new JSONObject();
        obj.append("error", "invalid email");
        return obj.toString();}
           
    
    }
}
