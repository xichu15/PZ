package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "edytujChmury")
@ViewScoped
public class EdytujChmury implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.edytujChmury");
    @EJB
    private DataBean db;
    
    private Integer idRodzajChmur;
    private Integer idGatunekChmur;
    private String nowyGatunekChmur;
    private String nowyRodzajChmur;
    
    public EdytujChmury() {}
    
    public void edytujRodzajChmury(){
        try{
            db.edytujRodzajChmury(getIdRodzajChmur(), getNowyRodzajChmur());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie edytowac rodzaju chmury");
            e.printStackTrace();
        }
    } 
 
    public void edytujGatunekChmury(){
        try{
            db.edytujGatunekChmury(getIdGatunekChmur(), getNowyGatunekChmur());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie edytowac gatunku chmury");
            e.printStackTrace();
        }
    } 

    public Integer getIdRodzajChmur() {
        return idRodzajChmur;
    }

    public void setIdRodzajChmur(Integer idRodzajChmur) {
        this.idRodzajChmur = idRodzajChmur;
    }

    public Integer getIdGatunekChmur() {
        return idGatunekChmur;
    }

    public void setIdGatunekChmur(Integer idGatunekChmur) {
        this.idGatunekChmur = idGatunekChmur;
    }

    public String getNowyGatunekChmur() {
        return nowyGatunekChmur;
    }

    public void setNowyGatunekChmur(String nowyGatunekChmur) {
        this.nowyGatunekChmur = nowyGatunekChmur;
    }

    public String getNowyRodzajChmur() {
        return nowyRodzajChmur;
    }

    public void setNowyRodzajChmur(String nowyRodzajChmur) {
        this.nowyRodzajChmur = nowyRodzajChmur;
    }
}
