/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import Utils.CoordinatesDTO;
import Utils.MakeDTO;
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
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pojo.Coordinates;
import pojo.Make;
import pojo.TrackingData;
import pojo.Trips;
import pojo.Type;
import pojo.User;
import pojo.Vehicle;
import pojo.VehicleModel;
import webServicesHandlers.Handler;

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
            Vehicle v = vehicles.get(i);
            VehicleDTO vdto = convertVehicleToVehicleJson(v,0);

            if (Objects.equals(u.getId(), vehicles.get(i).getUser().getId())) {
                vdto.setAdminFlag(true);
            } else {
                vdto.setAdminFlag(false);
            }
            udto.getVehicles().add(vdto);
        }
        return udto;
    }

    static VehicleDTO convertVehicleToVehicleJson(Vehicle v,int num) {
    VehicleDTO vdto = new VehicleDTO();
        vdto.setId(v.getId());
        vdto.setCurrentOdemeter(v.getCurrentOdemeter());
        vdto.setIntialOdemeter(v.getIntialOdemeter());
        vdto.setName(v.getName());
        vdto.setLicencePlate(v.getLicencePlate());

        /**
         * ******************VehicleModel*******************
         */
        VehicleModelDTO vmdto = new VehicleModelDTO();

        ModelDTO mdto = new ModelDTO();
        VehicleModel vehicleModel = v.getVehicleModel();
        mdto.setId(vehicleModel.getModel().getId());
        mdto.setName(vehicleModel.getModel().getName());
        mdto.setNiceName(vehicleModel.getModel().getNiceName());
        /**
         * *****************Make**************
         */
        Handler h=new Handler();
        
        Make make =h.getMakeByModel(vehicleModel.getModel().getId());
        MakeDTO makedto = new MakeDTO();
        makedto.setId(make.getId());
        makedto.setName(make.getName());
        makedto.setNiceName(make.getNiceName());
        mdto.setMake(makedto);
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

        vdto.setVehicleModel(vmdto);
        /**
         * ******************TrackingData*******************
         */
       if(num==0)
       { List<TrackingData> trackingDatas = new ArrayList<>(v.getTrackingDatas());
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
            MeasuringUnitDTO mu = new MeasuringUnitDTO();
            mu.setId(t.getMeasuringUnit().getId());
            mu.setName(t.getMeasuringUnit().getName());
            mu.setSuffix(t.getMeasuringUnit().getSuffix());
            typeDTO.setMeasuringUnit(mu);
            ServiceDTO service = new ServiceDTO();
            service.setId(t.getService().getId());
            service.setName(t.getService().getName());
            typeDTO.setService(service);

            tddto.setType(typeDTO);

            tddtos.add(tddto);

        }

        /**
         * *****************Trips*********************
         */
        List<Trips> trips = new ArrayList<>(v.getTripses());
        List<TripDTO> tripDtos = new ArrayList<>();
        for (Trips trip : trips) {
            TripDTO tripDTO = new TripDTO();
            tripDTO.setId(trip.getId());
            tripDTO.setIntialOdemeter(trip.getIntialOdemeter());
            tripDTO.setCoveredMilage(trip.getCoveredMilage());
            List<CoordinatesDTO> coordinatesDTO = new ArrayList<>();
            List<Coordinates> coors = new ArrayList<>(trip.getCoordinateses());
            for (Coordinates coor : coors) {
                CoordinatesDTO cdto = new CoordinatesDTO();
                cdto.setId(coor.getId());
                cdto.setLatitude(coor.getLatitude());
                cdto.setLongitude(coor.getLongitude());
                coordinatesDTO.add(cdto);
            }
            tripDTO.setCoordinates(coordinatesDTO);
            tripDtos.add(tripDTO);
        }
        vdto.setTrips(tripDtos);
        vdto.setTrackingDatas(tddtos);}
    return vdto;
    }
}
