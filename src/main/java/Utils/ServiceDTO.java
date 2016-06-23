/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.List;

/**
 *
 * @author yoka
 */
public class ServiceDTO {
    private int id;
    private String name;
    private List<Typedt> trackingType;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrackingType(List<Typedt> trackingType) {
        this.trackingType = trackingType;
    }

    public List<Typedt> getTrackingType() {
        return trackingType;
    }

    
}
