/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import Utils.ServiceProviderDTO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.json.JSONObject;
import pojo.ServiceProvider;
import webServicesHandlers.Handler;

/**
 *
 * @author yoka
 */
@Path("/serviceProvider")
public class ServiceProviderService {

    Handler handler;

    public ServiceProviderService() {
        handler = new Handler();
    }

    @Path("/getServiceProviders")
    @GET
    @Produces("application/json")
    public String getServiceProviders() {
        List<ServiceProvider> list = handler.getServiceProviders();
        JSONObject obj = new JSONObject();
        List<ServiceProviderDTO> dTOs = JsonConversion.convertToServiceProviderJson(list);
        if (dTOs.size() > 0) {
            obj.put("result", dTOs);
            obj.put("status", "success");
        } else {
            obj.put("msg", "no serviceProviders");
            obj.put("status", "error");
        }
        return obj.toString();
    }

    @Path("/getBranches")
    @GET
    @Produces("application/json")
    public String getServiceProviderBranches(@QueryParam("serviceProviderId") int serviceProviderId) {
        List<ServiceProvider> list = handler.getServiceProviderBranches(serviceProviderId);
        JSONObject obj = new JSONObject();
        List<ServiceProviderDTO> dTOs = JsonConversion.convertToServiceProviderJson(list);
        if (dTOs.size() > 0) {
            obj.put("result", dTOs);
            obj.put("status", "success");
        } else {
            obj.put("msg", "no branches for this serviceProvider");
            obj.put("status", "error");
        }
        return obj.toString();
    }
}
