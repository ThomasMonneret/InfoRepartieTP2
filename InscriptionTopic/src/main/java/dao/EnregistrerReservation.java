package dao;

import meserreurs.MonException;
import metier.ReservationEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

public class EnregistrerReservation {
    private EntityManagerFactory factory;
    private EntityManager entityManager;

    public void insertionReservation(ReservationEntity uneR){
        try {
            // On instancie l'entity Manager
            factory = Persistence.createEntityManagerFactory("PInscription");

            entityManager  = factory.createEntityManager();
            System.out.println("Création  EM !");
            // On démarre une transaction
            entityManager.getTransaction().begin();
            entityManager.persist(uneR);
            entityManager.flush();
            // on valide la transacition
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (EntityNotFoundException h) {
            new MonException("Erreur d'insertion", h.getMessage());
        } catch (Exception e) {
            System.out.print("Erreur : "); e.printStackTrace(System.out);
            new MonException("Erreur d'insertion", e.getMessage());
        }
    }
}
