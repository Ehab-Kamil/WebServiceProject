/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import pojo.MeasuringUnit;
import pojo.Service;

/**
 *
 * @author yoka
 */
public class TypeDTO {
     private Integer id;
     private MeasuringUnitDTO measuringUnit;
     private ServiceDTO service;
     private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MeasuringUnitDTO getMeasuringUnit() {
        return measuringUnit;
    }

    public void setMeasuringUnit(MeasuringUnitDTO measuringUnit) {
        this.measuringUnit = measuringUnit;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
