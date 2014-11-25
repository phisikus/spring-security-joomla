package pl.weimaraner.klub.baza.joomla.dao;

import pl.weimaraner.klub.baza.joomla.model.Group;

import javax.inject.Named;

/**
 * Data Access Object for {@link pl.weimaraner.klub.baza.joomla.model.Group} entities. It allows basic CRUD-style
 * operations.
 *
 */
@Named
public class GroupDAO extends AbstractDAO<Group, Long> {


    /**
     * Default constructor
     */
    public GroupDAO() {
        super(Group.class);
    }
}
