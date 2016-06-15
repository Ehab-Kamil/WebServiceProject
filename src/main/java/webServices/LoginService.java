/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import Utils.*;
import webServicesHandlers.Handler;
import com.google.gson.Gson;
import facadePkg.DataLayer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.json.JSONObject;
import pojo.User;
import pojo.Vehicle;

/**
 *
 * @author yoka
 */
@Path("/login")
public class LoginService {

    Gson gson = new Gson();
    Handler hand = new Handler();
    final String errorMessage = "error";
    final String successMessage = "success";

    @GET
    @Produces("application/json")
    public String LoginByUserName(@QueryParam("user") String user, @QueryParam("pass") String pass) {
        User u = hand.login(user, pass);
        if (u != null) {
            UserDTO udto = JsonConversion.convertUserToUserJson(u);
            JSONObject obj = new JSONObject();
            JSONObject obj1 = new JSONObject(udto);
            obj.append("result", obj1);
            obj.append("status", successMessage);
            return obj.toString();
        } else if (hand.userExists(user)) {
            //password  
            return JsonError.errorJsonObject("invalid password");
        } else {
            //user
            return JsonError.errorJsonObject("invalid user");
        }

    }

    @Path("/email")
    @GET
    @Produces("application/json")
    public String LoginByEmail(@QueryParam("email") String email, @QueryParam("pass") String pass) {

        String ret = "";
        User u = hand.loginByEmail(email, pass);
        if (u != null) {
              UserDTO udto = JsonConversion.convertUserToUserJson(u);
            JSONObject obj = new JSONObject();
            JSONObject obj1 = new JSONObject(udto);
            obj.put("result", obj1);

            obj.put("status", successMessage);
            return obj.toString();
        } else if (hand.emailExists(email)) {
            //password  
            return JsonError.errorJsonObject("invalid password");
        } else {
            //email
            return JsonError.errorJsonObject("invalid email");
        }

    }

    @Path("/facebookLogin")
    @GET
    @Produces("application/json")
    public String LoginByFacebook(@QueryParam("email") String email) {
        User u = hand.loginWithFacebook(email);
       
        if (u != null) {
             u.setPassword(" ");
            UserDTO udto = JsonConversion.convertUserToUserJson(u);
            JSONObject obj = new JSONObject();
            JSONObject obj1 = new JSONObject(udto);
            obj.append("result", obj1);
            obj.append("status", successMessage);
            return obj.toString();
        }  else {
            //user
            return JsonError.errorJsonObject("invalid email");
        }
    }

    @Path("/forgetPassword")
    @GET
    @Produces("application/json")
    public String forgetPassword(@QueryParam("email") String email) {

        DataLayer dataLayer = new DataLayer();
        dataLayer.sendForgetPasswordMail(email);
        JSONObject obj = new JSONObject();
        obj.append("error", "ay kalam");
        return obj.toString();
    }

}
