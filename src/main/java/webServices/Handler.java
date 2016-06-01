/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import dao.ModelDao;
import dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import pojo.Make;
import pojo.Model;
import pojo.Trim;
import pojo.User;
import pojo.Year;

/**
 *
 * @author yoka
 */
public class Handler {

    UserDao uDao = new UserDao();

    User login(String user, String pass) {
        User u = new User();
        u.setUsername(user);
        u.setPassword(pass);

        ArrayList<User> uarr = (ArrayList<User>) uDao.findByExample(u);
        if (uarr.size() > 0) {
            User u1 = uarr.get(0);
            return u1;
        } else {
            return null;
        }
    }

    boolean userExists(String user) {
        User u = new User();
        u.setUsername(user);
        ArrayList<User> uarr = (ArrayList<User>) uDao.findByExample(u);
        return uarr.size() > 0;
    }

    int register(User u) {
        uDao.create(u);
        return 0;
    }

    List<Make> getMake() {
        List<Make> lst = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Make e = new Make();
            e.setName("make " + i);
            lst.add(e);
        }
        return lst;
    }

    List<Model> getModelByMake(String make) {
        ModelDao modelDao = new ModelDao();
        List<Model> result = modelDao.getModelsByMake(make);
        return result;
    }

    List<Trim> getTrim(String model, String year) {
        return null;
    
    }

    List<Year> getYear(String model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean addVehicle(String make, String model, String year, String trim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    User loginByEmail(String email, String pass) {
        User u = new User();
        u.setEmail(email);
        u.setPassword(pass);
        uDao = new UserDao();
        ArrayList<User> uarr = (ArrayList<User>) uDao.findByExample(u);
        User u1 = uarr.get(0);
        return u1;
    }
//    String forgetPassword()
//    {
//        
//    }

    boolean emailExists(String email) {
        User u = new User();
        u.setEmail(email);
        ArrayList<User> uarr = (ArrayList<User>) uDao.findByExample(u);
        return uarr.size() > 0;
    }

}
