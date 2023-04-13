package org.example.repository;

import org.example.entity.Duty;
import org.example.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class DutyRepositoryImpl implements DutyRepository{
    private final EntityManager entityManager;

    public DutyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Duty entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(entity);

        transaction.commit();
    }

    public List<Duty> findAll() {
        TypedQuery<Duty> query = entityManager.createQuery("from Duty duty", Duty.class);
        return query.getResultList();
    }

    public List<Duty> findEntityByName(String name) {
        TypedQuery<Duty> query =
                entityManager.createQuery("from Duty duty where duty.name = ?1", Duty.class);
        return query.setParameter(1,name).getResultList();
    }
}
