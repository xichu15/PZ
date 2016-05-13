package ejb;

import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Stacja;
import model.Uzytkownik;

@Stateful
public class DataBean {
    private static Logger logger = Logger.getLogger(".ejb.DataBean");

    @PersistenceContext
    private EntityManager em;
    
    
    /**********Dodawanie obiektow**********/
    public void dodajUzytkownika(String login, String haslo, String email) {
        try{
            Uzytkownik dodawany = new Uzytkownik(Integer.valueOf(1), login, haslo, email);
            logger.info("dodaje uzytkownika : " + login);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void DodajStacje(String nazwa, String strefaCzasowa, int przesuniecie, double dlugoscGeograficzna, double szerokoscGeograficzna, int wysokoscNpm) {
        try{
            Stacja dodawany = new Stacja(Integer.valueOf(1), nazwa, strefaCzasowa, przesuniecie, dlugoscGeograficzna, szerokoscGeograficzna, wysokoscNpm);
            logger.info("dodaje stacje : " + nazwa);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
}
