/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import webServicesHandlers.Handler;
import com.google.gson.Gson;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.json.JSONException;
import org.json.JSONObject;
import pojo.User;

/**
 *
 * @author yoka
 */
@Path("/register")
public class RegisterService {

    final String errorMessage = "error";
    final String successMessage = "success";

    @GET
    @Produces("application/json")
    public String Register(@QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password, @QueryParam("firstName") String firstname, @QueryParam("lastName") String lastname, @QueryParam("phone") String phone) {
        User u = new User();
        if (firstname.length() > 0) {
            if (username.length() > 0) {
                if (password.length() >= 6) {
                    if (structured(email)) {

                        u.setUsername(username);
                        u.setPassword(password);
                        u.setFirstName(firstname);
                        u.setEmail(email);
                        u.setLastName(lastname);
                        u.setSuspended(0);
                        Handler handler = new Handler();
                        /*
  1.get username
  2.email
                         */
                        return addUserCheckUserNameAndEmail(handler, username, email, u);

                    } else {
                        return JsonError.errorJsonObject("email in not well formated");
                    }
                } else {//password coming not formated
                    return JsonError.errorJsonObject("password length is short");

                }
            } else {//error username coming empty
                return JsonError.errorJsonObject("username not filled");

            }
        } else {
            //error in firstname
            return JsonError.errorJsonObject("first name not filled");

        }

    }
    @Path("/fb")
     @GET
    @Produces("application/json")
     
     public String RegisterWithFacebook(@QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("firstName") String firstname, @QueryParam("lastName") String lastname, @QueryParam("phone") String phone)
     {
      User u = new User();
        if (firstname.length() > 0) {
            if (username.length() > 0) {
                    if (structured(email)) {

                        u.setUsername(username);
                        u.setPassword("fbp");
                        u.setFirstName(firstname);
                        u.setEmail(email);
                        u.setLastName(lastname);
                        u.setSuspended(0);
                        Handler handler = new Handler();
                        /*
  1.get username
  2.email
                         */
                        return addUserCheckUserNameAndEmail(handler, username, email, u);

                    } else {
                        return JsonError.errorJsonObject("email in not well formated");
                    }
                
            } else {//error username coming empty
                return JsonError.errorJsonObject("username not filled");

            }
        } else {
            //error in firstname
            return JsonError.errorJsonObject("first name not filled");

        }
     }

 

    private String addUserCheckUserNameAndEmail(Handler handler, String username, String email, User u) throws JSONException {
        if (!handler.userExists(username)) {
            if (!handler.emailExists(email)) {
                u = handler.register(u);
                if (u!=null) {
                   if(u.getPassword().equalsIgnoreCase("fbp"))
                   {
                   u.setPassword("");
                   }
                    JSONObject obj = new JSONObject();
                    JSONObject obj1 = new JSONObject(u);
                    obj.put("result", obj1);
                    obj.put("status", successMessage);
                    return obj.toString();
                } else {
                    return JsonError.errorJsonObject("error entering data try again");
                }
            } //error in username
            else {
                return JsonError.errorJsonObject("email exist");
            }
        } //error in email
        else {
            return JsonError.errorJsonObject("username exist");
        }
    }

    private boolean structured(String email) {
        //email is regex or not
        return validate(email);
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

}
