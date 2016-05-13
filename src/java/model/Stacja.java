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

@Entity
@Table(name = "STACJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stacja.findAll", query = "SELECT s FROM Stacja s"),
    @NamedQuery(name = "Stacja.findByIdStacja", query = "SELECT s FROM Stacja s WHERE s.idStacja = :idStacja"),
    @NamedQuery(name = "Stacja.findByNazwa", query = "SELECT s FROM Stacja s WHERE s.nazwa = :nazwa"),
    @NamedQuery(name = "Stacja.findByStrefaCzasowa", query = "SELECT s FROM Stacja s WHERE s.strefaCzasowa = :strefaCzasowa"),
    @NamedQuery(name = "Stacja.findByPrzesuniecie", query = "SELECT s FROM Stacja s WHERE s.przesuniecie = :przesuniecie"),
    @NamedQuery(name = "Stacja.findByDlugoscGeograficzna", query = "SELECT s FROM Stacja s WHERE s.dlugoscGeograficzna = :dlugoscGeograficzna"),
    @NamedQuery(name = "Stacja.findBySzerokoscGeograficzna", query = "SELECT s FROM Stacja s WHERE s.szerokoscGeograficzna = :szerokoscGeograficzna"),
    @NamedQuery(name = "Stacja.findByWysokoscNpm", query = "SELECT s FROM Stacja s WHERE s.wysokoscNpm = :wysokoscNpm")})
public class Stacja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_STACJA")
    private Integer idStacja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAZWA")
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STREFA_CZASOWA")
    private String strefaCzasowa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRZESUNIECIE")
    private int przesuniecie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DLUGOSC_GEOGRAFICZNA")
    private double dlugoscGeograficzna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SZEROKOSC_GEOGRAFICZNA")
    private double szerokoscGeograficzna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WYSOKOSC_NPM")
    private int wysokoscNpm;
    @OneToMany(mappedBy = "idStacja")
    private List<Czujnik> czujnikList;

    public Stacja() {
    }

    public Stacja(Integer idStacja) {
        this.idStacja = idStacja;
    }

    public Stacja(Integer idStacja, String nazwa, String strefaCzasowa, int przesuniecie, double dlugoscGeograficzna, double szerokoscGeograficzna, int wysokoscNpm) {
        this.idStacja = idStacja;
        this.nazwa = nazwa;
        this.strefaCzasowa = strefaCzasowa;
        this.przesuniecie = przesuniecie;
        this.dlugoscGeograficzna = dlugoscGeograficzna;
        this.szerokoscGeograficzna = szerokoscGeograficzna;
        this.wysokoscNpm = wysokoscNpm;
    }

    public Integer getIdStacja() {
        return idStacja;
    }

    public void setIdStacja(Integer idStacja) {
        this.idStacja = idStacja;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getStrefaCzasowa() {
        return strefaCzasowa;
    }

    public void setStrefaCzasowa(String strefaCzasowa) {
        this.strefaCzasowa = strefaCzasowa;
    }

    public int getPrzesuniecie() {
        return przesuniecie;
    }

    public void setPrzesuniecie(int przesuniecie) {
        this.przesuniecie = przesuniecie;
    }

    public double getDlugoscGeograficzna() {
        return dlugoscGeograficzna;
    }

    public void setDlugoscGeograficzna(double dlugoscGeograficzna) {
        this.dlugoscGeograficzna = dlugoscGeograficzna;
    }

    public double getSzerokoscGeograficzna() {
        return szerokoscGeograficzna;
    }

    public void setSzerokoscGeograficzna(double szerokoscGeograficzna) {
        this.szerokoscGeograficzna = szerokoscGeograficzna;
    }

    public int getWysokoscNpm() {
        return wysokoscNpm;
    }

    public void setWysokoscNpm(int wysokoscNpm) {
        this.wysokoscNpm = wysokoscNpm;
    }

    @XmlTransient
    public List<Czujnik> getCzujnikList() {
        return czujnikList;
    }

    public void setCzujnikList(List<Czujnik> czujnikList) {
        this.czujnikList = czujnikList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStacja != null ? idStacja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stacja)) {
            return false;
        }
        Stacja other = (Stacja) object;
        if ((this.idStacja == null && other.idStacja != null) || (this.idStacja != null && !this.idStacja.equals(other.idStacja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Stacja[ idStacja=" + idStacja + " ]";
    }
    
}
