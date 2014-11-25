package pl.weimaraner.klub.baza.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.weimaraner.klub.baza.joomla.dao.GroupDAO;
import pl.weimaraner.klub.baza.joomla.dao.SessionDAO;
import pl.weimaraner.klub.baza.joomla.dao.UserDAO;
import pl.weimaraner.klub.baza.joomla.model.Group;
import pl.weimaraner.klub.baza.joomla.model.Session;
import pl.weimaraner.klub.baza.joomla.model.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("rest")
@Transactional
public class TestService {

    private static final Logger log = LoggerFactory.getLogger(TestService.class);


    @Inject
    private SessionDAO sessionDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private GroupDAO groupDAO;

    @ResponseBody
    @Secured("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, value = "test/username")
    public String getUserName(Principal principal) {
        return principal.getName();
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "session")
    public List<Session> test_session() {
        return sessionDAO.getAll();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "user")
    public List<User> test_user() {
        return userDAO.getAll();
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "user/{id}")
    public User test_user_name(@PathVariable("id") String name) {
        return userDAO.getByUserName(name);
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "group")
    public List<Group> test_group() {
        return groupDAO.getAll();
    }


}
