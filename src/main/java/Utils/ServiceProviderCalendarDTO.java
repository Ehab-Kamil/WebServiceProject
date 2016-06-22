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
public class ServiceProviderCalendarDTO {
    private Integer id;
     private Date startingHour;
     private Date endingHour;
     private DayDTO day;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(Date startingHour) {
        this.startingHour = startingHour;
    }

    public Date getEndingHour() {
        return endingHour;
    }

    public void setEndingHour(Date endingHour) {
        this.endingHour = endingHour;
    }

    public DayDTO getDayDTO() {
        return day;
    }

    public void setDayDTO(DayDTO dayDTO) {
        this.day = dayDTO;
    }

}
