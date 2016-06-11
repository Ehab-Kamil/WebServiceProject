/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import webServicesHandlers.Handler;
import com.google.gson.Gson;
import facadePkg.DataLayer;
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

    Gson gson = new Gson();
    Handler hand = new Handler();
    // @Path("/{user}/{pass}")

    @GET
    @Produces("application/json")
    public String LoginByUserName(@QueryParam("user") String user, @QueryParam("pass") String pass) {
        String ret = "";
        User u = hand.login(user, pass);
        if (u != null) {
            ret = gson.toJson(u);
            JSONObject obj = new JSONObject();
            obj.put("result", ret);
            obj.put("status", "success");
            return obj.toString();
       
           
        } else if (hand.userExists(user)) {
            //password  
            JSONObject obj = new JSONObject();
            obj.put("error", "invalid password");
            return obj.toString();
        } else {
            //user
            JSONObject obj = new JSONObject();
            obj.append("error", "invalid user");
            return obj.toString();
        }

    }

    @Path("/email")
    @GET
    @Produces("application/json")
    public String LoginByEmail(@QueryParam("email") String email, @QueryParam("pass") String pass) {

        String ret = "";
        User u = hand.loginByEmail(email, pass);
        if (u != null) {
            ret = gson.toJson(u);
            //return ret;
              JSONObject obj = new JSONObject();
            obj.append("result", ret);
            obj.append("status", "success");
            return obj.toString();
        } else if (hand.emailExists(email)) {
            //password  
            JSONObject obj = new JSONObject();
            obj.append("error", "invalid password");
            return obj.toString();
        } else {
            //user
            JSONObject obj = new JSONObject();
            obj.append("error", "invalid email");
            return obj.toString();
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
