package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.Pomiar;

@Named(value = "usunPomiar")
@RequestScoped
public class UsunPomiar implements Serializable{
    private static Logger logger = Logger.getLogger(".control.usunPomiar");
    @EJB
    private DataBean db;
    
    public UsunPomiar() {
    }
 
    public void usunPomiar(Pomiar usuwany) {
        try{
            db.dodajArchiwum(usuwany);
            db.usunPomiar(usuwany);
        }
        catch(Exception e)
        {
            logger.warning("Nie udało się usunac pomiaru");
            e.printStackTrace();
        }        
    }    
}
