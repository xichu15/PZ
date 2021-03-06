package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "edytujPomiar")
@ViewScoped
public class EdytujPomiar implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.edytujPomiar");
    @EJB
    private DataBean db;
    
    private Integer idPomiar;
    private double nowaWartosc;
    private Date nowaDataPomiaru;
    private Integer nowaGodzinaPomiaru;
    private Integer nowaMinutaPomiaru;
    
    public EdytujPomiar() {}
    
    public void edytujPomiar(){
        try{
            Calendar czas = Calendar.getInstance();
            czas.setTime(nowaDataPomiaru);
            czas.add(Calendar.HOUR, nowaGodzinaPomiaru);
            czas.add(Calendar.MINUTE, nowaMinutaPomiaru); 
            db.edytujPomiar(getIdPomiar(), getNowaWartosc(), czas.getTime(), czas.getTime());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie edytowac pomiaru");
            e.printStackTrace();
        }
    }    

    public Integer getIdPomiar() {
        return idPomiar;
    }

    public void setIdPomiar(Integer idPomiar) {
        this.idPomiar = idPomiar;
    }

    public double getNowaWartosc() {
        return nowaWartosc;
    }

    public void setNowaWartosc(double nowaWartosc) {
        this.nowaWartosc = nowaWartosc;
    }

    public Date getNowaDataPomiaru() {
        return nowaDataPomiaru;
    }

    public void setNowaDataPomiaru(Date nowaDataPomiaru) {
        this.nowaDataPomiaru = nowaDataPomiaru;
    }

    public Integer getNowaGodzinaPomiaru() {
        return nowaGodzinaPomiaru;
    }

    public void setNowaGodzinaPomiaru(Integer nowaGodzinaPomiaru) {
        this.nowaGodzinaPomiaru = nowaGodzinaPomiaru;
    }

    public Integer getNowaMinutaPomiaru() {
        return nowaMinutaPomiaru;
    }

    public void setNowaMinutaPomiaru(Integer nowaMinutaPomiaru) {
        this.nowaMinutaPomiaru = nowaMinutaPomiaru;
    }
}
