/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Named(value = "edytujStacje")
@ViewScoped
public class EdytujStacje implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.edytujStacje");
    @EJB
    private DataBean db;
    
    private Integer idStacja;
    private String nowaNazwa;
    private String nowaStrefaCzasowa;
    private int nowePrzesuniecie;
    private double nowaDlugoscGeograficzna;
    private double nowaSzerokoscGeograficzna;
    private int nowaWysokoscNpm;
    
    public EdytujStacje() {}
    
    public void edytujUzytkownika(){
        try{
            db.edytujStacje(getIdStacja(), getNowaNazwa(), getNowaStrefaCzasowa(), getNowePrzesuniecie(), 
                    getNowaDlugoscGeograficzna(), getNowaSzerokoscGeograficzna(), getNowaWysokoscNpm());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie edytowac stacji");
            e.printStackTrace();
        }
    }    

    public Integer getIdStacja() {
        return idStacja;
    }

    public void setIdStacja(Integer idStacja) {
        this.idStacja = idStacja;
    }

    public String getNowaNazwa() {
        return nowaNazwa;
    }

    public void setNowaNazwa(String nowaNazwa) {
        this.nowaNazwa = nowaNazwa;
    }

    public String getNowaStrefaCzasowa() {
        return nowaStrefaCzasowa;
    }

    public void setNowaStrefaCzasowa(String nowaStrefaCzasowa) {
        this.nowaStrefaCzasowa = nowaStrefaCzasowa;
    }

    public int getNowePrzesuniecie() {
        return nowePrzesuniecie;
    }

    public void setNowePrzesuniecie(int nowePrzesuniecie) {
        this.nowePrzesuniecie = nowePrzesuniecie;
    }

    public double getNowaDlugoscGeograficzna() {
        return nowaDlugoscGeograficzna;
    }

    public void setNowaDlugoscGeograficzna(double nowaDlugoscGeograficzna) {
        this.nowaDlugoscGeograficzna = nowaDlugoscGeograficzna;
    }

    public double getNowaSzerokoscGeograficzna() {
        return nowaSzerokoscGeograficzna;
    }

    public void setNowaSzerokoscGeograficzna(double nowaSzerokoscGeograficzna) {
        this.nowaSzerokoscGeograficzna = nowaSzerokoscGeograficzna;
    }

    public int getNowaWysokoscNpm() {
        return nowaWysokoscNpm;
    }

    public void setNowaWysokoscNpm(int nowaWysokoscNpm) {
        this.nowaWysokoscNpm = nowaWysokoscNpm;
    }
}
