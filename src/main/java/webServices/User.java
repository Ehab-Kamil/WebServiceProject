/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

/**
 *
 * @author yoka
 */
class User {
private String u;
private String p;
    public User() {
    }
    public User(String u,String p)
    {
    this.p=p;
    this.u=u;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

   
    
}
