/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author yoka
 */
public class JsonError {

    final static String ERRORMESSAGE = "error";
    // final String successMessage = "success";

    public static String errorJsonObject(String msg) throws JSONException {
        //email is not well formatted
        JSONObject obj = new JSONObject();
        obj.put("result", msg);
        obj.put("status", ERRORMESSAGE);
        return obj.toString();
    }
}
