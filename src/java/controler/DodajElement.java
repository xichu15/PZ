package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "dodajElement")
@RequestScoped
public class DodajElement implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.dodajElement");
    @EJB
    private DataBean db;
    
    private String nazwa;
    private String jednostka;
    
    public DodajElement() {}
    
    public void dodajNowegoUzytkownika(){
        try{
            db.dodajElement(getNazwa(), getJednostka());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie dodac elementu");
            e.printStackTrace();
        }
    }    

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getJednostka() {
        return jednostka;
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }
    
}
