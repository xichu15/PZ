/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Pomiar;
import model.Rodzajchmur;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Named(value = "generujPomiary")
@ViewScoped
public class GenerujPomiary implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.generujPomiary");
    @EJB
    private DataBean db;
    
    private double wartosc;
    private Integer idStacji;
    private Date dataPomiaru;
    private Integer godzinaPomiaru;
    private Integer minutaPomiaru;

    public GenerujPomiary() {}
    
    public void generujPomiary(){
        Calendar aktualnyCzas = Calendar.getInstance();
        Rodzajchmur rodzajChmur = null;
        Pomiar cisnienie = null;
        Pomiar wiatr = null;
        Pomiar opady = null;
        Pomiar temperatura = null;
        Pomiar wilgotnosc = null;
        try{
            rodzajChmur = db.generujChmury(getIdStacji());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie znalezc chmur");
            e.printStackTrace();
        }
        try{
            cisnienie = db.generujCisnienie(getIdStacji(), rodzajChmur, aktualnyCzas);
        }
        catch(Exception e){
            logger.warning("Nie udalo sie znalezc cisnienia");
            e.printStackTrace();
        }  
        try{
            wiatr = db.generujWiatr(getIdStacji(), rodzajChmur, aktualnyCzas, cisnienie);
        }
        catch(Exception e){
            logger.warning("Nie udalo sie znalezc wiatru");
            e.printStackTrace();
        }   
        try{
            opady = db.generujOpady(getIdStacji(), rodzajChmur, aktualnyCzas, cisnienie);
        }
        catch(Exception e){
            logger.warning("Nie udalo sie znalezc opadow");
            e.printStackTrace();
        } 
        try{
            temperatura = db.generujTemperature(getIdStacji(), rodzajChmur, aktualnyCzas, cisnienie, wiatr, opady);
        }
        catch(Exception e){
            logger.warning("Nie udalo sie znalezc temperatury");
            e.printStackTrace();
        }               
        try{
            wilgotnosc = db.generujWilgotnosc(getIdStacji(), rodzajChmur, aktualnyCzas, temperatura);
        }
        catch(Exception e){
            logger.warning("Nie udalo sie znalezc wilgotnosci");
            e.printStackTrace();
        }         
    }    

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public Date getDataPomiaru() {
        return dataPomiaru;
    }

    public void setDataPomiaru(Date dataPomiaru) {
        this.dataPomiaru = dataPomiaru;
    }

    public Integer getGodzinaPomiaru() {
        return godzinaPomiaru;
    }

    public void setGodzinaPomiaru(Integer godzinaPomiaru) {
        this.godzinaPomiaru = godzinaPomiaru;
    }

    public Integer getMinutaPomiaru() {
        return minutaPomiaru;
    }

    public void setMinutaPomiaru(Integer minutaPomiaru) {
        this.minutaPomiaru = minutaPomiaru;
    }

    public Integer getIdStacji() {
        return idStacji;
    }

    public void setIdStacji(Integer idStacji) {
        this.idStacji = idStacji;
    }
    
}
