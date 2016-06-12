/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONObject;
import webServicesHandlers.Handler;

/**
 *
 * @author yoka
 */
@Path("/img")
public class ProfilePictureService {

    Handler handler;

    public ProfilePictureService() {
        handler = new Handler();
    }

    @POST
    public String upload(@FormParam("image") String img, @FormParam("userId") int userId, @FormParam("fileExt") String fileExt) {
        byte[] arr = DatatypeConverter.parseBase64Binary(img);
        System.out.println("byte >>" + arr.length);
        JSONObject obj = new JSONObject();
        obj.put("result", "done");
        try {
            writeToFile(arr, fileExt, userId);
        } catch (IOException ex) {
            Logger.getLogger(ProfilePictureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj.toString();
    }

    public void writeToFile(byte[] array, String ext, int userId) throws IOException {
        String image = "/Users/yoka/desktop/Uploaded/pic" + userId + "." + ext;
        try (FileOutputStream fileOuputStream = new FileOutputStream(image)) {
            fileOuputStream.write(array);
        }
        handler.insertPictureToUserAccount(image, userId);
    }
}
