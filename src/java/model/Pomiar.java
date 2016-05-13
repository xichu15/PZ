/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Entity
@Table(name = "POMIAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pomiar.findAll", query = "SELECT p FROM Pomiar p"),
    @NamedQuery(name = "Pomiar.findByIdPomiar", query = "SELECT p FROM Pomiar p WHERE p.idPomiar = :idPomiar"),
    @NamedQuery(name = "Pomiar.findByDataPomiaru", query = "SELECT p FROM Pomiar p WHERE p.dataPomiaru = :dataPomiaru"),
    @NamedQuery(name = "Pomiar.findByCzasPomiaru", query = "SELECT p FROM Pomiar p WHERE p.czasPomiaru = :czasPomiaru"),
    @NamedQuery(name = "Pomiar.findByWartosc", query = "SELECT p FROM Pomiar p WHERE p.wartosc = :wartosc")})
public class Pomiar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_POMIAR")
    private Integer idPomiar;
    @Column(name = "DATA_POMIARU")
    @Temporal(TemporalType.DATE)
    private Date dataPomiaru;
    @Column(name = "CZAS_POMIARU")
    @Temporal(TemporalType.TIME)
    private Date czasPomiaru;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WARTOSC")
    private Double wartosc;
    @JoinColumn(name = "ID_CZUJNIK", referencedColumnName = "ID_CZUJNIK")
    @ManyToOne
    private Czujnik idCzujnik;
    @JoinColumn(name = "ID_ELEMENT", referencedColumnName = "ID_ELEMENT")
    @ManyToOne
    private Element idElement;
    @JoinColumn(name = "ID_RODZAJ_CHMUR", referencedColumnName = "ID_RODZAJ_CHMUR")
    @ManyToOne
    private Rodzajchmur idRodzajChmur;

    public Pomiar() {
    }

    public Pomiar(Integer idPomiar) {
        this.idPomiar = idPomiar;
    }

    public Pomiar(Integer idPomiar, Double wartosc, Date dataPomiaru, Date czasPomiaru) {
        this.idPomiar = idPomiar;
        this.wartosc = wartosc;
        this.dataPomiaru = dataPomiaru;
        this.czasPomiaru = czasPomiaru;
    }    
    
    public Integer getIdPomiar() {
        return idPomiar;
    }

    public void setIdPomiar(Integer idPomiar) {
        this.idPomiar = idPomiar;
    }

    public Date getDataPomiaru() {
        return dataPomiaru;
    }

    public void setDataPomiaru(Date dataPomiaru) {
        this.dataPomiaru = dataPomiaru;
    }

    public Date getCzasPomiaru() {
        return czasPomiaru;
    }

    public void setCzasPomiaru(Date czasPomiaru) {
        this.czasPomiaru = czasPomiaru;
    }

    public Double getWartosc() {
        return wartosc;
    }

    public void setWartosc(Double wartosc) {
        this.wartosc = wartosc;
    }

    public Czujnik getIdCzujnik() {
        return idCzujnik;
    }

    public void setIdCzujnik(Czujnik idCzujnik) {
        this.idCzujnik = idCzujnik;
    }

    public Element getIdElement() {
        return idElement;
    }

    public void setIdElement(Element idElement) {
        this.idElement = idElement;
    }

    public Rodzajchmur getIdRodzajChmur() {
        return idRodzajChmur;
    }

    public void setIdRodzajChmur(Rodzajchmur idRodzajChmur) {
        this.idRodzajChmur = idRodzajChmur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPomiar != null ? idPomiar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pomiar)) {
            return false;
        }
        Pomiar other = (Pomiar) object;
        if ((this.idPomiar == null && other.idPomiar != null) || (this.idPomiar != null && !this.idPomiar.equals(other.idPomiar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pomiar[ idPomiar=" + idPomiar + " ]";
    }
    
}
