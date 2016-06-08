/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;


import com.sun.tools.internal.xjc.util.Util;
import dao.UserDao;
import java.util.List;
import javax.ws.rs.Path;
import org.aspectj.weaver.Utils;

import pojo.User;

/**
 *
 * @author yoka
 */
@Path("/forget")
public class ForgetPasswordService {
//   UserDao dao=new UserDao();
//    boolean checkEmail(String email)
//    { 
//        User u=new User();
//        u.setEmail(email);
//    List<User> up = dao.findByExample(u);
//        if(up!=null) {
//            // create verification code
//            String hash = Utils.prepareRandomString(30);
//            // update verification code in database 
//            UserDAO.updateEmailVerificationHashForResetPassword(inputEmail, BCrypt.hashpw(hash,GlobalConstants.SALT));
//            // send email to user with verification link
//            MailUtil.sendResetPasswordLink(up.getUSER_ID()+"", inputEmail, hash);
//            sp.setCode(0);
//            sp.setMessage("We have sent reset password link to your email");
//        } else {
//            sp.setCode(-1);
//            sp.setMessage("This email doesn't exist");
//        }
//    } catch (DBException | MessagingException e) {
//        LOGGER.debug(e.getMessage());
//        sp.setCode(-1);
//        sp.setMessage(e.getMessage());
//    }
}
