package metier;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "inscription", schema = "lesinscriptions", catalog = "")
public class InscriptionEntity {
    private int numcandidat;
    private String nomcandidat;
    private String prenomcandidat;
    private Date datenaissance;
    private String adresse;
    private String cpostal;
    private String ville;

    // Auto incrémlent pour Mysql
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numcandidat", nullable = false)
    public int getNumcandidat() {
        return numcandidat;
    }

       public void setNumcandidat(int numcandidat) {
        this.numcandidat = numcandidat;
    }

    @Basic
    @Column(name = "nomcandidat", nullable = true, length = 20)
    public String getNomcandidat() {
        return nomcandidat;
    }

    public void setNomcandidat(String nomcandidat) {
        this.nomcandidat = nomcandidat;
    }

    @Basic
    @Column(name = "prenomcandidat", nullable = true, length = 20)
    public String getPrenomcandidat() {
        return prenomcandidat;
    }

    public void setPrenomcandidat(String prenomcandidat) {
        this.prenomcandidat = prenomcandidat;
    }

    @Basic
    @Column(name = "datenaissance", nullable = true)
    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    @Basic
    @Column(name = "adresse", nullable = true, length = 150)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "cpostal", nullable = true, length = 20)
    public String getCpostal() {
        return cpostal;
    }

    public void setCpostal(String cpostal) {
        this.cpostal = cpostal;
    }

    @Basic
    @Column(name = "ville", nullable = true, length = 150)
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscriptionEntity that = (InscriptionEntity) o;
        return numcandidat == that.numcandidat &&
                Objects.equals(nomcandidat, that.nomcandidat) &&
                Objects.equals(prenomcandidat, that.prenomcandidat) &&
                Objects.equals(datenaissance, that.datenaissance) &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(cpostal, that.cpostal) &&
                Objects.equals(ville, that.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numcandidat, nomcandidat, prenomcandidat, datenaissance, adresse, cpostal, ville);
    }
}
