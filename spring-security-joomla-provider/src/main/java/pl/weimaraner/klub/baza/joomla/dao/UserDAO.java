package pl.weimaraner.klub.baza.joomla.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pl.weimaraner.klub.baza.joomla.model.User;

import javax.inject.Named;

/**
 * Data Access Object for {@link pl.weimaraner.klub.baza.joomla.model.User} entities. It allows basic CRUD-style
 * operations.
 *
 */
@Named
public class UserDAO extends AbstractDAO<User, Long> {


    /**
     * Default constructor
     */
    public UserDAO() {
        super(User.class);
    }


    /**
     * Retreive User object using userName field value
     *
     * @param userName Login name of user
     * @return User entity or null
     */
    public User getByUserName(String userName) {
        Criteria criteria = this.getSession().createCriteria(User.class, "u");
        criteria.add(Restrictions.eq("userName", userName));
        return (User) criteria.uniqueResult();
    }
}
