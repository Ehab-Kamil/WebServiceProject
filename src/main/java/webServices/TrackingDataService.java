/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import webServicesHandlers.Handler;

/**
 *
 * @author yoka
 */
@Path("trackingData")
public class TrackingDataService {
    
    @GET
    @Path("/add")
    public void addTrackingData(
    @QueryParam("intialOdemeter") int intialOdemeter,
            @QueryParam("dataAdded") String dataAdded,
             @QueryParam("dataModified") String dataModified,
            @QueryParam("typeId")int typeId,@QueryParam("value") String value
            ,@QueryParam("vehicleId")int vehicleId
    )
    { 
      Handler handler=new Handler();
      
      handler.insertTrackingData(intialOdemeter,dataAdded,dataModified,typeId,vehicleId,value);
    }
}
