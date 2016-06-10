/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import webServicesHandlers.Handler;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.json.JSONObject;
import pojo.Make;
import pojo.Model;
import pojo.Trim;
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
                // mk.setServiceProviders(null);
                mkresult.add(mk);
            }
            String x = gson.toJson(mkresult);
            return x;
        } else {
            JSONObject obj = new JSONObject();
            obj.append("msg", "400");
            obj.append("msg", "no supported trims");
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
            String x = gson.toJson(mkresult);
            //String x = gson.toJson(lst);
            return x;
        } else {
            JSONObject obj = new JSONObject();
            obj.append("msg", "400");
            obj.append("msg", "no supported models");
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
            String x = gson.toJson(trimresult);
            return x;
        } else {
            JSONObject obj = new JSONObject();
            obj.append("msg", "400");
            obj.append("msg", "no supported trims");
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
            String x = gson.toJson(yearResult);

            return x;
        } else {
            JSONObject obj = new JSONObject();
            obj.append("msg", "400");
            obj.append("msg", "no supported years");
            return obj.toString();
        }
    }

    @POST
    @Produces("application/json")

    public String addVehicle(@FormParam("model") String model, @FormParam("year") String year,
            @FormParam("trim") String trim, @FormParam("userId") int uId, @FormParam("carName") String carName, @FormParam("intialOdemeter") int intialOdemeter) {
        boolean lst = handler.addVehicle(model, year, trim, uId, carName, intialOdemeter);
        if (lst) {
            JSONObject obj = new JSONObject();
            obj.append("msg", "200");
            return obj.toString();
        } else {
            JSONObject obj = new JSONObject();
            obj.append("msg", "400");
            return obj.toString();
        }

    }
}
