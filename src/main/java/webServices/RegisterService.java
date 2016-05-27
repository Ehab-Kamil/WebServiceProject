/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;

/**
 *
 * @author yoka
 */
@Path("/register")
public class RegisterService {
     
    
    @Path("/{username}/{email}/{password}/{firstName}/{lastName}/{phone}")
    @GET
 @Produces("application/json")
  public String Register(@PathParam("username")String username,@PathParam("email")String email 
          ,@PathParam("password")String password,@PathParam("firstname")String firstname
  ,@PathParam("lastname")String lastname,@PathParam("phone")String phone)
  {
  User u=new User();
  u.setU(username);
  u.setP(password);
  u.setEmail(email);
  u.setFname(firstname);
  u.setEmail(email);
  u.setLname(lastname);
  Handler handler=new Handler();
          int x=handler.register(u);
      switch (x) { 
         //user is done
          case 0:
         
          {
              JSONObject obj=new JSONObject();
              obj.append("msg", "200");
              return obj.toString();}
          //error in username
          case 1:
          {
              JSONObject obj=new JSONObject();
              obj.append("msg", "403");
              return obj.toString();
          }  
          //error in email
          default:
          {
              JSONObject obj=new JSONObject();
              obj.append("error", "404");
              return obj.toString();
          }
      }
          
  }
}