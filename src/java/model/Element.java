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
@Table(name = "ELEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Element.findAll", query = "SELECT e FROM Element e"),
    @NamedQuery(name = "Element.findByIdElement", query = "SELECT e FROM Element e WHERE e.idElement = :idElement"),
    @NamedQuery(name = "Element.findByNazwa", query = "SELECT e FROM Element e WHERE e.nazwa = :nazwa"),
    @NamedQuery(name = "Element.findByJednostka", query = "SELECT e FROM Element e WHERE e.jednostka = :jednostka"),
    @NamedQuery(name = "Element.findByKlasa", query = "SELECT e FROM Element e WHERE e.klasa = :klasa")})
public class Element implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ELEMENT")
    private Integer idElement;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAZWA")
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "JEDNOSTKA")
    private String jednostka;
    @Size(max = 20)
    @Column(name = "KLASA")
    private String klasa;
    @OneToMany(mappedBy = "idElement")
    private List<Archiwumpomiar> archiwumpomiarList;
    @OneToMany(mappedBy = "idElement")
    private List<Pomiar> pomiarList;

    public Element() {
    }

    public Element(Integer idElement) {
        this.idElement = idElement;
    }

    public Element(Integer idElement, String nazwa, String jednostka) {
        this.idElement = idElement;
        this.nazwa = nazwa;
        this.jednostka = jednostka;
    }

    public Integer getIdElement() {
        return idElement;
    }

    public void setIdElement(Integer idElement) {
        this.idElement = idElement;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getJednostka() {
        return jednostka;
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }

    public String getKlasa() {
        return klasa;
    }

    public void setKlasa(String klasa) {
        this.klasa = klasa;
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
        hash += (idElement != null ? idElement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Element)) {
            return false;
        }
        Element other = (Element) object;
        if ((this.idElement == null && other.idElement != null) || (this.idElement != null && !this.idElement.equals(other.idElement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Element[ idElement=" + idElement + " ]";
    }
    
}
