/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Date;

/**
 *
 * @author yoka
 */
public class TrackingDataDTO {
    private Integer id;
     private TypeDTO type;
     private int intialOdemeter;
     private Date dateAdded;
     private Date dateModified;
     private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeDTO getType() {
        return type;
    }

    public void setType(TypeDTO type) {
        this.type = type;
    }

    public int getIntialOdemeter() {
        return intialOdemeter;
    }

    public void setIntialOdemeter(int intialOdemeter) {
        this.intialOdemeter = intialOdemeter;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
