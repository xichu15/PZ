package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "dodajUzytkownika")
@RequestScoped
public class DodajUzytkownika implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.dodajUzytkownika");
    @EJB
    private DataBean db;
    
    private String login;
    private String email;
    private String haslo;
    private String powtorzHaslo;
    
    public DodajUzytkownika() {}
    
    public void dodajNowegoUzytkownika(){
        Boolean istniejeLogin = true;
        try{
            istniejeLogin = db.sprawdzLogin(getLogin());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie sprawdzic loginu");
            e.printStackTrace();
        }
        if(!istniejeLogin){
            try{
                db.dodajUzytkownika(getLogin(), getHaslo(), getEmail());
            }
            catch(Exception e){
                logger.warning("Nie udalo sie dodac uzytkownika");
                e.printStackTrace();
            }            
        } else{
            logger.warning("Login juz istnieje");
        }
    }    
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getPowtorzHaslo() {
        return powtorzHaslo;
    }

    public void setPowtorzHaslo(String powtorzHaslo) {
        this.powtorzHaslo = powtorzHaslo;
    }
    
}
