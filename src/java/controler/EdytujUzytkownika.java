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
    private String nowyLogin;
    private String nowyEmail;
    private String noweHaslo;
    
    public EdytujUzytkownika() {}
    
    public void edytujUzytkownika(){
        try{
            db.edytujUzytkownika(getIdUzytkownik(), getNowyLogin(), getNoweHaslo(), getNowyEmail());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie edytowac uzytkownika");
            e.printStackTrace();
        }
    }    

    public String getNowyLogin() {
        return nowyLogin;
    }

    public void setNowyLogin(String nowyLogin) {
        this.nowyLogin = nowyLogin;
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

}
