/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import Exceptions.DataAccessLayerException;
import dao.MakeDao;
import dao.ModelDao;
import dao.TrimDao;
import dao.UserDao;
import dao.YearDao;
import facadePkg.DataLayer;
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
        List<Make> result = new ArrayList<>();
        MakeDao makeDao = new MakeDao();
        result = makeDao.findAll();
        return result;
    }

    List<Model> getModelByMake(String make) {
        ModelDao modelDao = new ModelDao();
        List<Model> result = modelDao.getModelsByMake(make);
        return result;
    }

    List<Trim> getTrim(String model, String year) {

        TrimDao trimDao = new TrimDao();
        return trimDao.getTrimByYearAndModel(model, year);

    }

    List<Year> getYear(String model) {

        YearDao yearDao = new YearDao();
        return yearDao.getYearByModel(model);
        
    }

    boolean addVehicle(String make, String model, String year, String trim) {
        boolean result = false;
        try {
            DataLayer dataLayer = new DataLayer();
            Make newMake = new Make(make, make);
            Model newModel = new Model(newMake, model, model);
            Year newYear = new Year(Integer.parseInt(year));
            Trim newTrim = new Trim(trim);
            dataLayer.insertVehicle(newMake, newModel, newYear, newTrim);
            result = true;
        } catch (DataAccessLayerException ex) {
            result = false;
        }
        return result;
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
