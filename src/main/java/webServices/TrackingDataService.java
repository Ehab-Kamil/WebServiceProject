/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import Utils.TrackingDataDTO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.json.JSONObject;
import pojo.TrackingData;
import webServicesHandlers.Handler;

/**
 *
 * @author yoka
 */
@Path("trackingData")
public class TrackingDataService {
    
    final String errorMessage = "error";
    final String successMessage = "success";
    @GET
    @Path("/add")
    @Produces("application/json")
    public String addTrackingData(
    @QueryParam("intialOdemeter") int intialOdemeter,
            @QueryParam("dataAdded") String dataAdded,
             @QueryParam("dataModified") String dataModified,
            @QueryParam("typeId")int typeId,@QueryParam("value") String value
            ,@QueryParam("vehicleId")int vehicleId
    )
    { 
      Handler handler=new Handler();
       JSONObject obj = new JSONObject();
      if(handler.insertTrackingData(intialOdemeter,dataAdded,dataModified,typeId,vehicleId,value))
      {
      TrackingDataDTO tdDTO=JsonConversion.convertToTrackingData(handler.getTrackingData());
     
        JSONObject obj1 = new JSONObject(tdDTO);
        obj.put("result", obj1);
        obj.put("status", successMessage);
        
      }
      else
      {
        obj.append("msg", "can't insert tracking data");
        obj.put("status", errorMessage);
      }
      return obj.toString();
    }
    @GET
    @Path("/getTrackingData")
    @Produces("application/json")
    public String getTrackingData(@QueryParam("vehicleId")int vehicleId)
    {
     Handler handler=new Handler();
       JSONObject obj = new JSONObject();
      List<TrackingData> trackingDatas=handler.getTrackingData(vehicleId);
     if(trackingDatas.size()>0)
     { for(TrackingData td:trackingDatas)
     {
     TrackingDataDTO tddto=JsonConversion.convertToTrackingData(td);
      JSONObject obj1 = new JSONObject(tddto);
     obj.append("result", obj1);
     
     }
        obj.put("status", successMessage);
        
      }
      else
      {
        obj.append("msg", "can't retrieve tracking data");
        obj.put("status", errorMessage);
      }
      return obj.toString();
}
}