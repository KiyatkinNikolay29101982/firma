package org.example.repository;

import java.util.List;

public interface CRUDRepository <T>{
     void save(T entity);

     List<T> findAll();

     List<T> findEntityByName(String name);

}
