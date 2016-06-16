/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
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
@Path("/tripService")
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
            @QueryParam("initialOdemeter") int initialOdemeter, @QueryParam("coveredMilage") int coveredMilage) {
        Trips trips = handler.addTrip(initialOdemeter, coveredMilage, vehicleId);
        JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject(trips);
        obj.append("result", obj1);
        obj.append("status", successMessage);
        return obj.toString();
    }
    @Path("/addCoordinates")
    @GET
    @Produces("application/json")
    public String addCoordinates(@QueryParam("vehicleId") int vehicleId,
            @QueryParam("longitude") float longitude, @QueryParam("latitude") float latitude, @QueryParam("tripId") int tripId) {
        Coordinates coordinates = handler.addCoordinatesToTrip(longitude, latitude, tripId);
        JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject(coordinates);
        obj.append("result", obj1);
        obj.append("status", successMessage);
        return obj.toString();
    }

    @POST
    public String upload(@FormParam("image") String img, @FormParam("userId") int userId, @FormParam("fileExt") String fileExt) {
        byte[] arr = DatatypeConverter.parseBase64Binary(img);
        System.out.println("byte >>" + arr.length);
        JSONObject obj = new JSONObject();
        obj.put("result", "done");
        try {
            writeToFile(arr, fileExt, userId);
        } catch (IOException ex) {
            Logger.getLogger(ProfilePictureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj.toString();
    }

    private void writeToFile(byte[] array, String ext, int userId) throws IOException {
        String image = "/Users/yoka/desktop/Uploaded/pic" + userId + "." + ext;
        try (FileOutputStream fileOuputStream = new FileOutputStream(image)) {
            fileOuputStream.write(array);
        }
        handler.insertPictureToUserAccount(image, userId);
    }
}
