/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import pojo.Model;
import pojo.Trim;
import pojo.Year;

/**
 *
 * @author yoka
 */
public class VehicleModelDTO {
     private Integer id;
     private ModelDTO model;
     private TrimDTO trim;
     private YearDTO year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModelDTO getModel() {
        return model;
    }

    public void setModel(ModelDTO model) {
        this.model = model;
    }

    public TrimDTO getTrim() {
        return trim;
    }

    public void setTrim(TrimDTO trim) {
        this.trim = trim;
    }

    public YearDTO getYear() {
        return year;
    }

    public void setYear(YearDTO year) {
        this.year = year;
    }
}
