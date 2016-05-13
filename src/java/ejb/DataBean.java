package ejb;

import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Uzytkownik;

@Stateful
public class DataBean {
    private static Logger logger = Logger.getLogger(".ejb.DataBean");

    @PersistenceContext
    private EntityManager em;
    
    
    /**********Dodawanie obiektow**********/
    public void dodajUzytkownika(String login, String haslo, String email) {
        try{
            Uzytkownik Dodawany = new Uzytkownik(Integer.valueOf(1), login, haslo, email);
            logger.info("dodaje uzytkownika : " + login);
            em.persist(Dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
}
