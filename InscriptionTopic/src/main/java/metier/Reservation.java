package metier;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reservation implements Serializable {

    private Integer vehicule;
    private Integer client;
    private Timestamp dateReservation;
    private Timestamp dateEcheance;

    public Integer getVehicule() {
        return vehicule;
    }

    public void setVehicule(Integer vehicule) {
        this.vehicule = vehicule;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Timestamp getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Timestamp dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Timestamp getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Timestamp dateEcheance) {
        this.dateEcheance = dateEcheance;
    }
}
