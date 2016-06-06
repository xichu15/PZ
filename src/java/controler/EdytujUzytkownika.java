package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "edytujUzytkownika")
@ViewScoped
public class EdytujUzytkownika implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.edytujUzytkownika");
    @EJB
    private DataBean db;
    
    private Integer idUzytkownik;
    private String nowyEmail;
    private String stareHaslo;
    private String noweHaslo;
    
    public EdytujUzytkownika() {}
    
    public void edytujUzytkownika(){
        try{
            db.edytujUzytkownika(getIdUzytkownik(), getNoweHaslo(), getNowyEmail());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie edytowac uzytkownika");
            e.printStackTrace();
        }
    }    
    
    public void edytujUzytkownika(Integer idUzytkownik){
        Boolean hasloPoprawne = false;
        System.out.println("Id usera: " + idUzytkownik);
        try{
            hasloPoprawne = db.sprawdzHaslo(idUzytkownik, getStareHaslo());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie sprawdzic hasla");
            e.printStackTrace();
        }
        if(hasloPoprawne){
            try{
                db.edytujUzytkownika(idUzytkownik, getNoweHaslo(), getNowyEmail());
            }
            catch(Exception e){
                logger.warning("Haslo sie nie zgadza");
                e.printStackTrace();
            }            
        }
    }    

    public String getNowyEmail() {
        return nowyEmail;
    }

    public void setNowyEmail(String nowyEmail) {
        this.nowyEmail = nowyEmail;
    }

    public String getNoweHaslo() {
        return noweHaslo;
    }

    public void setNoweHaslo(String noweHaslo) {
        this.noweHaslo = noweHaslo;
    }

    public Integer getIdUzytkownik() {
        return idUzytkownik;
    }

    public void setIdUzytkownik(Integer idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    public String getStareHaslo() {
        return stareHaslo;
    }

    public void setStareHaslo(String stareHaslo) {
        this.stareHaslo = stareHaslo;
    }

}
