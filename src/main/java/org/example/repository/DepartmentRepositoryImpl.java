package org.example.repository;

import org.example.entity.Department;
import org.example.entity.Duty;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class DepartmentRepositoryImpl implements DepartmentRepository{
    private final EntityManager entityManager;

    public DepartmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Department entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(entity);

        transaction.commit();
    }

    public List<Department> findAll() {
        TypedQuery<Department> query = entityManager.createQuery("from Department department", Department.class);
        return query.getResultList();
    }

    public List<Department> findEntityByName(String name) {
        TypedQuery<Department> query =
                entityManager.createQuery("from Department department where department.name = ?1", Department.class);
        return query.setParameter(1,name).getResultList();
    }
}
