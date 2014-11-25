package pl.weimaraner.klub.baza.joomla.auth.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.weimaraner.klub.baza.joomla.dao.UserDAO;
import pl.weimaraner.klub.baza.joomla.model.User;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService} that uses Joomla's
 * users database.
 */
@Named
public class JoomlaUserDetailsService implements UserDetailsService {

    @Inject
    private UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getByUserName(s);
        if (user != null) {
            return new JoomlaUserDetails(user);
        }
        return null;
    }
}
