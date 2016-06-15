/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import Utils.MeasuringUnitDTO;
import Utils.ModelDTO;
import Utils.ServiceDTO;
import Utils.TrackingDataDTO;
import Utils.TrimDTO;
import Utils.TripDTO;
import Utils.TypeDTO;
import Utils.UserDTO;
import Utils.VehicleDTO;
import Utils.VehicleModelDTO;
import Utils.YearDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pojo.TrackingData;
import pojo.Trips;
import pojo.Type;
import pojo.User;
import pojo.Vehicle;
import pojo.VehicleModel;

/**
 *
 * @author yoka
 */
public class JsonConversion {
       public static UserDTO convertUserToUserJson(User u) {
        List<VehicleDTO> vehiclesRes = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>(u.getVehicles());
        UserDTO udto = new UserDTO();
        udto.setEmail(u.getEmail());
        udto.setFirstName(u.getFirstName());
        udto.setLastName(u.getLastName());
        udto.setPassword(u.getPassword());
        udto.setId(u.getId());
        udto.setUsername(u.getUsername());
        udto.setPhone(u.getPhone());
        for (int i = 0; i < vehicles.size(); i++) {
            VehicleDTO v = new VehicleDTO();
            v.setId(vehicles.get(i).getId());
            v.setCurrentOdemeter(vehicles.get(i).getCurrentOdemeter());
            v.setIntialOdemeter(vehicles.get(i).getIntialOdemeter());
            v.setName(vehicles.get(i).getName());
            v.setLicencePlate(vehicles.get(i).getLicencePlate());
            
            /**
             * ******************VehicleModel*******************
             */
            VehicleModelDTO vmdto = new VehicleModelDTO();
            
            ModelDTO mdto = new ModelDTO();
            VehicleModel vehicleModel = vehicles.get(i).getVehicleModel();
            mdto.setId(vehicleModel.getModel().getId());
            mdto.setName(vehicleModel.getModel().getName());
            mdto.setNiceName(vehicleModel.getModel().getNiceName());
            
            YearDTO ydto = new YearDTO();
            ydto.setId(vehicleModel.getYear().getId());
            ydto.setName(vehicleModel.getYear().getName());
            
            TrimDTO tdto = new TrimDTO();
            tdto.setId(vehicleModel.getTrim().getId());
            tdto.setName(vehicleModel.getTrim().getName());
            vmdto.setId(vehicleModel.getId());
            vmdto.setModel(mdto);
            vmdto.setTrim(tdto);
            vmdto.setYear(ydto);
            
            v.setVehicleModel(vmdto);
            /**
             * ******************TrackingData*******************
             */
            List<TrackingData> trackingDatas = new ArrayList<>(vehicles.get(i).getTrackingDatas());
            List<TrackingDataDTO> tddtos = new ArrayList<>();
            for (TrackingData td : trackingDatas) {
                TrackingDataDTO tddto = new TrackingDataDTO();
                tddto.setId(td.getId());
                tddto.setDateAdded(td.getDateAdded());
                tddto.setDateModified(td.getDateModified());
                tddto.setIntialOdemeter(td.getIntialOdemeter());
                tddto.setValue(td.getValue());
                Type t = td.getType();
                TypeDTO typeDTO = new TypeDTO();
                typeDTO.setId(t.getId());
                typeDTO.setName(t.getName());
                MeasuringUnitDTO mu =new MeasuringUnitDTO();
                mu.setId(t.getMeasuringUnit().getId());
                mu.setName(t.getMeasuringUnit().getName());
                mu.setSuffix(t.getMeasuringUnit().getSuffix());
                typeDTO.setMeasuringUnit(mu);
                ServiceDTO service =new ServiceDTO();
                service.setId(t.getService().getId());
                service.setName(t.getService().getName());
                typeDTO.setService(service);
                
                tddto.setType(typeDTO);
                
                tddtos.add(tddto);
                
            }
            
            /*******************Trips**********************/
            List<Trips> trips = new ArrayList<>(vehicles.get(i).getTripses());
            List<TripDTO> tripDtos = new ArrayList<>();
            for(Trips trip:trips)
            {
                TripDTO tripDTO=new TripDTO();
                tripDTO.setId(trip.getId());
                tripDTO.setIntialOdemeter(trip.getIntialOdemeter());
                tripDTO.setCoveredMilage(trip.getCoveredMilage());
                
                tripDtos.add(tripDTO);
            }
            v.setTrips(tripDtos);
            v.setTrackingDatas(tddtos);
            if (Objects.equals(u.getId(), vehicles.get(i).getUser().getId())) {
                v.setAdminFlag(true);
            } else {
                v.setAdminFlag(false);
            }
            udto.getVehicles().add(v);
        }
        return udto;
    }
    
}
