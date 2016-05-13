package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "dodajStacje")
@RequestScoped
public class DodajStacje implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.dodajStacje");
    @EJB
    private DataBean db;
    
    private String nazwa;
    private String strefaCzasowa;
    private int przesuniecie;
    private double dlugoscGeograficzna;
    private double szerokoscGeograficzna;
    private int wysokoscNpm;
    
    public DodajStacje() {}
    
    public void dodajNowaStacje(){
        try{
            db.DodajStacje(getNazwa(), getStrefaCzasowa(), getPrzesuniecie(), getDlugoscGeograficzna(), getSzerokoscGeograficzna(), getWysokoscNpm());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie dodac stacji");
            e.printStackTrace();
        }
    }    

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getStrefaCzasowa() {
        return strefaCzasowa;
    }

    public void setStrefaCzasowa(String strefaCzasowa) {
        this.strefaCzasowa = strefaCzasowa;
    }

    public int getPrzesuniecie() {
        return przesuniecie;
    }

    public void setPrzesuniecie(int przesuniecie) {
        this.przesuniecie = przesuniecie;
    }

    public double getDlugoscGeograficzna() {
        return dlugoscGeograficzna;
    }

    public void setDlugoscGeograficzna(double dlugoscGeograficzna) {
        this.dlugoscGeograficzna = dlugoscGeograficzna;
    }

    public double getSzerokoscGeograficzna() {
        return szerokoscGeograficzna;
    }

    public void setSzerokoscGeograficzna(double szerokoscGeograficzna) {
        this.szerokoscGeograficzna = szerokoscGeograficzna;
    }

    public int getWysokoscNpm() {
        return wysokoscNpm;
    }

    public void setWysokoscNpm(int wysokoscNpm) {
        this.wysokoscNpm = wysokoscNpm;
    }
    
}
