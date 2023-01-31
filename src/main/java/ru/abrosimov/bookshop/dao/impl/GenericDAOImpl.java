package ru.abrosimov.bookshop.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.abrosimov.bookshop.ReflectionMagic;
import ru.abrosimov.bookshop.dao.GenericDAO;
import ru.abrosimov.bookshop.models.GenericEntity;

import java.util.Collection;
import java.util.Optional;

public class GenericDAOImpl<T extends GenericEntity> implements GenericDAO<T> {

    protected SessionFactory sessionFactory;
    private final Class<T> typeT = ReflectionMagic.getGeneric(getClass(), 0);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveAll(Collection<T> entities) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            entities.forEach(session::save);
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<T> findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return applyInitialize(Optional.ofNullable(session.get(typeT, id)));
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean existsById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createCriteria(typeT)
                    .add(Restrictions.idEq(id))
                    .setProjection(Projections.id())
                    .uniqueResult() != null;
        }
    }

    @Override
    public Collection<T> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder().createQuery(typeT);
            criteriaQuery.from(typeT);
            return applyInitialize(session.createQuery(criteriaQuery).getResultList());
        }
    }

    @Override
    public long count() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<T> root = criteriaQuery.from(typeT);
            criteriaQuery.select(builder.count(root));
            return session.createQuery(criteriaQuery).getResultList().get(0);
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        }
    }

    protected final Collection<T> applyInitialize(Collection<T> t) {
        t.forEach(this::initialize);
        return t;
    }

    protected final Optional<T> applyInitialize(Optional<T> t) {
        t.ifPresent(this::initialize);
        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(T entity) {
        try {
            ReflectionMagic.getLazyFields(typeT).forEach(field -> ReflectionMagic.applyToField(entity, field,
                    aField -> Hibernate.initialize(aField.get(entity))));
        } catch (HibernateException ignored) {
            try (Session session = sessionFactory.openSession()) {
                T reattachedEntity = (T) session.merge(entity);
                ReflectionMagic.getLazyFields(typeT).forEach(field -> ReflectionMagic.applyToField(reattachedEntity, field,
                        aField -> {
                            Hibernate.initialize(aField.get(reattachedEntity));
                            aField.set(entity, aField.get(reattachedEntity));
                        }));
            }
        }
    }
}
