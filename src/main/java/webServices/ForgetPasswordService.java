/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.json.JSONObject;

import webServicesHandlers.Handler;

/**
 *
 * @author yoka
 */
@Path("/forget")
public class ForgetPasswordService {

    final String errorMessage = "error";
    final String successMessage = "success";

    @GET
    @Produces("application/json")
    public void updateUserPassword(@QueryParam("email") String email, @QueryParam("password") String password) {
        Handler handler = new Handler();
        if (handler.updateUserPassword(email, password)) {
            JSONObject obj = new JSONObject();
            obj.append("status", successMessage);
        }
    }
}
