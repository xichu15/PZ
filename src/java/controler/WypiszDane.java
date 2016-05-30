/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Archiwumpomiar;
import model.Czujnik;
import model.Element;
import model.Gatunekchmur;
import model.Pomiar;
import model.Rodzajchmur;
import model.Stacja;
import model.Uzytkownik;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Named(value = "wypiszDane")
@ViewScoped
public class WypiszDane implements Serializable{
    private static Logger logger = Logger.getLogger(".control.wypiszDane");
    
    @EJB
    private DataBean db;
    private List<Uzytkownik> uzytkownicy;
    private List<Stacja> stacje;
    private List<Pomiar> pomiary;
    private List<Element> elementy;
    private List<Czujnik> czujniki;
    private List<Rodzajchmur> rodzaje;
    private List<Gatunekchmur> gatunki;
    private List<Archiwumpomiar> archiwum;
    
    public WypiszDane() {
    }
    
    public List<Uzytkownik> pobierzUzytkownikow(){
        try{
            this.uzytkownicy = db.pobierzUzytkownikow();
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac uzytkownikow");
            e.printStackTrace();
        }
        return uzytkownicy;
    }
    
    public List<Stacja> pobierzStacje(){
        try{
            this.stacje = db.pobierzStacje();
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac stacji");
            e.printStackTrace();
        }
        return stacje;
    }

    public List<Pomiar> pobierzPomiary(){
        try{
            this.pomiary = db.pobierzPomiary();
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac pomiarow");
            e.printStackTrace();
        }
        return pomiary;
    }

    public List<Element> pobierzElementy(){
        try{
            this.elementy = db.pobierzElementy();
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac elementow");
            e.printStackTrace();
        }
        return elementy;
    }

    public List<Czujnik> pobierzCzujniki(){
        try{
            this.czujniki = db.pobierzCzujniki();
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac czujnikow");
            e.printStackTrace();
        }
        return czujniki;
    }

    public List<Rodzajchmur> pobierzRodzajeChmur(){
        try{
            this.rodzaje = db.pobierzRodzajeChmur();
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac rodzajow chmur");
            e.printStackTrace();
        }
        return rodzaje;
    }

    public List<Gatunekchmur> pobierzGatunkiChmur(){
        try{
            this.gatunki = db.pobierzGatunkiChmur();
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac gatunkow chmur");
            e.printStackTrace();
        }
        return gatunki;
    }

    public List<Archiwumpomiar> pobierzArchiwa(){
        try{
            this.archiwum = db.pobierzArchiwa();
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac archiwum");
            e.printStackTrace();
        }
        return archiwum;
    }
 
}
