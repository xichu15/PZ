/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Entity
@Table(name = "RODZAJCHMUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rodzajchmur.findAll", query = "SELECT r FROM Rodzajchmur r"),
    @NamedQuery(name = "Rodzajchmur.findByIdRodzajChmur", query = "SELECT r FROM Rodzajchmur r WHERE r.idRodzajChmur = :idRodzajChmur"),
    @NamedQuery(name = "Rodzajchmur.findByNazwa", query = "SELECT r FROM Rodzajchmur r WHERE r.nazwa = :nazwa")})
public class Rodzajchmur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RODZAJ_CHMUR")
    private Integer idRodzajChmur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAZWA")
    private String nazwa;

    public Rodzajchmur() {
    }

    public Rodzajchmur(Integer idRodzajChmur) {
        this.idRodzajChmur = idRodzajChmur;
    }

    public Rodzajchmur(Integer idRodzajChmur, String nazwa) {
        this.idRodzajChmur = idRodzajChmur;
        this.nazwa = nazwa;
    }

    public Integer getIdRodzajChmur() {
        return idRodzajChmur;
    }

    public void setIdRodzajChmur(Integer idRodzajChmur) {
        this.idRodzajChmur = idRodzajChmur;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRodzajChmur != null ? idRodzajChmur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rodzajchmur)) {
            return false;
        }
        Rodzajchmur other = (Rodzajchmur) object;
        if ((this.idRodzajChmur == null && other.idRodzajChmur != null) || (this.idRodzajChmur != null && !this.idRodzajChmur.equals(other.idRodzajChmur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Rodzajchmur[ idRodzajChmur=" + idRodzajChmur + " ]";
    }
    
}
