package dao;

import meserreurs.MonException;
import javax.persistence.*;
import metier.InscriptionEntity;

public class EnregistreInscription  {
    private EntityManagerFactory factory;
    private  EntityManager entityManager;

    public   void  insertionInscription(InscriptionEntity uneI) throws Exception, MonException {

        try {

                 // On instancie l'entity Manager
                factory = Persistence.createEntityManagerFactory("PInscription");

                entityManager  = factory.createEntityManager();
               System.out.println("Création  EM !");
               // On démarre une transaction
                entityManager.getTransaction().begin();
                entityManager.persist(uneI);
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

