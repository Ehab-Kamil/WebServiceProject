/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import java.io.File;

/**
 *
 * @author yoka
 */
public class NewClass {
    public static void main(String[]args)
    {
    
    final File f = new File(NewClass.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println(""+f.getPath());
    }
}
