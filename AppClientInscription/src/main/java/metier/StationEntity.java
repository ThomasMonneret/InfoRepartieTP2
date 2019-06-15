package metier;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "station", schema = "autolib", catalog = "")
public class StationEntity {
    private Integer idStation;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String adresse;
    private Integer numero;
    private String ville;
    private Integer codePostal;
    private Collection<BorneEntity> bornesByIdStation;

    @Id
    @Column(name = "idStation", nullable = false)
    public Integer getIdStation() {
        return idStation;
    }

    public void setIdStation(Integer idStation) {
        this.idStation = idStation;
    }

    @Basic
    @Column(name = "latitude", nullable = false, precision = 6)
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude", nullable = false, precision = 6)
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "adresse", nullable = true, length = 200)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "numero", nullable = true)
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Basic
    @Column(name = "ville", nullable = true, length = 100)
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Basic
    @Column(name = "code_postal", nullable = true)
    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationEntity that = (StationEntity) o;
        return Objects.equals(idStation, that.idStation) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(numero, that.numero) &&
                Objects.equals(ville, that.ville) &&
                Objects.equals(codePostal, that.codePostal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStation, latitude, longitude, adresse, numero, ville, codePostal);
    }

    @OneToMany(mappedBy = "stationByStation")
    public Collection<BorneEntity> getBornesByIdStation() {
        return bornesByIdStation;
    }

    public void setBornesByIdStation(Collection<BorneEntity> bornesByIdStation) {
        this.bornesByIdStation = bornesByIdStation;
    }
}
