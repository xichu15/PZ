package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "dodajCzujnik")
@RequestScoped
public class DodajCzujnik implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.dodajCzujnik");
    @EJB
    private DataBean db;
    
    private String nazwa;

    public DodajCzujnik() {}
    
    public void dodajNowyCzujnik(){
        try{
            db.dodajCzujnik(getNazwa());    
        }
        catch(Exception e){
            logger.warning("Nie udalo sie dodac pomiaru");
            e.printStackTrace();
        }
    }    

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

}
