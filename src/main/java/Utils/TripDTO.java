/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author yoka
 */
public class TripDTO {
     private Integer id;
     private int coveredMilage;
     private int intialOdemeter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCoveredMilage() {
        return coveredMilage;
    }

    public void setCoveredMilage(int coveredMilage) {
        this.coveredMilage = coveredMilage;
    }

    public int getIntialOdemeter() {
        return intialOdemeter;
    }

    public void setIntialOdemeter(int intialOdemeter) {
        this.intialOdemeter = intialOdemeter;
    }
}
