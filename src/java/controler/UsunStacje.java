package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.Stacja;

@Named(value = "usunStacje")
@RequestScoped
public class UsunStacje implements Serializable{
    private static Logger logger = Logger.getLogger(".control.usunStacje");
    @EJB
    private DataBean db;
    
    public UsunStacje() {
    }
 
    public void usunStacje(Stacja usuwany) {
        try{
            db.usunStacje(usuwany);
        }
        catch(Exception e)
        {
            logger.warning("Nie udało się usunac stacji");
            e.printStackTrace();
        }        
    }    
}
