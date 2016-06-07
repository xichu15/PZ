package ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static jdk.nashorn.internal.objects.NativeMath.round;
import model.Archiwumpomiar;
import model.Czujnik;
import model.Element;
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
        ArrayList<Czujnik> listaCzujnikow = new ArrayList<>();
        try{
             listaCzujnikow = dodawanieCzujnikow();
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
        try{
            Stacja dodawany = new Stacja(Integer.valueOf(1), nazwa, strefaCzasowa, przesuniecie, dlugoscGeograficzna, szerokoscGeograficzna, wysokoscNpm, listaCzujnikow);
            logger.info("Dodaje stacje: " + nazwa);
            em.persist(dodawany);
        }
        catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    public ArrayList<Czujnik> dodawanieCzujnikow(){
        ArrayList<Czujnik> listaCzujnikow = new ArrayList<>();
        listaCzujnikow.add(dodajZwrocCzujnik("Termometr"));
        listaCzujnikow.add(dodajZwrocCzujnik("Barometr"));
        listaCzujnikow.add(dodajZwrocCzujnik("Deszczomierz"));
        listaCzujnikow.add(dodajZwrocCzujnik("Wiatromierz"));
        listaCzujnikow.add(dodajZwrocCzujnik("Higrometr"));
        return listaCzujnikow;
    }
    
    public Czujnik dodajZwrocCzujnik(String nazwa) {
        try{
            Czujnik dodawany = new Czujnik(Integer.valueOf(1), nazwa);
            logger.info("Dodaje czujnik: " + nazwa);
            em.persist(dodawany);
            return dodawany;
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
            Collections.sort(pomiaryStacji, Pomiar.pomiarPorownajDaty);
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
            Collections.sort(archiwaStacji, Archiwumpomiar.pomiarPorownajDaty);
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
    
    private Rodzajchmur znajdzChmury(String wybranaChmura) {
        List<Rodzajchmur> chmury = null;
        Rodzajchmur chmura = null;
            try{
                logger.info("Pobieram liste chmur");
                chmury = (List<Rodzajchmur>) em.createNamedQuery("Rodzajchmur.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }  
        for(Rodzajchmur sprawdzana : chmury){
            if(sprawdzana.getNazwa().equals(wybranaChmura)){
                chmura = sprawdzana;
            }
        }
        return chmura; 
    }
    
    private Element znajdzElement(String nazwaElementu) {
        List<Element> elementy = null;
        Element element = null;
            try{
                logger.info("Pobieram liste elementow");
                elementy = (List<Element>) em.createNamedQuery("Element.findAll").getResultList();
            }
            catch(Exception e){
                throw new EJBException(e.getMessage());
            }  
        for(Element sprawdzana : elementy){
            if(sprawdzana.getNazwa().equals(nazwaElementu)){
                element = sprawdzana;
            }
        }
        return element; 
    }    
    

    /********** Generowanie pomiarow **********/
    
    public Rodzajchmur generujChmury(Integer idStacji) {
        Random losowanie = new Random();
        
        HashMap<String, Integer> szanse = new HashMap<>();
        szanse.put("Cirrus", 10);
        szanse.put("Cirrocumulus", 10);
        szanse.put("Cirrostratus", 10);
        szanse.put("Altocumulus", 10);
        szanse.put("Altostratus", 10);
        szanse.put("Nimbostratus", 10);
        szanse.put("Stratocumulus", 10);
        szanse.put("Stratus", 10);
        szanse.put("Cumulus", 10);
        szanse.put("Cumulonimbus", 10);

        System.out.println("Generuj chmury... idStacji: " + idStacji);
        List<Pomiar> ostatniePomiary = pobierzPomiary(idStacji);
        for(Pomiar p : ostatniePomiary)
            szanse.replace(p.getIdRodzajChmur().getNazwa(), szanse.get(p.getIdRodzajChmur().getNazwa())+1);

        System.out.println("Pobralem pomiary dla chmur");
        HashMap<String, Integer> przedzialy = new HashMap<>();
        Integer dolnaGranica = 1;
        
        Set set = szanse.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry mentry = (Map.Entry)iterator.next();
            przedzialy.put((String)mentry.getKey(), dolnaGranica);
            dolnaGranica += (Integer)mentry.getValue();
        }
        
        dolnaGranica -= 1;
        
        Integer wylosowanaLiczba = losowanie.nextInt(dolnaGranica) + 1;
        String wybranaChmura = "";
        
        set = przedzialy.entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry mentry = (Map.Entry)iterator.next();
            if(wylosowanaLiczba >= (Integer)mentry.getValue())
                 wybranaChmura = (String)mentry.getKey();
        }
        System.out.println("Wybrana chmura: " + wybranaChmura);
        return znajdzChmury(wybranaChmura);
    }

    public Pomiar generujCisnienie(Integer idStacji, Rodzajchmur rodzajChmur, Calendar aktualnyCzas) {
        Stacja stacja = em.find(Stacja.class, idStacji);
        Czujnik czujnik = null;
        for(Czujnik c : stacja.getCzujnikList()){
            if(c.getNazwa().equals("Barometr"))
                czujnik = c;
        }
        
        Element element = znajdzElement("Cisnienie");
        
        
        Random losowanie = new Random();
        double cisnienie = losowanie.nextInt(31) + 1010;
        
        cisnienie -= 12.0 * stacja.getWysokoscNpm()/100;
        Pomiar pomiar = new Pomiar(Integer.valueOf(1), cisnienie, aktualnyCzas.getTime(), aktualnyCzas.getTime(), czujnik, element, rodzajChmur);
        em.persist(pomiar);
        
        return pomiar;
    }

    public Pomiar generujWiatr(Integer idStacji, Rodzajchmur rodzajChmur, Calendar aktualnyCzas, Pomiar cisnienie) {
        Stacja stacja = em.find(Stacja.class, idStacji);
        Czujnik czujnik = null;
        for(Czujnik c : stacja.getCzujnikList()){
            if(c.getNazwa().equals("Wiatromierz"))
                czujnik = c;
        }
        
        Random losowanie = new Random();
        double wiatr = losowanie.nextInt(11) + 9;       
        
        Element element = znajdzElement("Predkosc wiatru");
        List<Pomiar> ostatniePomiary = pobierzPomiary(idStacji);
        
        Integer ilosc = 0;
        double suma = 0;
        for(Pomiar p : ostatniePomiary){
            if(p.getIdElement().getNazwa().equals("Cisnienie"))
                ilosc += 1;
                suma += p.getWartosc();
        }
        double srednieCisnienie = suma/ilosc;
        if(cisnienie.getWartosc() > srednieCisnienie)
            wiatr -= losowanie.nextInt(3) + 1;
        else
            wiatr += losowanie.nextInt(3) + 1;
        
        switch(rodzajChmur.getNazwa()){
            case "Cirrus":
                wiatr -= losowanie.nextInt(2) + 1;
                break;
            case "Cirrocumulus":
                wiatr += losowanie.nextInt(4) + 1;
                break;
            case "Cirrostratus":
                wiatr += losowanie.nextInt(3) - 1;
                break;
            case "Altocumulus":
                wiatr += losowanie.nextInt(4) + 1;
                break;
            case "Altostratus":
                wiatr += losowanie.nextInt(3) - 1;
                break;
            case "Nimbostratus":
                wiatr -= losowanie.nextInt(3) + 1;
                break;
            case "Stratocumulus":
                wiatr += losowanie.nextInt(2) - 1;
                break;
            case "Stratus":
                wiatr -= losowanie.nextInt(3) + 1;
                break;
            case "Cumulus":
                wiatr += losowanie.nextInt(5) - 2;
                break;    
            case "Cumulonimbus":
                wiatr += losowanie.nextInt(7) + 2;
                break;    
        }

        Pomiar pomiar = new Pomiar(Integer.valueOf(1), wiatr, aktualnyCzas.getTime(), aktualnyCzas.getTime(), czujnik, element, rodzajChmur);
        em.persist(pomiar);
        
        return pomiar;
    }

    public Pomiar generujOpady(Integer idStacji, Rodzajchmur rodzajChmur, Calendar aktualnyCzas, Pomiar cisnienie) {
        Stacja stacja = em.find(Stacja.class, idStacji);
        Czujnik czujnik = null;
        for(Czujnik c : stacja.getCzujnikList()){
            if(c.getNazwa().equals("Deszczomierz"))
                czujnik = c;
        }
        
        Random losowanie = new Random();     
        
        Element element = znajdzElement("Opad");
        List<Pomiar> ostatniePomiary = pobierzPomiary(idStacji);
        
        Integer ilosc = 0;
        double suma = 0;
        for(Pomiar p : ostatniePomiary){
            if(p.getIdElement().getNazwa().equals("Opad"))
                ilosc += 1;
                suma += p.getWartosc();
        }
        double opady = suma/ilosc;

        switch(rodzajChmur.getNazwa()){
            case "Cirrus":
                opady = 0;
                break;
            case "Cirrocumulus":
                opady = 0;
                break;
            case "Cirrostratus":
                opady = 0;
                break;
            case "Altocumulus":
                opady = 0;
                break;
            case "Altostratus":
                opady += round(losowanie.nextInt(3) * 0.1 - 0.1, 2);
                break;
            case "Nimbostratus":
                opady += round(losowanie.nextInt(4) * 0.1, 2);
                break;
            case "Stratocumulus":
                opady += round(losowanie.nextInt(2) * 0.1, 2);
                break;
            case "Stratus":
                opady += round(losowanie.nextInt(3) * 0.1, 2);
                break;
            case "Cumulus":
                opady = 0;
                break;    
            case "Cumulonimbus":
                opady += round(losowanie.nextInt(11) * 0.1 + 0.5, 2);
                break;    
        }
        
        if(opady < 0)
            opady = 0;

        Pomiar pomiar = new Pomiar(Integer.valueOf(1), opady, aktualnyCzas.getTime(), aktualnyCzas.getTime(), czujnik, element, rodzajChmur);
        em.persist(pomiar);
        
        return pomiar;
    }
    
    public Pomiar generujTemperature(Integer idStacji, Rodzajchmur rodzajChmur, Calendar aktualnyCzas, Pomiar cisnienie, Pomiar wiatr, Pomiar opady) {
        Stacja stacja = em.find(Stacja.class, idStacji);
        Czujnik czujnik = null;
        for(Czujnik c : stacja.getCzujnikList()){
            if(c.getNazwa().equals("Termometr"))
                czujnik = c;
        }
        
        Random losowanie = new Random();
        Element element = znajdzElement("Temperatura rzeczywista");
        double temperatura = 0;
        
        switch(aktualnyCzas.get(Calendar.MONTH)){
            case 0:
                temperatura = losowanie.nextInt(21) - 10;
                break;
            case 1:
                temperatura = losowanie.nextInt(10) - 5;
                break;
            case 2:
                temperatura = losowanie.nextInt(11);
                break;
            case 3:
                temperatura = losowanie.nextInt(15) + 5;
                break;
            case 4:
                temperatura = losowanie.nextInt(10) + 13;
                break;
            case 5:
                temperatura = losowanie.nextInt(15) + 15;
                break;
            case 6:
                temperatura = losowanie.nextInt(15) + 17;
                break;
            case 7:
                temperatura = losowanie.nextInt(15) + 17;
                break;
            case 8:
                temperatura = losowanie.nextInt(14) + 10;
                break;
            case 9:
                temperatura = losowanie.nextInt(11) + 8;
                break;
            case 10:
                temperatura = losowanie.nextInt(16);
                break;
            case 11:
                temperatura = losowanie.nextInt(15) - 5;
                break;               
        }
        
        temperatura -= 0.65 * stacja.getWysokoscNpm()/100;
        Pomiar pomiar = new Pomiar(Integer.valueOf(1), temperatura, aktualnyCzas.getTime(), aktualnyCzas.getTime(), czujnik, element, rodzajChmur);
        em.persist(pomiar);
        
        Pomiar pomiarOdczuwalny;
        
        element = znajdzElement("Temperatura odczuwalna");
        
        if(aktualnyCzas.get(Calendar.HOUR_OF_DAY) > 0 && aktualnyCzas.get(Calendar.HOUR_OF_DAY) <= 6){
            temperatura -= losowanie.nextInt(4) + 3;
        } else if(aktualnyCzas.get(Calendar.HOUR_OF_DAY) > 6 && aktualnyCzas.get(Calendar.HOUR_OF_DAY) <= 12 ){
            temperatura -= losowanie.nextInt(3) + 1;
        } else if(aktualnyCzas.get(Calendar.HOUR_OF_DAY) > 12 && aktualnyCzas.get(Calendar.HOUR_OF_DAY) <= 18 ){
            temperatura += losowanie.nextInt(3) + 1;
        } else{            
            temperatura += losowanie.nextInt(3) - 1;
        }
        
        temperatura -= round(wiatr.getWartosc()/4, 2);
        pomiarOdczuwalny = new Pomiar(Integer.valueOf(1), temperatura, aktualnyCzas.getTime(), aktualnyCzas.getTime(), czujnik, element, rodzajChmur);
        em.persist(pomiarOdczuwalny);
        
        return pomiar;
    }

    public Pomiar generujWilgotnosc(Integer idStacji, Rodzajchmur rodzajChmur, Calendar aktualnyCzas, Pomiar temperatura) {
        Stacja stacja = em.find(Stacja.class, idStacji);
        Czujnik czujnik = null;
        for(Czujnik c : stacja.getCzujnikList()){
            if(c.getNazwa().equals("Higrometr"))
                czujnik = c;
        }
        
        Element element = znajdzElement("Wilgotnosc");
        
        
        Random losowanie = new Random();
        double punktyRosy = losowanie.nextInt(41);
        double roznica = temperatura.getWartosc() - punktyRosy;
        double wilgotnosc;
        
        if(roznica < 1.5)
            wilgotnosc = 100;
        else if(roznica < 3)
            wilgotnosc = 90;
        else if(roznica < 5)
            wilgotnosc = 80;
        else if(roznica < 7)
            wilgotnosc = 70;        
        else if(roznica < 10)
            wilgotnosc = 60;
        else if(roznica < 13)
            wilgotnosc = 50;
        else if(roznica < 18)
            wilgotnosc = 40;
        else if(roznica < 24)
            wilgotnosc = 30;
        else if(roznica < 36)
            wilgotnosc = 20;
        else 
            wilgotnosc = 10;
        
        Pomiar pomiar = new Pomiar(Integer.valueOf(1), wilgotnosc, aktualnyCzas.getTime(), aktualnyCzas.getTime(), czujnik, element, rodzajChmur);
        em.persist(pomiar);
        
        return pomiar;
    }
}