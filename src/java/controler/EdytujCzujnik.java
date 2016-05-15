package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "edytujCzujnik")
@ViewScoped
public class EdytujCzujnik implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.edytujCzujnik");
    @EJB
    private DataBean db;
    
    private Integer idCzujnik;
    private String nowaNazwa;
    
    public EdytujCzujnik() {}
    
    public void edytujCzujnik(){
        try{
            db.edytujCzujnik(getIdCzujnik(), getNowaNazwa());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie edytowac czujnika");
            e.printStackTrace();
        }
    } 

    public Integer getIdCzujnik() {
        return idCzujnik;
    }

    public void setIdCzujnik(Integer idCzujnik) {
        this.idCzujnik = idCzujnik;
    }

    public String getNowaNazwa() {
        return nowaNazwa;
    }

    public void setNowaNazwa(String nowaNazwa) {
        this.nowaNazwa = nowaNazwa;
    }
}
