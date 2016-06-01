/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import com.google.gson.Gson;
import java.util.List;
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
    Handler handler=new  Handler();
    Gson gson=new Gson();
   @GET
   @Path("/makes")
   @Produces("application/json")
    public String getMakes()
    {
        List<Make> lst=handler.getMake();
       String x= gson.toJson(lst);
        return x;
    
    }
    @GET
   @Path("/models")
    @Produces("application/json")
    public String getModels(@QueryParam("make")String make)
    {
        List<Model> lst=handler.getModelByMake(make);
         String x= gson.toJson(lst);
    return x;
    }
    @GET
   @Path("/trim")
    @Produces("application/json")
    public String getTrim(@QueryParam("model")String model,@QueryParam("year")String year)
    {
        List<Trim> lst=handler.getTrim(model,year);
        String x=gson.toJson(lst);
    return x;
    }
      @GET
   @Path("/year")
      @Produces("application/json")
    public String getYear(@QueryParam("model")String model)
    {
        List<Year> lst=handler.getYear(model);
    return null;
    }
     @POST
 //  @Path("/{make}/{model}/{year}/{trim}")
      @Produces("application/json")
   
    public String addVehicle(@QueryParam("make")String make,@QueryParam("model")String model,@QueryParam("year")String year,@QueryParam("trim")String trim)
    {
       boolean lst=handler.addVehicle(make,model,year,trim);
   if(lst)
         {
         JSONObject obj=new JSONObject();
              obj.append("msg", "200");
              return obj.toString();
         }
   else{
   JSONObject obj=new JSONObject();
              obj.append("msg", "400");
              return obj.toString();
   }
    
    } 
}
