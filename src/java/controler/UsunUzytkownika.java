/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.Uzytkownik;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Named(value = "usunUzytkownika")
@RequestScoped
public class UsunUzytkownika implements Serializable{
    private static Logger logger = Logger.getLogger(".control.usunUzytkownika");
    @EJB
    private DataBean db;
    
    public UsunUzytkownika() {
    }
 
    public void usunUzytkownika(Uzytkownik usuwany) {
        try{
            db.usunUzytkownika(usuwany);
        }
        catch(Exception e)
        {
            logger.warning("Nie udało się usunac uzytkownika");
            e.printStackTrace();
        }        
    }      
}
