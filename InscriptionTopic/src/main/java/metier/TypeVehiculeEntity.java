package metier;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "type_vehicule", schema = "autolib", catalog = "")
public class TypeVehiculeEntity {
    private Integer idTypeVehicule;
    private String categorie;
    private String typeVehicule;
    private Collection<VehiculeEntity> vehiculesByIdTypeVehicule;

    @Id
    @Column(name = "idType_vehicule", nullable = false)
    public Integer getIdTypeVehicule() {
        return idTypeVehicule;
    }

    public void setIdTypeVehicule(Integer idTypeVehicule) {
        this.idTypeVehicule = idTypeVehicule;
    }

    @Basic
    @Column(name = "categorie", nullable = false, length = 45)
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Basic
    @Column(name = "type_vehicule", nullable = false, length = 45)
    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeVehiculeEntity that = (TypeVehiculeEntity) o;
        return Objects.equals(idTypeVehicule, that.idTypeVehicule) &&
                Objects.equals(categorie, that.categorie) &&
                Objects.equals(typeVehicule, that.typeVehicule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeVehicule, categorie, typeVehicule);
    }

    @OneToMany(mappedBy = "typeVehiculeByTypeVehicule")
    public Collection<VehiculeEntity> getVehiculesByIdTypeVehicule() {
        return vehiculesByIdTypeVehicule;
    }

    public void setVehiculesByIdTypeVehicule(Collection<VehiculeEntity> vehiculesByIdTypeVehicule) {
        this.vehiculesByIdTypeVehicule = vehiculesByIdTypeVehicule;
    }
}
