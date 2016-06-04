/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import com.google.gson.Gson;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@Path("/register")
public class RegisterService {

    @GET
    @Produces("application/json")
    public String Register(@QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password, @QueryParam("firstName") String firstname, @QueryParam("lastName") String lastname, @QueryParam("phone") String phone) {
        User u = new User();
      if(firstname.length()>0){
        if (username.length() > 0) {
            if (password.length() >= 6) {
                if (structured(email)) {
                    if (phone.length() == 11) {
                        u.setUsername(username);
                        u.setPassword(password);
                        u.setEmail(email);
                        u.setFirstName(firstname);
                        u.setEmail(email);
                        u.setLastName(lastname);
                        u.setSuspended(0);
                        Handler handler = new Handler();
                        /*
  1.get username
  2.email
                         */

                        if (!handler.userExists(username)) {
                            if (!handler.emailExists(email)) {
                                int x = handler.register(u);
                                if (x == 0) {
                                    Gson gson = new Gson();
                                    return gson.toJson(u);
                                } else {
                                    JSONObject obj = new JSONObject();
                                    obj.append("msg", "error entering data try again");
                                    return obj.toString();
                                }
                            } //error in username
                            else {
                                JSONObject obj = new JSONObject();
                                obj.append("msg", "email exist");
                                return obj.toString();
                            }
                        } //error in email
                        else {
                            JSONObject obj = new JSONObject();
                            obj.append("error", "username exist");
                            return obj.toString();
                        }
                    } else {
                        //phone is not correct
                          JSONObject obj = new JSONObject();
                            obj.append("error", "phone length is not correct");
                            return obj.toString();
                    }
                } else {
                    //email is not well formatted
                      JSONObject obj = new JSONObject();
                            obj.append("error", "email in not well formated");
                            return obj.toString();
                }
            } else {//password coming not formated
                  JSONObject obj = new JSONObject();
                            obj.append("error", "password length is short ");
                            return obj.toString();

            }
        } else {//error username coming empty
              JSONObject obj = new JSONObject();
                            obj.append("error", "username not filled");
                            return obj.toString();

        }}
      else{
      //error in firstname
       JSONObject obj = new JSONObject();
                            obj.append("error", "first name not filled");
                            return obj.toString();

      }

    }

    private boolean structured(String email) {
      //email is regex or not
      return validate(email);
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
}


}
