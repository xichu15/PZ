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
@Table(name = "ARCHIWUMPOMIAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archiwumpomiar.findAll", query = "SELECT a FROM Archiwumpomiar a"),
    @NamedQuery(name = "Archiwumpomiar.findByIdArchiwumPomiar", query = "SELECT a FROM Archiwumpomiar a WHERE a.idArchiwumPomiar = :idArchiwumPomiar"),
    @NamedQuery(name = "Archiwumpomiar.findByDataPomiaru", query = "SELECT a FROM Archiwumpomiar a WHERE a.dataPomiaru = :dataPomiaru"),
    @NamedQuery(name = "Archiwumpomiar.findByCzasPomiaru", query = "SELECT a FROM Archiwumpomiar a WHERE a.czasPomiaru = :czasPomiaru"),
    @NamedQuery(name = "Archiwumpomiar.findByWartosc", query = "SELECT a FROM Archiwumpomiar a WHERE a.wartosc = :wartosc")})
public class Archiwumpomiar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ARCHIWUM_POMIAR")
    private Integer idArchiwumPomiar;
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

    public Archiwumpomiar() {
    }

    public Archiwumpomiar(Integer idArchiwumPomiar) {
        this.idArchiwumPomiar = idArchiwumPomiar;
    }

    public Integer getIdArchiwumPomiar() {
        return idArchiwumPomiar;
    }

    public void setIdArchiwumPomiar(Integer idArchiwumPomiar) {
        this.idArchiwumPomiar = idArchiwumPomiar;
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
        hash += (idArchiwumPomiar != null ? idArchiwumPomiar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archiwumpomiar)) {
            return false;
        }
        Archiwumpomiar other = (Archiwumpomiar) object;
        if ((this.idArchiwumPomiar == null && other.idArchiwumPomiar != null) || (this.idArchiwumPomiar != null && !this.idArchiwumPomiar.equals(other.idArchiwumPomiar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Archiwumpomiar[ idArchiwumPomiar=" + idArchiwumPomiar + " ]";
    }
    
}
