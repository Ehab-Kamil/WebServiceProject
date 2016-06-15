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

/**
 *
 * @author Ehab
 */
@Path("/trip")
public class TripServiceEhab {

    @Path("/addNew")
    @GET
    @Produces("application/json")
    public String addTrip(@QueryParam("initial") String initial, @QueryParam("covered") String covered,
            @QueryParam("vehicle_id") String vehicle) {
        int initialOdemeter = Integer.parseInt(initial);
        int coveredMilage = Integer.parseInt(covered);
        int vehicleId = Integer.parseInt(vehicle);

        return null;
    }

    @Path("/addCoordinates")
    @GET
    @Produces("application/json")
    public String addCoordinatesToTrip() {
        return null;
    }
}
