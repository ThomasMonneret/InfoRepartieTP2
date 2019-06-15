package metier;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class UtiliseEntityPK implements Serializable {
    private Integer vehicule;
    private Integer client;
    private Timestamp date;

    @Column(name = "Vehicule", nullable = false)
    @Id
    public Integer getVehicule() {
        return vehicule;
    }

    public void setVehicule(Integer vehicule) {
        this.vehicule = vehicule;
    }

    @Column(name = "Client", nullable = false)
    @Id
    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    @Column(name = "date", nullable = false)
    @Id
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtiliseEntityPK that = (UtiliseEntityPK) o;
        return Objects.equals(vehicule, that.vehicule) &&
                Objects.equals(client, that.client) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicule, client, date);
    }
}
