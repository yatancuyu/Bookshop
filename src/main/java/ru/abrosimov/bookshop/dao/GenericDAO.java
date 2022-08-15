package ru.abrosimov.bookshop.dao;

import ru.abrosimov.bookshop.models.GenericEntity;

import java.util.Collection;
import java.util.Optional;

public interface GenericDAO<T extends GenericEntity> {
    void save(T entity);

    void saveAll(Collection<T> entities);

    Optional<T> findById(int id);

    boolean existsById(int id);

    Collection<T> findAll();

    long count();

    void delete(T entity);

    void update(T entity);

    void initialize(T entity);
}
