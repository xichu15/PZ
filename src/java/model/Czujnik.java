/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Entity
@Table(name = "CZUJNIK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Czujnik.findAll", query = "SELECT c FROM Czujnik c"),
    @NamedQuery(name = "Czujnik.findByIdCzujnik", query = "SELECT c FROM Czujnik c WHERE c.idCzujnik = :idCzujnik"),
    @NamedQuery(name = "Czujnik.findByNazwa", query = "SELECT c FROM Czujnik c WHERE c.nazwa = :nazwa")})
public class Czujnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CZUJNIK")
    private Integer idCzujnik;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAZWA")
    private String nazwa;
    @JoinColumn(name = "ID_STACJA", referencedColumnName = "ID_STACJA")
    @ManyToOne
    private Stacja idStacja;
    @OneToMany(mappedBy = "idCzujnik")
    private List<Archiwumpomiar> archiwumpomiarList;
    @OneToMany(mappedBy = "idCzujnik")
    private List<Pomiar> pomiarList;

    public Czujnik() {
    }

    public Czujnik(Integer idCzujnik) {
        this.idCzujnik = idCzujnik;
    }

    public Czujnik(Integer idCzujnik, String nazwa) {
        this.idCzujnik = idCzujnik;
        this.nazwa = nazwa;
    }

    public Integer getIdCzujnik() {
        return idCzujnik;
    }

    public void setIdCzujnik(Integer idCzujnik) {
        this.idCzujnik = idCzujnik;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Stacja getIdStacja() {
        return idStacja;
    }

    public void setIdStacja(Stacja idStacja) {
        this.idStacja = idStacja;
    }

    @XmlTransient
    public List<Archiwumpomiar> getArchiwumpomiarList() {
        return archiwumpomiarList;
    }

    public void setArchiwumpomiarList(List<Archiwumpomiar> archiwumpomiarList) {
        this.archiwumpomiarList = archiwumpomiarList;
    }

    @XmlTransient
    public List<Pomiar> getPomiarList() {
        return pomiarList;
    }

    public void setPomiarList(List<Pomiar> pomiarList) {
        this.pomiarList = pomiarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCzujnik != null ? idCzujnik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Czujnik)) {
            return false;
        }
        Czujnik other = (Czujnik) object;
        if ((this.idCzujnik == null && other.idCzujnik != null) || (this.idCzujnik != null && !this.idCzujnik.equals(other.idCzujnik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Czujnik[ idCzujnik=" + idCzujnik + " ]";
    }
    
}
