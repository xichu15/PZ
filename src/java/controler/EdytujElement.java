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
@Named(value = "edytujElement")
@ViewScoped
public class EdytujElement implements Serializable{
    private static Logger logger = Logger.getLogger(".controler.edytujElement");
    @EJB
    private DataBean db;
    
    private Integer idElement;
    private String nowaNazwa;
    private String nowaJednostka;
    
    public EdytujElement() {}
    
    public void edytujElement(){
        try{
            db.edytujElement(getIdElement(), getNowaNazwa(), getNowaJednostka());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie edytowac elementu");
            e.printStackTrace();
        }
    }    

    public Integer getIdElement() {
        return idElement;
    }

    public void setIdElement(Integer idElement) {
        this.idElement = idElement;
    }

    public String getNowaNazwa() {
        return nowaNazwa;
    }

    public void setNowaNazwa(String nowaNazwa) {
        this.nowaNazwa = nowaNazwa;
    }

    public String getNowaJednostka() {
        return nowaJednostka;
    }

    public void setNowaJednostka(String nowaJednostka) {
        this.nowaJednostka = nowaJednostka;
    }

}
