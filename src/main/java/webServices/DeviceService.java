/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    public DeviceService() {
        h = new Handler();
        gson =new Gson();
    }

    @POST
    @Produces("application/json")
    public String addDevice(@FormParam("userId") int userId, @FormParam("token") String token) {
        
        if (h.addDevice(userId, token)) {
            JSONObject obj = new JSONObject();
             obj.append("msg", "200");
            obj.append("msg", "device is inserted");
            return obj.toString();
        } else {
            JSONObject obj = new JSONObject();
              obj.append("error", "400");
            obj.append("error", "device isn't inserted");
            return obj.toString();
        }
    }
    
    @GET
     @Produces("application/json")
    public String getDevices(@QueryParam("userId") int userId)
    {
   List<Device> lst= h.getDeviceByUser(userId);
     if(lst.size()>0)
     {
         List<Device> deviceResult = new ArrayList<>();
            for (int i = 0; i < lst.size(); i++) {
                Device d = lst.get(i);
                d.setUser(null);
                deviceResult.add(d);
            }
         String x = gson.toJson(deviceResult);

            return x;
        } else {
            JSONObject obj = new JSONObject();
            obj.append("msg", "400");
            obj.append("msg", "no devices for this user");
            return obj.toString();
        }
    }

}
