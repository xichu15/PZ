package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.Czujnik;

@Named(value = "usunCzujnik")
@RequestScoped
public class UsunCzujnik implements Serializable{
    private static Logger logger = Logger.getLogger(".control.usunCzujnik");
    @EJB
    private DataBean db;
    
    public UsunCzujnik() {
    }
 
    public void usunCzujnik(Czujnik usuwany) {
        try{
            db.usunCzujnik(usuwany);
        }
        catch(Exception e)
        {
            logger.warning("Nie udało się usunac czujnika");
            e.printStackTrace();
        }        
    }   
}
