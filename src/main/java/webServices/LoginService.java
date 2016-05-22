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
   @Path("/{user}/{pass}")
    @GET
    @Produces("application/json")
    public String LoginByUserName(@PathParam("user") String user,@PathParam("pass") String pass)
    {
     String ret="";
        if(user.equals("aya")&&(pass.equals("aya")))
        {
            User u=new User("aya","aya");
            
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
     @Path("/{email}/{pass}")
    @GET
    @Produces("application/json")
    public String LoginByEmail(@PathParam("user") String email,@PathParam("pass") String pass)
    {
        
       
        String ret="";
        if(email.equals("aya@gmail.com")&&(pass.equals("aya")))
        {
            User u=new User("aya","aya");
            
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
