/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import Utils.TripDTO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONObject;
import pojo.Coordinates;
import pojo.Trips;
import webServicesHandlers.Handler;

/**
 *
 * @author yoka
 */
@Path("/trip")
public class TripService {

    final String errorMessage = "error";
    final String successMessage = "success";

    Handler handler;

    public TripService() {
        handler = new Handler();
    }
    @Path("/addTrip")
    @GET
    @Produces("application/json")
    public String addTrip(@QueryParam("vehicleId") int vehicleId,
            @QueryParam("initialOdemeter") int initialOdemeter,
            @QueryParam("coveredMilage") int coveredMilage) {
        Trips trips = handler.addTrip(initialOdemeter, coveredMilage, vehicleId);
        TripDTO tripDTO=JsonConversion.convertToTripJson(trips);
       JSONObject obj = new JSONObject();
        if(trips!=null)
        {JSONObject obj1 = new JSONObject(tripDTO);
        obj.put("result", obj1);
        obj.put("status", successMessage);
        } else{
                obj.put("status", errorMessage);}
        return obj.toString();
    }
    @Path("/addCoordinates")
    @GET
    @Produces("application/json")
    public String addCoordinates(@QueryParam("vehicleId") int vehicleId,
            @QueryParam("longitude") float longitude,
            @QueryParam("latitude") float latitude, 
            @QueryParam("tripId") int tripId) {
        Coordinates coordinates = handler.addCoordinatesToTrip(longitude, latitude, tripId);
        JSONObject obj = new JSONObject();
        if(coordinates!=null)
        { JSONObject obj1 = new JSONObject(coordinates);
        obj.put("result", obj1);
        obj.put("status", successMessage);
        }
        else{
        obj.put("status", errorMessage);
        }
        return obj.toString();
    }

    @POST
    public String upload(@FormParam("image") String img, @FormParam("tripId") int tripId, @FormParam("fileExt") String fileExt) {
        byte[] arr = DatatypeConverter.parseBase64Binary(img);
        System.out.println("byte >>" + arr.length);
        JSONObject obj = new JSONObject();
        obj.put("result", "done");
        try {
            writeToFile(arr, fileExt, tripId);
        } catch (IOException ex) {
            Logger.getLogger(ProfilePictureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj.toString();
    }

    private void writeToFile(byte[] array, String ext, int tripId) throws IOException {
        String image = "/Users/yoka/desktop/Uploaded/Trips/pic" + tripId + "." + ext;
        try (FileOutputStream fileOuputStream = new FileOutputStream(image)) {
            fileOuputStream.write(array);
        }
        handler.insertImageToTrip(image, tripId);
    }
}
