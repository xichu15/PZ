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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NP550P5C-SO4PL
 */
@Entity
@Table(name = "GRUPA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupa.findAll", query = "SELECT g FROM Grupa g"),
    @NamedQuery(name = "Grupa.findByIdGrupa", query = "SELECT g FROM Grupa g WHERE g.idGrupa = :idGrupa"),
    @NamedQuery(name = "Grupa.findByUzytkownik", query = "SELECT g FROM Grupa g WHERE g.uzytkownik = :uzytkownik"),
    @NamedQuery(name = "Grupa.findByNazwagrupy", query = "SELECT g FROM Grupa g WHERE g.nazwagrupy = :nazwagrupy")})
public class Grupa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRUPA")
    private Integer idGrupa;
    @Size(max = 50)
    @Column(name = "UZYTKOWNIK")
    private String uzytkownik;
    @Size(max = 255)
    @Column(name = "NAZWAGRUPY")
    private String nazwagrupy;

    public Grupa() {
    }

    public Grupa(Integer idGrupa) {
        this.idGrupa = idGrupa;
    }

    public Integer getIdGrupa() {
        return idGrupa;
    }

    public void setIdGrupa(Integer idGrupa) {
        this.idGrupa = idGrupa;
    }

    public String getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(String uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public String getNazwagrupy() {
        return nazwagrupy;
    }

    public void setNazwagrupy(String nazwagrupy) {
        this.nazwagrupy = nazwagrupy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupa != null ? idGrupa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupa)) {
            return false;
        }
        Grupa other = (Grupa) object;
        if ((this.idGrupa == null && other.idGrupa != null) || (this.idGrupa != null && !this.idGrupa.equals(other.idGrupa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Grupa[ idGrupa=" + idGrupa + " ]";
    }
    
}
