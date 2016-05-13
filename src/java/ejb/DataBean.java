package ejb;

import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Archiwumpomiar;
import model.Czujnik;
import model.Element;
import model.Gatunekchmur;
import model.Pomiar;
import model.Rodzajchmur;
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
            logger.info("Dodaje uzytkownika: " + login);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void DodajStacje(String nazwa, String strefaCzasowa, int przesuniecie, double dlugoscGeograficzna, double szerokoscGeograficzna, int wysokoscNpm) {
        try{
            Stacja dodawany = new Stacja(Integer.valueOf(1), nazwa, strefaCzasowa, przesuniecie, dlugoscGeograficzna, szerokoscGeograficzna, wysokoscNpm);
            logger.info("Dodaje stacje: " + nazwa);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void dodajRodzajChmur(String rodzajChmur) {
        try{
            Rodzajchmur dodawany = new Rodzajchmur(Integer.valueOf(1), rodzajChmur);
            logger.info("Dodaje rodzaj chmur: " + rodzajChmur);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }    
    }

    public void dodajGatunekChmur(String gatunekChmur) {
        try{
            Gatunekchmur dodawany = new Gatunekchmur(Integer.valueOf(1), gatunekChmur);
            logger.info("Dodaje gatunek chmur: " + gatunekChmur);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        } 
    }

    public void dodajElement(String nazwa, String jednostka) {
        try{
            Element dodawany = new Element(Integer.valueOf(1), nazwa, jednostka);
            logger.info("Dodaje element: " + nazwa);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        } 
    }

    public void dodajPomiar(double wartosc, Date data, Date czas) {
        try{
            Pomiar dodawany = new Pomiar(Integer.valueOf(1), wartosc, data, czas);
            logger.info("Dodaje pomiar: " + data);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        } 
    }

    public void dodajArchiwum(double wartosc, Date data, Date czas) {
        try{
            Archiwumpomiar dodawany = new Archiwumpomiar(Integer.valueOf(1), wartosc, data, czas);
            logger.info("Dodaje pomiar: " + data);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        } 
    }
    
    public void dodajCzujnik(String nazwa) {
        try{
            Czujnik dodawany = new Czujnik(Integer.valueOf(1), nazwa);
            logger.info("Dodaje czujnik: " + nazwa);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        } 
    }

    /**********Usuwanie obiektow**********/
    
    public void usunUzytkownika(Uzytkownik usuwany) {
        try{
            logger.info("Usuwanie uzytkownika: " + usuwany.getLogin());
            em.remove(em.merge(usuwany));
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

}
