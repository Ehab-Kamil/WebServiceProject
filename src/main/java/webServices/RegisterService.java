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
import pojo.User;

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
          ,@PathParam("password")String password,@PathParam("firstName")String firstname
  ,@PathParam("lastName")String lastname,@PathParam("phone")String phone)
  {
  User u=new User();
  u.setUsername(username);
  u.setPassword(password);
  u.setEmail(email);
  u.setFirstName(firstname);
  u.setEmail(email);
  u.setLastName(lastname);
  u.setSuspended(0);
  Handler handler=new Handler();
  /*
  1.get username
  2.email
  */

  if(!handler.userExists(username))
  {
      if(!handler.emailExists(email))
  
      {  int x=handler.register(u);
     if(x==0)
         
          {
                   User u1=handler.login(username,password);
                    Gson gson=new Gson();
        
             return gson.toJson(u);

          }
     else{
      JSONObject obj=new JSONObject();
              obj.append("msg", "error entering data try again");
              return obj.toString();
     }
      }
          //error in username
         else
          {
              JSONObject obj=new JSONObject();
              obj.append("msg", "email exist");
              return obj.toString();
          }  
  }
          //error in email
         else
          {
              JSONObject obj=new JSONObject();
              obj.append("error", "username exist");
              return obj.toString();
          }
      }
          
  }

