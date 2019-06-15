package metier;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "autolib", catalog = "")
public class ClientEntity {
    private Integer idClient;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private Collection<ReservationEntity> reservationsByIdClient;
    private Collection<UtilisateurEntity> utilisateursByIdClient;
    private Collection<UtiliseEntity> utilisesByIdClient;

    @Id
    @Column(name = "idClient", nullable = false)
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = 45)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "prenom", nullable = false, length = 45)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "date_naissance", nullable = true)
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(idClient, that.idClient) &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(prenom, that.prenom) &&
                Objects.equals(dateNaissance, that.dateNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, nom, prenom, dateNaissance);
    }

    @OneToMany(mappedBy = "clientByClient")
    public Collection<ReservationEntity> getReservationsByIdClient() {
        return reservationsByIdClient;
    }

    public void setReservationsByIdClient(Collection<ReservationEntity> reservationsByIdClient) {
        this.reservationsByIdClient = reservationsByIdClient;
    }

    @OneToMany(mappedBy = "clientByIdClient")
    public Collection<UtilisateurEntity> getUtilisateursByIdClient() {
        return utilisateursByIdClient;
    }

    public void setUtilisateursByIdClient(Collection<UtilisateurEntity> utilisateursByIdClient) {
        this.utilisateursByIdClient = utilisateursByIdClient;
    }

    @OneToMany(mappedBy = "clientByClient")
    public Collection<UtiliseEntity> getUtilisesByIdClient() {
        return utilisesByIdClient;
    }

    public void setUtilisesByIdClient(Collection<UtiliseEntity> utilisesByIdClient) {
        this.utilisesByIdClient = utilisesByIdClient;
    }
}
