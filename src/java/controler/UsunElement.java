package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.Element;

@Named(value = "usunElement")
@RequestScoped
public class UsunElement implements Serializable{
    private static Logger logger = Logger.getLogger(".control.usunElement");
    @EJB
    private DataBean db;
    
    public UsunElement() {
    }
 
    public void usunElement(Element usuwany) {
        try{
            db.usunElement(usuwany);
        }
        catch(Exception e)
        {
            logger.warning("Nie udało się usunac elementu");
            e.printStackTrace();
        }        
    }    
}
