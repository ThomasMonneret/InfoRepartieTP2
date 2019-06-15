package metier;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ReservationEntityPK implements Serializable {
    private Integer vehicule;
    private Integer client;
    private Timestamp dateReservation;

    @Column(name = "vehicule", nullable = false)
    @Id
    public Integer getVehicule() {
        return vehicule;
    }

    public void setVehicule(Integer vehicule) {
        this.vehicule = vehicule;
    }

    @Column(name = "client", nullable = false)
    @Id
    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    @Column(name = "date_reservation", nullable = false)
    @Id
    public Timestamp getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Timestamp dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationEntityPK that = (ReservationEntityPK) o;
        return Objects.equals(vehicule, that.vehicule) &&
                Objects.equals(client, that.client) &&
                Objects.equals(dateReservation, that.dateReservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicule, client, dateReservation);
    }
}
