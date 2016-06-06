package controler;
import ejb.DataBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
  
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
 
@ManagedBean
@ViewScoped
public class MarkerSelectionView implements Serializable {
     
    private MapModel simpleModel;
  
    private Marker marker;
  
    @EJB
    private DataBean db;    
    private List<Marker> znaczniki;
  
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();

        this.znaczniki = db.pobierzLokacje();

        for(Marker znacznik : znaczniki){
            simpleModel.addOverlay(znacznik);          
        }

    }
      
    public MapModel getSimpleModel() {
        return simpleModel;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        Integer znalezioneId = db.znajdzIdStacji(marker.getTitle());
        WypiszPomiary wp = new WypiszPomiary();
        wp.przypiszId(znalezioneId);
        RysujWykresy rw = new RysujWykresy();
        rw.setIdStacji(znalezioneId);
        RysujWykresyArchiwum rwa = new RysujWykresyArchiwum();
        rwa.setIdStacji(znalezioneId);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wybrana lokacja", marker.getTitle()));
    }
      
    public Marker getMarker() {
        return marker;
    }
}