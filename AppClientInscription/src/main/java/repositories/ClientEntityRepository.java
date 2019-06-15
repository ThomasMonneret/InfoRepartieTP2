package repositories;

import metier.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, Integer> {

    /*@Query("select c from ClientEntity c where c.nom = ?1 and c.prenom = ?2")
    public List<ClientEntity> rechercheNomPrenom(String nom, String prenom);*/
}
