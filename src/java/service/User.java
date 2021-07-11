/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.io.Serializable;
/**
 *
 * @author Matthieu
 */
public class User implements Serializable {
    private String login;
    private String pwd;
    private int userId;
    static private int compteur=0;

    public static int getCompteur() {
        return compteur;
    }

    public User() {
        super();
        userId=compteur++;
    }
    public void finalize(){
        
        compteur--;
    }
     public User(String login) {
        this.login = login;
        userId=compteur++;
        
    }

    public User(String login, String pwd) {
        this.login = login;
        this.pwd = pwd;
        userId=compteur++;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
}
