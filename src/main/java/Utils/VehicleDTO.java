/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import pojo.VehicleModel;

/**
 *
 * @author yoka
 */
public class VehicleDTO implements Serializable{

    private Integer id;
    private VehicleModelDTO vehicleModel;
    private String name;
    private String licencePlate;
    private int intialOdemeter;
    private int currentOdemeter;
    private boolean adminFlag;
   private List<TrackingDataDTO> trackingDatas=new ArrayList<>();
   private List<TripDTO> trips=new ArrayList<>();
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VehicleModelDTO getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModelDTO vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getIntialOdemeter() {
        return intialOdemeter;
    }

    public void setIntialOdemeter(int intialOdemeter) {
        this.intialOdemeter = intialOdemeter;
    }

    public int getCurrentOdemeter() {
        return currentOdemeter;
    }

    public void setCurrentOdemeter(int currentOdemeter) {
        this.currentOdemeter = currentOdemeter;
    }

    public boolean isAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(boolean adminFlag) {
        this.adminFlag = adminFlag;
    }

    public List<TrackingDataDTO> getTrackingDatas() {
        return trackingDatas;
    }

    public void setTrackingDatas(List<TrackingDataDTO> trackingDatas) {
        this.trackingDatas = trackingDatas;
    }

    public List<TripDTO> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDTO> trips) {
        this.trips = trips;
    }
    

}
