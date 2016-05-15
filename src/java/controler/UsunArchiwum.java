package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.Archiwumpomiar;

@Named(value = "usunArchiwum")
@RequestScoped
public class UsunArchiwum implements Serializable{
    private static Logger logger = Logger.getLogger(".control.usunArchiwum");
    @EJB
    private DataBean db;
    
    public UsunArchiwum() {
    }
 
    public void usunArchiwum(Archiwumpomiar usuwany) {
        try{
            db.usunArchiwum(usuwany);
        }
        catch(Exception e)
        {
            logger.warning("Nie udało się usunac archiwum");
            e.printStackTrace();
        }        
    }   
}
