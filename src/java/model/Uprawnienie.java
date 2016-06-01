/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
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
 * @author Adrianna
 */
@Entity
@Table(name = "UPRAWNIENIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uprawnienie.findAll", query = "SELECT u FROM Uprawnienie u"),
    @NamedQuery(name = "Uprawnienie.findByIdUprawnienie", query = "SELECT u FROM Uprawnienie u WHERE u.idUprawnienie = :idUprawnienie"),
    @NamedQuery(name = "Uprawnienie.findByNazwa", query = "SELECT u FROM Uprawnienie u WHERE u.nazwa = :nazwa")})
public class Uprawnienie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_UPRAWNIENIE")
    private Integer idUprawnienie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAZWA")
    private String nazwa;
    @OneToMany(mappedBy = "idUprawnienie")
    private Collection<Uzytkownik> uzytkownikCollection;

    public Uprawnienie() {
    }

    public Uprawnienie(Integer idUprawnienie) {
        this.idUprawnienie = idUprawnienie;
    }

    public Uprawnienie(Integer idUprawnienie, String nazwa) {
        this.idUprawnienie = idUprawnienie;
        this.nazwa = nazwa;
    }

    public Integer getIdUprawnienie() {
        return idUprawnienie;
    }

    public void setIdUprawnienie(Integer idUprawnienie) {
        this.idUprawnienie = idUprawnienie;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public Collection<Uzytkownik> getUzytkownikCollection() {
        return uzytkownikCollection;
    }

    public void setUzytkownikCollection(Collection<Uzytkownik> uzytkownikCollection) {
        this.uzytkownikCollection = uzytkownikCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUprawnienie != null ? idUprawnienie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uprawnienie)) {
            return false;
        }
        Uprawnienie other = (Uprawnienie) object;
        if ((this.idUprawnienie == null && other.idUprawnienie != null) || (this.idUprawnienie != null && !this.idUprawnienie.equals(other.idUprawnienie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Uprawnienie[ idUprawnienie=" + idUprawnienie + " ]";
    }
    
}
