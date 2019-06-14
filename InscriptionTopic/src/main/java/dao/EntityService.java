package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Valentin on 06/04/2016.
 */
public abstract class EntityService {

    protected EntityManager entityManager;
    protected EntityManagerFactory emf;

    public EntityTransaction startTransaction() throws Exception
    {
        emf = Persistence.createEntityManagerFactory("PInscription");
        entityManager = emf.createEntityManager();

        return entityManager.getTransaction();
    }

}