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
@Table(name = "UZYTKOWNIK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u"),
    @NamedQuery(name = "Uzytkownik.findByIdUzytkownik", query = "SELECT u FROM Uzytkownik u WHERE u.idUzytkownik = :idUzytkownik"),
    @NamedQuery(name = "Uzytkownik.findByLogin", query = "SELECT u FROM Uzytkownik u WHERE u.login = :login"),
    @NamedQuery(name = "Uzytkownik.findByEmail", query = "SELECT u FROM Uzytkownik u WHERE u.email = :email"),
    @NamedQuery(name = "Uzytkownik.findByHaslo", query = "SELECT u FROM Uzytkownik u WHERE u.haslo = :haslo")})
public class Uzytkownik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_UZYTKOWNIK")
    private Integer idUzytkownik;
    @Size(max = 50)
    @Column(name = "LOGIN")
    private String login;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 255)
    @Column(name = "HASLO")
    private String haslo;

    public Uzytkownik() {
    }

    public Uzytkownik(Integer idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    public Uzytkownik(Integer valueOf, String login, String haslo, String email) {
        this.idUzytkownik = valueOf;
        this.login = login;
        this.haslo = haslo;
        this.email = email;
    }

    public Integer getIdUzytkownik() {
        return idUzytkownik;
    }

    public void setIdUzytkownik(Integer idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUzytkownik != null ? idUzytkownik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzytkownik)) {
            return false;
        }
        Uzytkownik other = (Uzytkownik) object;
        if ((this.idUzytkownik == null && other.idUzytkownik != null) || (this.idUzytkownik != null && !this.idUzytkownik.equals(other.idUzytkownik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Uzytkownik[ idUzytkownik=" + idUzytkownik + " ]";
    }
    
}
