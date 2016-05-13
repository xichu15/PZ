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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "GATUNEKCHMUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gatunekchmur.findAll", query = "SELECT g FROM Gatunekchmur g"),
    @NamedQuery(name = "Gatunekchmur.findByIdGatunekChmur", query = "SELECT g FROM Gatunekchmur g WHERE g.idGatunekChmur = :idGatunekChmur"),
    @NamedQuery(name = "Gatunekchmur.findByNazwa", query = "SELECT g FROM Gatunekchmur g WHERE g.nazwa = :nazwa")})
public class Gatunekchmur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GATUNEK_CHMUR")
    private Integer idGatunekChmur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAZWA")
    private String nazwa;
    @JoinTable(name = "RODZAJGATUNEK", joinColumns = {
        @JoinColumn(name = "ID_GATUNEK_CHMUR", referencedColumnName = "ID_GATUNEK_CHMUR")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_RODZAJ_CHMUR", referencedColumnName = "ID_RODZAJ_CHMUR")})
    @ManyToMany
    private List<Rodzajchmur> rodzajchmurList;

    public Gatunekchmur() {
    }

    public Gatunekchmur(Integer idGatunekChmur) {
        this.idGatunekChmur = idGatunekChmur;
    }

    public Gatunekchmur(Integer idGatunekChmur, String nazwa) {
        this.idGatunekChmur = idGatunekChmur;
        this.nazwa = nazwa;
    }

    public Integer getIdGatunekChmur() {
        return idGatunekChmur;
    }

    public void setIdGatunekChmur(Integer idGatunekChmur) {
        this.idGatunekChmur = idGatunekChmur;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public List<Rodzajchmur> getRodzajchmurList() {
        return rodzajchmurList;
    }

    public void setRodzajchmurList(List<Rodzajchmur> rodzajchmurList) {
        this.rodzajchmurList = rodzajchmurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGatunekChmur != null ? idGatunekChmur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gatunekchmur)) {
            return false;
        }
        Gatunekchmur other = (Gatunekchmur) object;
        if ((this.idGatunekChmur == null && other.idGatunekChmur != null) || (this.idGatunekChmur != null && !this.idGatunekChmur.equals(other.idGatunekChmur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Gatunekchmur[ idGatunekChmur=" + idGatunekChmur + " ]";
    }
    
}
