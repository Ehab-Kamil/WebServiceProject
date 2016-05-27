/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yoka
 */
public class Handler {

    boolean login(String user, String pass) {
        return user.equals("aya");
    }

    User getUser(String user, String pass) {
       return new User(user,pass);
    }

    boolean userExists(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int register(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Make> getMake() {
        List<Make> lst=new ArrayList<>();
       for (int i=0;i<5;i++)
       {
           Make e=new Make();
           e.setName("make "+i);
        lst.add(e);
       }
       return lst;
    }

    List<Model> getModel(String make) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Trim> getTrim(String model, String year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Year> getYear(String model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Year> addVehicle(String make, String model, String year, String trim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
