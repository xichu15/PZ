/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Named(value = "przekierowanie")
@ViewScoped
public class Przekierowanie implements Serializable{
    private static Logger logger = Logger.getLogger(".control.przekierowanie");

    public static Integer getIdZalogowanego() {
        return idZalogowanego;
    }

    public static void setIdZalogowanego(Integer aIdZalogowanego) {
        Przekierowanie.idZalogowanego = aIdZalogowanego;
    }
    @EJB
    private DataBean db;
    private String login;
    private String haslo;
    private static Integer idZalogowanego;
    
    public Przekierowanie() {
    }
    
    public String zaloguj() throws NoSuchAlgorithmException{
        String grupa = "";
        Boolean czyIstnieje;
        czyIstnieje = db.sprawdzLogin(getLogin(), getHaslo());
        if(czyIstnieje){
            grupa = db.znajdzGrupe(getLogin());
        }
        switch (grupa) {
            case "admingroup":
                setIdZalogowanego(db.znajdzUzytkownika(getLogin()));
                return "admin_strona_glowna.xhtml?faces-redirect=true";
            case "usergroup":
                setIdZalogowanego(db.znajdzUzytkownika(getLogin()));
                System.out.println(db.znajdzUzytkownika(getLogin()));
                return "user_strona_glowna.xhtml?faces-redirect=true";
            default:
                return "";
        }
    }
    
     public String wyloguj(){
         setIdZalogowanego(0);
         return "index.xhtml?faces-redirect=true";
     }
     
     public Integer pobierzId(){
         return Przekierowanie.idZalogowanego;
     }
             

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    
 
}
