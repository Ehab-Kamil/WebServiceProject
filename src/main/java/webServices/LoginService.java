/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;

/**
 *
 * @author yoka
 */
@Path("/login")
public class LoginService {
    Gson gson=new Gson();
    Handler hand=new Handler();
   @Path("/{user}/{pass}")
    @GET
    @Produces("application/json")
    public String LoginByUserName(@PathParam("user") String user,@PathParam("pass") String pass)
    {
     String ret="";
        if(hand.login(user,pass))
        {
           User u=hand.getUser(user,pass);
          
             ret=gson.toJson(u);
           
              return ret;
        }
        
        else if(hand.userExists(user))
        {
           //username  
       JSONObject obj=new JSONObject();
        obj.append("error", "403");
        return obj.toString();
        }
        else{
            //password
        JSONObject obj=new JSONObject();
        obj.append("error", "404");
        return obj.toString();}
              
    }
     @Path("/email/{email}/{pass}")
    @GET
    @Produces("application/json")
    public String LoginByEmail(@PathParam("user") String email,@PathParam("pass") String pass)
    {
        
       
        String ret="";
        if(hand.login(email, pass))
        {
            User u=new User("aya@gmail.com","aya");
            
             ret=gson.toJson(u);
               return ret;
        }
        else
        {
            
       JSONObject obj=new JSONObject();
        obj.append("error", "403");
        return obj.toString();
        } 
    
    }
}
