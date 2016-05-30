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
import model.Pomiar;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Named(value = "wypiszPomiary")
@ViewScoped
public class WypiszPomiary implements Serializable{
    private static Logger logger = Logger.getLogger(".control.wypiszPomiary");
    
    @EJB
    private DataBean db;
    private Integer idStacji;
    private List<Pomiar> pomiary;
    
    public WypiszPomiary() {
    }
    
    public List<Pomiar> pobierzPomiary(){
        try{
            this.pomiary = db.pobierzPomiary(getIdStacji());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac pomiarow dla stacji");
            e.printStackTrace();
        }
        return pomiary;
    }    

    public Integer getIdStacji() {
        return idStacji;
    }

    public void setIdStacji(Integer idStacji) {
        this.idStacji = idStacji;
    }
    
    
}
