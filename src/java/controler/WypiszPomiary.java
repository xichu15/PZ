/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Archiwumpomiar;
import model.Pomiar;
import model.Stacja;

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
    private static Integer idStacji = 0;
    private Date dataOd = new Date();
    private Date dataDo = new Date();
    private List<Pomiar> pomiary;
    private List<Archiwumpomiar> archiwa;
    
    public WypiszPomiary() {
    }
    
    public List<Pomiar> pobierzPomiary(){
        System.out.println("Posiadane id: " + idStacji);
        try{
            this.pomiary = db.pobierzPomiary(getIdStacji());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac pomiarow dla stacji");
            e.printStackTrace();
        }
        return pomiary;
    }    
    
    public List<Archiwumpomiar> pobierzArchiwum(){
        if(getDataOd().toString().isEmpty()){
            this.setDataOd(new Date());
        }
        if(getDataDo().toString().isEmpty()){
            this.setDataDo(new Date());
        }
        System.out.println("Posiadane id: " + idStacji);
        try{
            this.archiwa = db.pobierzArchiwum(getIdStacji(), getDataOd(), getDataDo());
        }
        catch(Exception e){
            logger.warning("Nie udalo sie pobrac pomiarow dla stacji");
            e.printStackTrace();
        }
        return archiwa;
    }        
    
    
    public void wybierzStacje(){
        System.out.println("Nasze idStacji: " + idStacji);
        RysujWykresy rw = new RysujWykresy();
        rw.setIdStacji(idStacji);
        RysujWykresyArchiwum rwa = new RysujWykresyArchiwum();
        rwa.setIdStacji(idStacji);
    }

    public Integer getIdStacji() {
        return WypiszPomiary.idStacji;
    }

    public void setIdStacji(Integer idStacji2) {
        WypiszPomiary.idStacji = idStacji2;
    }
    
    public void przypiszId(Integer idStacji){
        WypiszPomiary.idStacji = idStacji;
    }

    public Date getDataOd() {
        return dataOd;
    }

    public void setDataOd(Date dataOd) {
        RysujWykresyArchiwum rwa = new RysujWykresyArchiwum();
        rwa.setDataOd(dataOd);
        this.dataOd = dataOd;
    }

    public Date getDataDo() {
        System.out.println("Jestem w seterze!");
        RysujWykresyArchiwum rwa = new RysujWykresyArchiwum();
        rwa.setDataDo(dataDo);
        return dataDo;
    }

    public void setDataDo(Date dataDo) {
        this.dataDo = dataDo;
    }
    
}
