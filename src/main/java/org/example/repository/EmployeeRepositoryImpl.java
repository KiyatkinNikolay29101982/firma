package org.example.repository;

import org.example.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository{
    private final EntityManager entityManager;

    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Employee entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(entity);

        transaction.commit();

    }

    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee employee", Employee.class);
        return query.getResultList();
    }

    public List<Employee> findEntityByName(String name) {
        TypedQuery<Employee> query =
                entityManager.createQuery("from Employee employee where employee.lastName = ?1", Employee.class);
       return query.setParameter(1,name).getResultList();

    }
}
