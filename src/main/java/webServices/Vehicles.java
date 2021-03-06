/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import Utils.VehicleDTO;
import webServicesHandlers.Handler;
import com.google.gson.Gson;
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
import pojo.Make;
import pojo.Model;
import pojo.Trim;
import pojo.Vehicle;
import pojo.Year;

/**
 *
 * @author yoka
 */
@Path("/vehicle")
public class Vehicles {

    Handler handler;
    Gson gson;

    public Vehicles() {
        handler = new Handler();
        gson = new Gson();

    }

    @GET
    @Path("/makes")
    @Produces("application/json")
    public String getMakes() {
        List<Make> lst = handler.getMake();
        if (lst.size() > 0) {
            List<Make> mkresult = new ArrayList<>();
            for (int i = 0; i < lst.size(); i++) {
                Make mk = lst.get(i);
                mk.setModels(null);
                mk.setServiceProviders(null);
                mkresult.add(mk);
            }
            JSONObject obj1 = new JSONObject(mkresult);
            JSONObject obj = new JSONObject();
            obj.put("result", mkresult);
            obj.put("status", "success");
            return obj.toString();
        } else {
            JSONObject obj = new JSONObject();
            obj.put("msg", "no supported makes");
            obj.put("status", "error");
            return obj.toString();
        }
    }

    @GET
    @Path("/models")
    @Produces("application/json")
    public String getModels(@QueryParam("make") String make) {
        List<Model> lst = handler.getModelByMake(make);
        if (lst.size() > 0) {
            List<Model> mkresult = new ArrayList<>();
            for (int i = 0; i < lst.size(); i++) {
                Model model = lst.get(i);
                model.setMake(null);
                model.setVehicleModels(null);
                mkresult.add(model);
            }
            JSONObject obj1 = new JSONObject(mkresult);
            JSONObject obj = new JSONObject();
            obj.put("result", mkresult);
            obj.put("status", "success");
            return obj.toString();
        } else {
           JSONObject obj = new JSONObject();
            obj.put("msg", "no supported models");
            obj.put("status", "error");
            return obj.toString();
        }
    }

    @GET
    @Path("/trim")
    @Produces("application/json")
    public String getTrim(@QueryParam("model") String model, @QueryParam("year") String year) {
        List<Trim> lst = handler.getTrim(model, year);
        if (lst.size() > 0) {
            List<Trim> trimresult = new ArrayList<>();
            for (int i = 0; i < lst.size(); i++) {
                Trim trim = lst.get(i);
                trim.setVehicleModels(null);
                trimresult.add(trim);
            }
            JSONObject obj1 = new JSONObject(trimresult);
            JSONObject obj = new JSONObject();
            obj.put("result", trimresult);
            obj.put("status", "success");
            return obj.toString();
        } else {
           JSONObject obj = new JSONObject();
            obj.put("msg", "no supported trims");
            obj.put("status", "error");
            return obj.toString();
        }
    }

    @GET
    @Path("/year")
    @Produces("application/json")
    public String getYear(@QueryParam("model") String model) {
        List<Year> lst = handler.getYear(model);
        if (lst.size() > 0) {
            List<Year> yearResult = new ArrayList<>();
            for (int i = 0; i < lst.size(); i++) {
                Year year = lst.get(i);
                year.setVehicleModels(null);
                yearResult.add(year);
            }
            JSONArray obj1 = new JSONArray(yearResult.toArray());
            JSONObject obj = new JSONObject();
            obj.put("result", obj1);
            obj.put("status", "success");
            return obj.toString();

        } else {
           JSONObject obj = new JSONObject();
            obj.put("msg", "no supported years");
            obj.put("status", "error");
            return obj.toString();
        }
    }

    @GET
    @Produces("application/json")
@Path("/add")
    public String addVehicle(@QueryParam("model") String model, @QueryParam("year") String year,
            @QueryParam("trim") String trim, @QueryParam("userId") int uId, @QueryParam("carName") String carName,
            @QueryParam("intialOdemeter") int intialOdemeter,@QueryParam("licencePlate")String licencePlate) {
      if(!handler.lPlateisExists(licencePlate))
      {  Vehicle v = handler.addVehicle(model, year, trim, uId, carName, intialOdemeter,licencePlate);
        if (v != null) {
           VehicleDTO vdto= JsonConversion.convertVehicleToVehicleJson(v,1);
            JSONObject obj = new JSONObject();
            JSONObject obj1 = new JSONObject(vdto);
            obj.put("result", obj1);
            obj.put("status", "success");
            return obj.toString();
        } else {
            JSONObject obj = new JSONObject();
            obj.put("result", "error");
            obj.put("status", "error");
            return obj.toString();
        }
      }
      else{
       JSONObject obj = new JSONObject();
            obj.put("result", "licence plate entered before");
       obj.put("status", "error");
            return obj.toString();
      }

    }

    @GET
    @Path("/userVehicle")
    public String getVehicle(@QueryParam("userId") int userId) {
        List<Vehicle> vehicleList = handler.getVehiclesPerUser(userId);
        if (vehicleList.size() > 0) {
            JSONObject obj = new JSONObject();
            JSONObject obj1 = new JSONObject(vehicleList);
//    for(Vehicle v:vehicleList)
//            { System.out.println("v : "+v.getName());}
//    
//            obj.append("vehicles", obj1);
//            obj.append("status", "success");
//            return obj.toString();
   List<Vehicle> vehicleResult = new ArrayList<>();
            for (int i = 0; i < vehicleList.size(); i++) {
                Vehicle vehicle = vehicleList.get(i);
               vehicle.setUsers(null);
               vehicle.setUser(null);
              vehicle.setTripses(null);
              vehicle.setTrackingDatas(null);
              vehicle.setVehicleModel(null);
                vehicleResult.add(vehicle);
            }
return gson.toJson(vehicleResult);
        } else {
            JSONObject obj = new JSONObject();
            obj.append("status", "error");
            obj.append("msg", "No vehicles are found");
            return obj.toString();
        }
    }
}
