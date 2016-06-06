package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.Rodzajchmur;

@Named(value = "usunChmury")
@RequestScoped
public class UsunChmury implements Serializable{
    private static Logger logger = Logger.getLogger(".control.usunChmury");
    @EJB
    private DataBean db;
    
    public UsunChmury() {
    }
 
    public void usunRodzajChmur(Rodzajchmur usuwany) {
        try{
            db.usunRodzajChmur(usuwany);
        }
        catch(Exception e)
        {
            logger.warning("Nie udało się usunac rodzaju chmur");
            e.printStackTrace();
        }        
    }  
}
