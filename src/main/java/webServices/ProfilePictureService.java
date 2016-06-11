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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author yoka
 */
@Path("/img")
public class ProfilePictureService {
  @POST
  @Path("/upload")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploudPP(
    @FormDataParam("file") InputStream uploadedInputStream,
@FormDataParam("file") FormDataContentDisposition fileDetail
    )
    {
  String uploadedFileLocation = "./" + fileDetail.getFileName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

    }
        @Path("/pic")
    @POST
    public String uploadImage(@FormParam("imgArrag")byte [] imageInByte,@FormParam("userId") int userId)
    {
      try {
          InputStream in = new ByteArrayInputStream(imageInByte);
          BufferedImage bImageFromConvert = ImageIO.read(in);
          
          ImageIO.write(bImageFromConvert, "jpg", new File(
                  "/Users/yoka/desktop/uploaded/user"+userId+".jpg"));
      } catch (IOException ex) {
          //Logger.getLogger(ProfilePictureService.class.getName()).log(Level.SEVERE, null, ex);
            JSONObject obj = new JSONObject();
            obj.append("error", "upload error");
            return obj.toString();
      }
  JSONObject obj = new JSONObject();
            obj.append("msg", "uploaded done");
            return obj.toString();
    }
}
