package metier;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "vehicule", schema = "autolib", catalog = "")
public class VehiculeEntity {
    private Integer idVehicule;
    private Integer rfid;
    private Integer etatBatterie;
    private String disponibilite;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer typeVehicule;
    private Collection<BorneEntity> bornesByIdVehicule;
    private Collection<ReservationEntity> reservationsByIdVehicule;
    private Collection<UtiliseEntity> utilisesByIdVehicule;
    private TypeVehiculeEntity typeVehiculeByTypeVehicule;

    @Id
    @Column(name = "idVehicule", nullable = false)
    public Integer getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Integer idVehicule) {
        this.idVehicule = idVehicule;
    }

    @Basic
    @Column(name = "RFID", nullable = false)
    public Integer getRfid() {
        return rfid;
    }

    public void setRfid(Integer rfid) {
        this.rfid = rfid;
    }

    @Basic
    @Column(name = "etatBatterie", nullable = true)
    public Integer getEtatBatterie() {
        return etatBatterie;
    }

    public void setEtatBatterie(Integer etatBatterie) {
        this.etatBatterie = etatBatterie;
    }

    @Basic
    @Column(name = "Disponibilite", nullable = false, length = 45)
    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Basic
    @Column(name = "latitude", nullable = true, precision = 6)
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude", nullable = true, precision = 6)
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "type_vehicule", nullable = false)
    public Integer getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(Integer typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiculeEntity that = (VehiculeEntity) o;
        return Objects.equals(idVehicule, that.idVehicule) &&
                Objects.equals(rfid, that.rfid) &&
                Objects.equals(etatBatterie, that.etatBatterie) &&
                Objects.equals(disponibilite, that.disponibilite) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(typeVehicule, that.typeVehicule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVehicule, rfid, etatBatterie, disponibilite, latitude, longitude, typeVehicule);
    }

    @OneToMany(mappedBy = "vehiculeByIdVehicule")
    public Collection<BorneEntity> getBornesByIdVehicule() {
        return bornesByIdVehicule;
    }

    public void setBornesByIdVehicule(Collection<BorneEntity> bornesByIdVehicule) {
        this.bornesByIdVehicule = bornesByIdVehicule;
    }

    @OneToMany(mappedBy = "vehiculeByVehicule")
    public Collection<ReservationEntity> getReservationsByIdVehicule() {
        return reservationsByIdVehicule;
    }

    public void setReservationsByIdVehicule(Collection<ReservationEntity> reservationsByIdVehicule) {
        this.reservationsByIdVehicule = reservationsByIdVehicule;
    }

    @OneToMany(mappedBy = "vehiculeByVehicule")
    public Collection<UtiliseEntity> getUtilisesByIdVehicule() {
        return utilisesByIdVehicule;
    }

    public void setUtilisesByIdVehicule(Collection<UtiliseEntity> utilisesByIdVehicule) {
        this.utilisesByIdVehicule = utilisesByIdVehicule;
    }

    @ManyToOne
    @JoinColumn(name = "type_vehicule", referencedColumnName = "idType_vehicule", nullable = false, updatable = false, insertable = false)
    public TypeVehiculeEntity getTypeVehiculeByTypeVehicule() {
        return typeVehiculeByTypeVehicule;
    }

    public void setTypeVehiculeByTypeVehicule(TypeVehiculeEntity typeVehiculeByTypeVehicule) {
        this.typeVehiculeByTypeVehicule = typeVehiculeByTypeVehicule;
    }
}
