package pl.weimaraner.klub.baza.joomla.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Abstract Data Access Object. It implements basic CRUD-style operations.
 *
 * @param <E>  Entity type
 * @param <PK> Primary Key type
 */
@Named
public abstract class AbstractDAO<E, PK extends Serializable> {

    /**
     * Type of entity.
     */
    protected final Class<E> entityType;
    /**
     * Injected SessionFactory. Used to create criterias and sessions.
     *
     * @see <a href="http://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/SessionFactory.html">http://docs
     * .jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/SessionFactory.html</a>
     */
    @Inject
    protected SessionFactory sessionFactory;

    /**
     * Default constructor
     *
     * @param type defines type of entity
     */
    public AbstractDAO(final Class<E> type) {
        entityType = type;
    }

    /**
     * @return current session from sessionFactory
     */
    protected org.hibernate.Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Creates entity.
     *
     * @param entity entity to create
     * @return primary key of created entity
     */
    @SuppressWarnings("unchecked")
    public PK create(final E entity) {
        return (PK) this.getSession().save(entity);
    }

    /**
     * Retrieves entity.
     *
     * @param id entity primary key
     * @return entity or null
     */
    @SuppressWarnings("unchecked")
    public E get(final PK id) {
        return (E) this.getSession().get(this.entityType, id);
    }


    /**
     * Updates specified entity.
     *
     * @param entity entity to update
     */
    public void update(final E entity) {
        this.getSession().update(entity);
    }

    /**
     * Merges selected entity.
     *
     * @param entity entity to merge
     * @return merged entity
     */
    @SuppressWarnings("unchecked")
    public E merge(final E entity) {
        return (E) getSession().merge(entity);
    }


    /**
     * Deletes specified entity.
     *
     * @param entity entity to delete
     */
    public void delete(final E entity) {
        this.getSession().delete(entity);
    }

    /**
     * Retrieves all entities.
     *
     * @return list of entities
     */
    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        return getSession().createCriteria(entityType)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }


    /**
     * Returns number of all objects.
     *
     * @return count all rows
     */
    public long countAll() {
        final Number number = (Number) getSession()
                .createCriteria(this.entityType)
                .setProjection(Projections.rowCount())
                .uniqueResult();
        return number.longValue();
    }


}
