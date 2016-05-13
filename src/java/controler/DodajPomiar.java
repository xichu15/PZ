package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "dodajPomiar")
@RequestScoped
public class DodajPomiar implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.dodajPomiar");
    @EJB
    private DataBean db;
    
    private double wartosc;
    private Date dataPomiaru;
    private Integer godzinaPomiaru;
    private Integer minutaPomiaru;

    public DodajPomiar() {}
    
    public void dodajNowyPomiar(){
        try{
            Calendar czas = Calendar.getInstance();
            czas.setTime(dataPomiaru);
            czas.add(Calendar.HOUR, godzinaPomiaru);
            czas.add(Calendar.MINUTE, minutaPomiaru);
            db.dodajPomiar(getWartosc(), czas.getTime(), czas.getTime());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie dodac pomiaru");
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
    
}
