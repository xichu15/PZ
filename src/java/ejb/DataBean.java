package ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Archiwumpomiar;
import model.Czujnik;
import model.Element;
import model.Gatunekchmur;
import model.Grupa;
import model.Pomiar;
import model.Rodzajchmur;
import model.Stacja;
import model.Uzytkownik;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

@Stateless
public class DataBean {
    private static Logger logger = Logger.getLogger(".ejb.DataBean");

    @PersistenceContext
    private EntityManager em;
    
    /**********Dodawanie obiektow**********/
    
    public void dodajUzytkownika(String login, String haslo, String email) {
        try{
            haslo = sha256(haslo);
            Uzytkownik dodawany = new Uzytkownik(Integer.valueOf(1), login, haslo, email);
            logger.info("Dodaje uzytkownika: " + login);
            em.persist(dodawany);
            Grupa przypisanie = new Grupa(Integer.valueOf(1), login, "usergroup");
            logger.info("Dodaje uzytkownika do grupy");
            em.persist(przypisanie);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
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
            logger.info("Dodaje archiwum: " + data);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        } 
    }
    
    public void dodajArchiwum(Pomiar usuwany) {
        try{
            Archiwumpomiar dodawany = new Archiwumpomiar(Integer.valueOf(1), usuwany.getWartosc(), usuwany.getDataPomiaru(), usuwany.getCzasPomiaru(), usuwany.getIdElement(), usuwany.getIdCzujnik());
            logger.info("Dodaje archiwum: " + usuwany.getDataString());
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

    public void usunStacje(Stacja usuwany) {
        try{
            logger.info("Usuwanie stacji: " + usuwany.getNazwa());
            em.remove(em.merge(usuwany));
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void usunPomiar(Pomiar usuwany) {
        try{
            logger.info("Usuwanie pomiaru: " + usuwany.getDataPomiaru() + " " + usuwany.getCzasPomiaru());
            em.remove(em.merge(usuwany));
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void usunElement(Element usuwany) {
        try{
            logger.info("Usuwanie elementu: " + usuwany.getNazwa());
            em.remove(em.merge(usuwany));
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void usunCzujnik(Czujnik usuwany) {
        try{
            logger.info("Usuwanie czujnika: " + usuwany.getNazwa());
            em.remove(em.merge(usuwany));
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void usunRodzajChmur(Rodzajchmur usuwany) {
        try{
            logger.info("Usuwanie rodzaju chmur: " + usuwany.getNazwa());
            em.remove(em.merge(usuwany));
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void usunGatunekChmur(Gatunekchmur usuwany) {
        try{
            logger.info("Usuwanie gatunku chmur: " + usuwany.getNazwa());
            em.remove(em.merge(usuwany));
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void usunArchiwum(Archiwumpomiar usuwany) {
        try{
            logger.info("Usuwanie archiwum pomiaru: " + usuwany.getDataPomiaru() + " " + usuwany.getCzasPomiaru());
            em.remove(em.merge(usuwany));
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    /********** Edytowanie obiektow **********/
    
    public void edytujUzytkownika(Integer idUzytkownik, String noweHaslo, String nowyEmail) {
        try{
            logger.info("Szukanie uzytkownika id: " + idUzytkownik);
            Uzytkownik uzytkownik = em.find(Uzytkownik.class, idUzytkownik);
            logger.info("Edycja uzytkownika");
            if(!noweHaslo.isEmpty()){
                noweHaslo = sha256(noweHaslo);
                uzytkownik.setHaslo(noweHaslo);
            }
            if(!nowyEmail.isEmpty()){
                uzytkownik.setEmail(nowyEmail);
            }
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void edytujStacje(Integer idStacja, String nowaNazwa, String nowaStrefaCzasowa, int nowePrzesuniecie, double nowaDlugoscGeograficzna, double nowaSzerokoscGeograficzna, int nowaWysokoscNpm) {
        try{
            logger.info("Szukanie stacji id: " + idStacja);
            Stacja stacja = em.find(Stacja.class, idStacja);
            logger.info("Edycja stacji: " + stacja.getNazwa());
            if(!nowaNazwa.isEmpty()){
                stacja.setNazwa(nowaNazwa);
            }
            if(!nowaStrefaCzasowa.isEmpty()){
                stacja.setStrefaCzasowa(nowaStrefaCzasowa);
                stacja.setPrzesuniecie(nowePrzesuniecie);
            }
            if(nowaDlugoscGeograficzna != 0){
                stacja.setDlugoscGeograficzna(nowaDlugoscGeograficzna);
            }    
            if(nowaSzerokoscGeograficzna != 0){
                stacja.setSzerokoscGeograficzna(nowaSzerokoscGeograficzna);
            }   
            if(nowaWysokoscNpm != 0){
                stacja.setWysokoscNpm(nowaWysokoscNpm);
            }          
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void edytujPomiar(Integer idPomiar, double nowaWartosc, Date nowaDataPomiaru, Date nowyCzasPomiaru) {
        try{
            logger.info("Szukanie pomiaru id: " + idPomiar);
            Pomiar pomiar = em.find(Pomiar.class, idPomiar);
            logger.info("Edycja pomiaru: " + pomiar.getDataPomiaru() + " " + pomiar.getCzasPomiaru());
            if(nowaWartosc != 0){
                pomiar.setWartosc(nowaWartosc);
            }
            if(!nowaDataPomiaru.toString().isEmpty()){
                pomiar.setDataPomiaru(nowaDataPomiaru);
            }
            if(!nowyCzasPomiaru.toString().isEmpty()){
                pomiar.setCzasPomiaru(nowyCzasPomiaru);
            }
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void edytujElement(Integer idElement, String nowaNazwa, String nowaJednostka) {
           try{
            logger.info("Szukanie elementu id: " + idElement);
            Element element = em.find(Element.class, idElement);
            logger.info("Edycja elementu: " + element.getNazwa());
            if(nowaNazwa.isEmpty()){
                element.setNazwa(nowaNazwa);
            }
            if(!nowaJednostka.isEmpty()){
                element.setJednostka(nowaJednostka);
            }
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void edytujCzujnik(Integer idCzujnik, String nowaNazwa) {
        try{
            logger.info("Szukanie czujnika id: " + idCzujnik);
            Czujnik czujnik = em.find(Czujnik.class, idCzujnik);
            logger.info("Edycja czujnika: " + czujnik.getNazwa());
            if(nowaNazwa.isEmpty()){
                czujnik.setNazwa(nowaNazwa);
            }
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void edytujRodzajChmury(Integer idRodzajChmur, String nowyRodzajChmur) {
        try{
            logger.info("Szukanie rodzaju chmur id: " + idRodzajChmur);
            Rodzajchmur rodzajChmur = em.find(Rodzajchmur.class, idRodzajChmur);
            logger.info("Edycja rodzaju chmur: " + rodzajChmur.getNazwa());
            if(nowyRodzajChmur.isEmpty()){
                rodzajChmur.setNazwa(nowyRodzajChmur);
            }
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public void edytujGatunekChmury(Integer idGatunekChmur, String nowyGatunekChmur) {
        try{
            logger.info("Szukanie gatunku chmur id: " + idGatunekChmur);
            Gatunekchmur gatunekChmur = em.find(Gatunekchmur.class, idGatunekChmur);
            logger.info("Edycja gatunku chmur: " + gatunekChmur.getNazwa());
            if(nowyGatunekChmur.isEmpty()){
                gatunekChmur.setNazwa(nowyGatunekChmur);
            }
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }     
    }

    public void edytujArchiwum(Integer idPomiar, double nowaWartosc, Date nowaDataArchiwum, Date nowyCzasArchiwum) {
        try{
            logger.info("Szukanie archiwum id: " + idPomiar);
            Archiwumpomiar archiwum = em.find(Archiwumpomiar.class, idPomiar);
            logger.info("Edycja archiwum: " + archiwum.getDataPomiaru() + " " + archiwum.getCzasPomiaru());
            if(nowaWartosc != 0){
                archiwum.setWartosc(nowaWartosc);
            }
            if(!nowaDataArchiwum.toString().isEmpty()){
                archiwum.setDataPomiaru(nowaDataArchiwum);
            }
            if(!nowyCzasArchiwum.toString().isEmpty()){
                archiwum.setCzasPomiaru(nowyCzasArchiwum);
            }
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    /********** Listy elementow **********/
    
    public List<Uzytkownik> pobierzUzytkownikow() {
        List<Uzytkownik> uzytkownicy = null;
            try{
                logger.info("Pobieram liste uzytkownikow");
                uzytkownicy = (List<Uzytkownik>) em.createNamedQuery("Uzytkownik.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }
        return uzytkownicy;
    }

    public List<Pomiar> pobierzPomiary() {
        List<Pomiar> pomiary = null;
            try{
                logger.info("Pobieram liste pomiarow");
                pomiary = (List<Pomiar>) em.createNamedQuery("Pomiar.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }
            Collections.sort(pomiary, Pomiar.pomiarPorownajDaty);
        return pomiary;
    }
    
    public List<Pomiar> pobierzPomiary(Integer idStacji) {
        List<Pomiar> pomiary;
        ArrayList<Pomiar> pomiaryStacji = new ArrayList<>();
        Calendar czasOd = Calendar.getInstance();
        czasOd.add(Calendar.DATE, -1);
            try{
                logger.info("Pobieram liste pomiarow");
                pomiary = (List<Pomiar>) em.createNamedQuery("Pomiar.findAll").getResultList();
                logger.info("Szukam stacji");
                Stacja stacja = em.find(Stacja.class, idStacji);
                for(Pomiar pomiar : pomiary){
                    Calendar czasPomiaru = Calendar.getInstance();
                    czasPomiaru.setTime(pomiar.getDataPlusCzas());
                    if(pomiar.getIdCzujnik().getIdStacja() == stacja && czasPomiaru.after(czasOd)){
                        pomiaryStacji.add(pomiar);
                    }
                }
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }  
        return pomiaryStacji;
    }    

    public List<Stacja> pobierzStacje() {
        List<Stacja> stacje = null;
            try{
                logger.info("Pobieram liste stacji");
                stacje = (List<Stacja>) em.createNamedQuery("Stacja.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }
        return stacje;
    }

    public List<Element> pobierzElementy() {
        List<Element> elementy = null;
            try{
                logger.info("Pobieram liste elementow");
                elementy = (List<Element>) em.createNamedQuery("Element.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }
        return elementy;
    }

    public List<Czujnik> pobierzCzujniki() {
        List<Czujnik> czujniki = null;
            try{
                logger.info("Pobieram liste czujnikow");
                czujniki = (List<Czujnik>) em.createNamedQuery("Czujnik.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }
        return czujniki;
    }

    public List<Rodzajchmur> pobierzRodzajeChmur() {
        List<Rodzajchmur> rodzajeChmur = null;
            try{
                logger.info("Pobieram liste rodzajow chmur");
                rodzajeChmur = (List<Rodzajchmur>) em.createNamedQuery("Rodzajchmur.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }
        return rodzajeChmur;
    }

    public List<Gatunekchmur> pobierzGatunkiChmur() {
        List<Gatunekchmur> gatunkiChmur = null;
            try{
                logger.info("Pobieram liste gatunkow chmur");
                gatunkiChmur = (List<Gatunekchmur>) em.createNamedQuery("Gatunekchmur.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }
        return gatunkiChmur;
    }

    public List<Archiwumpomiar> pobierzArchiwa() {
        List<Archiwumpomiar> archiwa = null;
            try{
                logger.info("Pobieram liste archiwum");
                archiwa = (List<Archiwumpomiar>) em.createNamedQuery("Archiwumpomiar.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }
            Collections.sort(archiwa, Archiwumpomiar.pomiarPorownajDaty);
        return archiwa;
    }
    
    public List<Archiwumpomiar> pobierzArchiwum(Integer idStacji, Date dataOd, Date dataDo) {
        List<Archiwumpomiar> archiwa;
        ArrayList<Archiwumpomiar> archiwaStacji = new ArrayList<>();
        Calendar czasDo = Calendar.getInstance();
        czasDo.setTime(dataDo);
        Calendar czasOd = Calendar.getInstance();
        czasOd.setTime(dataOd);
        System.out.println("Data od: " + dataOd.toString() + " data do: " + dataDo.toString() + " id stacji: " + idStacji);
            try{
                logger.info("Pobieram liste archiwow");
                archiwa = (List<Archiwumpomiar>) em.createNamedQuery("Archiwumpomiar.findAll").getResultList();
                logger.info("Szukam stacji");
                Stacja stacja = em.find(Stacja.class, idStacji);
                for(Archiwumpomiar pomiar : archiwa){
                    Calendar czasPomiaru = Calendar.getInstance();
                    System.out.println("Id stacji w petli: " + pomiar.getIdCzujnik().getIdStacja().getIdStacja());
                    czasPomiaru.setTime(pomiar.getDataPlusCzas());
                    if(pomiar.getIdCzujnik().getIdStacja() == stacja && czasPomiaru.after(czasOd) && czasPomiaru.before(czasDo)){
                        archiwaStacji.add(pomiar);
                    }
                }
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }  
            for(Archiwumpomiar pomiar : archiwaStacji){
                System.out.println("Znalezione id: " + pomiar.getIdArchiwumPomiar());
            }
        return archiwaStacji;        
    }

    public List<Marker> pobierzLokacje() {
        List<Stacja> stacje = null;
        ArrayList<Marker> znaczniki = new ArrayList<>();
            try{
                logger.info("Pobieram liste stacji");
                stacje = (List<Stacja>) em.createNamedQuery("Stacja.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }
            if(stacje != null){
                for(Stacja lokacja : stacje){
                    System.out.println(lokacja.getNazwa() + " " + lokacja.getDlugoscGeograficzna() + " " + lokacja.getSzerokoscGeograficzna());
                    LatLng wspolrzedne = new LatLng(lokacja.getDlugoscGeograficzna(), lokacja.getSzerokoscGeograficzna());
                    znaczniki.add(new Marker(wspolrzedne, lokacja.getNazwa())); 
                }
            }
        return znaczniki;        
    }

    /********** Inne funkcjonalnosci **********/
    
    public Boolean sprawdzLogin(String login, String haslo) throws NoSuchAlgorithmException {
        List<Uzytkownik> uzytkownicy = null;
        Boolean poprawny = false;
            try{
                logger.info("Pobieram liste uzytkownikow");
                uzytkownicy = (List<Uzytkownik>) em.createNamedQuery("Uzytkownik.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }  
        for(Uzytkownik sprawdzany : uzytkownicy){
            if(sprawdzany.getLogin().equals(login)){
                if(sprawdzSha256(sprawdzany.getHaslo(), haslo)){
                    poprawny = true;
                }
            }
        }
        return poprawny;
    }
    
    static Boolean sprawdzSha256(String hasloUzytkownika, String hasloWprowadzone) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(hasloWprowadzone.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        Boolean takieSame = false;
        if(sb.toString().equals(hasloUzytkownika))
            takieSame = true;
         
        return takieSame;
    }

    public String znajdzGrupe(String login) {
        List<Grupa> grupy = null;
        String grupaUzytkownika = "";
            try{
                logger.info("Pobieram liste grup");
                grupy = (List<Grupa>) em.createNamedQuery("Grupa.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }          
        for(Grupa grupa : grupy){
            if(grupa.getUzytkownik().equals(login)){
                grupaUzytkownika = grupa.getNazwagrupy();
            }
        }    
        return grupaUzytkownika; 
    }

    public Integer znajdzUzytkownika(String login) {
        List<Uzytkownik> uzytkownicy = null;
        Integer idUzytkownika = 0;
            try{
                logger.info("Pobieram liste uzytkownikow");
                uzytkownicy = (List<Uzytkownik>) em.createNamedQuery("Uzytkownik.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }  
        for(Uzytkownik sprawdzany : uzytkownicy){
            if(sprawdzany.getLogin().equals(login)){
                idUzytkownika = sprawdzany.getIdUzytkownik();
            }
        }
        return idUzytkownika;        
    }

    public Boolean sprawdzLogin(String login) {
        List<Uzytkownik> uzytkownicy = null;
            try{
                logger.info("Pobieram liste uzytkownikow");
                uzytkownicy = (List<Uzytkownik>) em.createNamedQuery("Uzytkownik.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }  
        for(Uzytkownik sprawdzany : uzytkownicy){
            if(sprawdzany.getLogin().equals(login)){
                return true;
            }
        }
        return false;           
    }

    public Integer znajdzIdStacji(String title) {
        List<Stacja> stacje = null;
        Integer idStacji = 0;
            try{
                logger.info("Pobieram liste stacji");
                stacje = (List<Stacja>) em.createNamedQuery("Stacja.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }  
        for(Stacja sprawdzana : stacje){
            if(sprawdzana.getNazwa().equals(title)){
                idStacji = sprawdzana.getIdStacja();
            }
        }
        return idStacji;         
    }

    public Boolean sprawdzHaslo(Integer idUzytkownik, String stareHaslo) {
        Uzytkownik uzytkownik;
            try{
                logger.info("Szukam uzytkownika");
                uzytkownik = (Uzytkownik) em.find(Uzytkownik.class, idUzytkownik);
                if(uzytkownik.getHaslo().equals(sha256(stareHaslo)))
                    return true;
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }
        return false;    
    }

    /********** Szukanie po ID **********/
}
