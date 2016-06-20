/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pojo.Address;
import pojo.Make;
import pojo.ServiceProvider;
import pojo.ServiceProviderCalendar;
import pojo.ServiceProviderPhone;
import pojo.ServiceProviderServices;

/**
 *
 * @author yoka
 */
public class ServiceProviderDTO {
     private Integer id;
     private String name;
     private String email;
     private String website;
     private AddressDTO address;
     private List<ServiceProviderCalendarDTO> serviceProviderCalendars = new ArrayList<>();
     private List<MakeDTO> makes = new ArrayList<>();
     private List<ServiceProviderPhoneDTO> serviceProviderPhones = new ArrayList<>();
    private List<ServiceProviderServicesDTO> serviceProviderServices=new ArrayList<>();

    public List<ServiceProviderServicesDTO> getServiceProviderServices() {
        return serviceProviderServices;
    }

    public void setServiceProviderServices(List<ServiceProviderServicesDTO> serviceProviderServices) {
        this.serviceProviderServices = serviceProviderServices;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<ServiceProviderCalendarDTO> getServiceProviderCalendars() {
        return serviceProviderCalendars;
    }

    public void setServiceProviderCalendars(List<ServiceProviderCalendarDTO> serviceProviderCalendars) {
        this.serviceProviderCalendars = serviceProviderCalendars;
    }

    public List<MakeDTO> getMakes() {
        return makes;
    }

    public void setMakes(List<MakeDTO> makes) {
        this.makes = makes;
    }

    public List<ServiceProviderPhoneDTO> getServiceProviderPhones() {
        return serviceProviderPhones;
    }

    public void setServiceProviderPhones(List<ServiceProviderPhoneDTO> serviceProviderPhones) {
        this.serviceProviderPhones = serviceProviderPhones;
    }
}
