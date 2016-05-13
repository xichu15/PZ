package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "dodajChmury")
@RequestScoped
public class DodajChmury implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.dodajChmury");
    @EJB
    private DataBean db;
    
    private String gatunekChmur;
    private String rodzajChmur;
    
    public DodajChmury() {}
    
    public void dodajNowyRodzajChmur(){
        try{
            db.dodajRodzajChmur(getRodzajChmur());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie dodac rodzaju chmur");
            e.printStackTrace();
        }
    }
    
    public void dodajNowyGatunekChmur(){
        try{
            db.dodajGatunekChmur(getGatunekChmur());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie dodac gatunku chmur");
            e.printStackTrace();
        }
    } 

    public String getGatunekChmur() {
        return gatunekChmur;
    }

    public void setGatunekChmur(String gatunekChmur) {
        this.gatunekChmur = gatunekChmur;
    }

    public String getRodzajChmur() {
        return rodzajChmur;
    }

    public void setRodzajChmur(String rodzajChmur) {
        this.rodzajChmur = rodzajChmur;
    }
    
}
