/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import ejb.DataBean;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Archiwumpomiar;
import model.Pomiar;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Named(value = "rysujWykresy")
@ViewScoped
public class RysujWykresy implements Serializable{
    private static Logger logger = Logger.getLogger(".control.rysujWykresy");
    
    @EJB
    private DataBean db;
    private static Integer idStacji = 0;
    private Date dataOd = new Date();
    private Date dataDo = new Date();
    private List<Pomiar> pomiary;
    private List<Archiwumpomiar> archiwa;

    private LineChartModel temperatura;
    private LineChartModel wilgotnosc;
    private LineChartModel cisnienie;
    private LineChartModel wiatr;
    private BarChartModel opady;
       
    @PostConstruct
    public void init() {
        createLineModels();
    }
 
    
    public RysujWykresy() {
    }

    private void createLineModels() {
        this.pomiary = db.pobierzPomiary(idStacji);
        Collections.reverse(pomiary);
        
        temperatura = initTemperatura();
        getTemperatura().setTitle("Temperatura");
        getTemperatura().setLegendPosition("e");
        getTemperatura().setShowPointLabels(true);
        getTemperatura().getAxes().put(AxisType.X, new CategoryAxis("Data/czas"));
        Axis yAxis = getTemperatura().getAxis(AxisType.Y);
        yAxis.setLabel("Temperatura[C]");
        yAxis.setMin(-30);
        yAxis.setMax(40);
        
        wilgotnosc = initWilgotnosc();
        getWilgotnosc().setTitle("Wigotność");
        getWilgotnosc().setLegendPosition("e");
        getWilgotnosc().setShowPointLabels(true);
        getWilgotnosc().getAxes().put(AxisType.X, new CategoryAxis("Data/czas"));
        yAxis = getWilgotnosc().getAxis(AxisType.Y);
        yAxis.setLabel("Wilgotność[%]");
        yAxis.setMin(0);
        yAxis.setMax(100);

        cisnienie = initCisnienie();
        getCisnienie().setTitle("Ciśnienie");
        getCisnienie().setLegendPosition("e");
        getCisnienie().setShowPointLabels(true);
        getCisnienie().getAxes().put(AxisType.X, new CategoryAxis("Data/czas"));
        yAxis = getCisnienie().getAxis(AxisType.Y);
        yAxis.setLabel("Ciśnienie[hPa]");
        yAxis.setMin(950);
        yAxis.setMax(1050);

        wiatr = initWiatr();
        getWiatr().setTitle("Wiatr");
        getWiatr().setLegendPosition("e");
        getWiatr().setShowPointLabels(true);
        getWiatr().getAxes().put(AxisType.X, new CategoryAxis("Data/czas"));
        yAxis = getWiatr().getAxis(AxisType.Y);
        yAxis.setLabel("Predkość wiatru[km/h]");
        yAxis.setMin(0);
        yAxis.setMax(30);

        opady = initOpady();
        getOpady().setTitle("Opady");
        getOpady().setLegendPosition("e");
        getOpady().setShowPointLabels(true);
        getOpady().getAxes().put(AxisType.X, new CategoryAxis("Data/czas"));
        yAxis = getOpady().getAxis(AxisType.Y);
        yAxis.setLabel("Wysokość opadów[mm]");
        yAxis.setMin(0);
        yAxis.setMax(15);
    }
     
    private LineChartModel initTemperatura() {
        
        LineChartModel model = new LineChartModel();
        LineChartSeries rzeczywista = new LineChartSeries();
        rzeczywista.setLabel("Temperatura rzeczywista");
        
        for(Pomiar p : pomiary){
            if(p.getIdElement().getNazwa().equals("Temperatura rzeczywista"))
                rzeczywista.set(p.getDataString(), p.getWartosc());
        }
 
        LineChartSeries odczuwalna = new LineChartSeries();
        odczuwalna.setLabel("Temperatura odczuwalna");
        
        for(Pomiar p : pomiary){
            if(p.getIdElement().getNazwa().equals("Temperatura odczuwalna"))
                odczuwalna.set(p.getDataString(), p.getWartosc());
        }
 
        model.addSeries(rzeczywista);
        model.addSeries(odczuwalna);
         
        return model;
    }
    
    private LineChartModel initWilgotnosc() {
        
        LineChartModel model = new LineChartModel();
        LineChartSeries sWilgotnosc = new LineChartSeries();
        sWilgotnosc.setLabel("Wilgotność");
        
        for(Pomiar p : pomiary){
            if(p.getIdElement().getNazwa().equals("Wilgotnosc"))
                sWilgotnosc.set(p.getDataString(), p.getWartosc());
        }
 
        model.addSeries(sWilgotnosc);
         
        return model;
    }
 
    private LineChartModel initCisnienie() {
        
        LineChartModel model = new LineChartModel();
        LineChartSeries sCisnienie = new LineChartSeries();
        sCisnienie.setLabel("Ciśnienie");
        
        for(Pomiar p : pomiary){
            if(p.getIdElement().getNazwa().equals("Cisnienie"))
                sCisnienie.set(p.getDataString(), p.getWartosc());
        }
 
        model.addSeries(sCisnienie);
         
        return model;
    }
 
    private LineChartModel initWiatr() {
        
        LineChartModel model = new LineChartModel();
        LineChartSeries sWiatr = new LineChartSeries();
        sWiatr.setLabel("Predkość wiatru");
        
        for(Pomiar p : pomiary){
            if(p.getIdElement().getNazwa().equals("Predkosc wiatru"))
                sWiatr.set(p.getDataString(), p.getWartosc());
        }
 
        model.addSeries(sWiatr);
         
        return model;
    }    

    private BarChartModel initOpady() {
        
        BarChartModel model = new BarChartModel();
        ChartSeries sOpady = new ChartSeries();
        sOpady.setLabel("Ilość opadów");
        
        for(Pomiar p : pomiary){
            if(p.getIdElement().getNazwa().equals("Ilosc opadow"))
                sOpady.set(p.getDataString(), p.getWartosc());
        }

        model.addSeries(sOpady);
         
        return model;
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
    }

    
    
    public Integer getIdStacji() {
        return RysujWykresy.idStacji;
    }

    public void setIdStacji(Integer idStacji2) {
        RysujWykresy.idStacji = idStacji2;
    }
    
    public void przypiszId(Integer idStacji){
        RysujWykresy.idStacji = idStacji;
    }

    public Date getDataOd() {
        return dataOd;
    }

    public void setDataOd(Date dataOd) {
        this.dataOd = dataOd;
    }

    public Date getDataDo() {
        return dataDo;
    }

    public void setDataDo(Date dataDo) {
        this.dataDo = dataDo;
    }

    public LineChartModel getTemperatura() {
        return temperatura;
    }

    public LineChartModel getWilgotnosc() {
        return wilgotnosc;
    }

    public LineChartModel getCisnienie() {
        return cisnienie;
    }

    public LineChartModel getWiatr() {
        return wiatr;
    }

    public BarChartModel getOpady() {
        return opady;
    }
    
}
