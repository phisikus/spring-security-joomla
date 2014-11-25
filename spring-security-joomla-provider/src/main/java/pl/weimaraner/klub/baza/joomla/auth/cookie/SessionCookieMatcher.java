package pl.weimaraner.klub.baza.joomla.auth.cookie;

import pl.weimaraner.klub.baza.joomla.dao.SessionDAO;
import pl.weimaraner.klub.baza.joomla.model.Session;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.transaction.Transactional;

/**
 * The purpose of this class is to find user that has active Joomla session and return userName if that
 * user is found.
 *
 * @see <a href="http://forum.joomla.org/viewtopic.php?t=320169">http://forum.joomla.org/viewtopic.php?t=320169</a>
 * @see <a href="http://docs.joomla.org/Tables/session">http://docs.joomla.org/Tables/session</a>
 */
@Named
public class SessionCookieMatcher {


    @Inject
    private SessionDAO sessionDAO;


    /**
     * This fuction returns username of user that has active session in Joomla based on provided cookies.
     *
     * @param cookies objects to analyze
     * @return username (if found) or null
     */
    @Transactional
    public String getUserNameForCookie(final Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // We don't need to analyze our own session cookies...
                if (!cookie.getName().equals("JSESSIONID")) {
                    final String cookieValue = cookie.getValue();
                    if (cookieValue != null) {
                        Session session = sessionDAO.get(cookieValue);
                        if (verifySession(session)) {
                            // We have active session for existing user
                            // update timestamp - keep session alive
                            session.setTimestamp(System.currentTimeMillis() / 1000L);
                            sessionDAO.update(session);
                            return session.getUserName();
                        }

                    }
                }
            }
        }
        return null;
    }

    /**
     * This function decides if provided Session object can be used to authenticate user.
     *
     * @param session object to analyze
     * @return true if user can be authorized, false otherwise
     * @see <a href="https://github.com/joomla-framework/session/blob/master/Session.php">_validate() function in
     * Joomla session.php implementation</a>
     */

    private boolean verifySession(Session session) {
        if (session == null) {
            return false;
        }

        // Anonymous users also get session cookies...
        if (session.getIsGuest()) {
            return false;
        }

        if (session.getUserId() == null || session.getUserId().equals(0)) {
            return false;
        }

        if (session.getUserName() == null || session.getUserName().isEmpty()) {
            return false;
        }

        long now = System.currentTimeMillis() / 1000L;

        /**
         * Session expired (Joomla would garbate-collect it).
         *
         * @see <a href="https://github.com/joomla-framework/session/blob/master/Storage/Database.php">https://github
         * .com/joomla-framework/session/blob/master/Storage/Database.php</a>
         */
        if (session.getTimestamp() < now - 1400) {
            return false;
        }


        return true;
    }

}
