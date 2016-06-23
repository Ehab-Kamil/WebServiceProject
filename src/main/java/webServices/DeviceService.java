/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import com.google.gson.Gson;
import facadePkg.DataLayer;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.json.JSONArray;
import org.json.JSONObject;
import pojo.Device;
import webServicesHandlers.Handler;

/**
 *
 * @author yoka
 */
@Path("/device")
public class DeviceService {

    Handler h;
    Gson gson;
    final String errorMessage = "error";
    final String successMessage = "success";

    public DeviceService() {
        h = new Handler();
        gson = new Gson();
    }
    
    @Path("/add")
    @GET
    @Produces("application/json")
    public String addDevice(@QueryParam("userId") int userId, @QueryParam("token") String token) {

        if (h.addDevice(userId, token)) {
            JSONObject obj = new JSONObject();
            obj.put("result", token);
            obj.put("status", successMessage);
            return obj.toString();
        } else {
            JSONObject obj = new JSONObject();
            obj.put("result", "Device wasn't Inserted");
            obj.put("status", errorMessage);
            return obj.toString();
        }
    }
@Path("/get")
    @GET
    @Produces("application/json")
    public String getDevices() {
        DataLayer dataLayer=new DataLayer();
        List<Device> lst= dataLayer.getAllDevices();
       
        if (lst.size() > 0) {
            List<Device> deviceResult = new ArrayList<>();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < lst.size(); i++) {
                Device d = lst.get(i);
                d.setUser(null);
                deviceResult.add(d);
               JSONObject obj1 = new JSONObject(deviceResult.get(i));
            
            obj.append("result", obj1); 
            }
           
           // JSONObject obj1 = new JSONObject(deviceResult);
            
          //  obj.put("result", obj1);
            obj.put("status", successMessage);
            return obj.toString();
        } else {
            JSONObject obj = new JSONObject();
            obj.put("result", "Couldn't Load Data");
            obj.put("status", errorMessage);
            return obj.toString();
        }
    }
    

}
