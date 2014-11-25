package pl.weimaraner.klub.baza.joomla.dao;

import pl.weimaraner.klub.baza.joomla.model.Session;

import javax.inject.Named;

/**
 * Data Access Object for {@link pl.weimaraner.klub.baza.joomla.model.Session} entities. It allows basic CRUD-style
 * operations.
 *
 */
@Named
public class SessionDAO extends AbstractDAO<Session, String> {


    /**
     * Default constructor
     */
    public SessionDAO() {
        super(Session.class);
    }
}
