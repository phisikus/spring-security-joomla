package pl.weimaraner.klub.baza.joomla.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import pl.weimaraner.klub.baza.joomla.auth.cookie.SessionCookieMatcher;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

/**
 * This class is a core part in authentication process. It replaces commonly used
 * UsernamePasswordAuthenticationFilter that checks user and password provided through HTML form. This implementation
 * uses Joomla's session cookies to distinguish if client should be authorized.
 *
 * @author phisikus
 * @author blacksmithy
 * @see <a href="https://github.com/spring-projects/spring-security/blob/master/web/src/main/java/org/springframework
 * /security/web/authentication/UsernamePasswordAuthenticationFilter.java">UsernamePasswordAuthenticationFilter</a>
 * @see <a href="http://docs.spring.io/spring-security/site/docs/3.0.x/reference/core-web-filters.html">http://docs
 * .spring.io/spring-security/site/docs/3.0.x/reference/core-web-filters.html</a>
 */
@Named
public class JoomlaAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger log = LoggerFactory.getLogger(JoomlaAuthenticationProcessingFilter.class);

    @Inject
    private SessionCookieMatcher sessionCookieMatcher;

    /**
     * This field contains URL to redirect in case of failed authorization.
     */
    private String redirectIfFailedUrl = "failed";

    public JoomlaAuthenticationProcessingFilter() {
        super("/authorize_joomla");
    }

    @Inject
    public void setRedirectIfFailedUrl(String redirectIfFailedUrl) {
        this.redirectIfFailedUrl = redirectIfFailedUrl;
    }

    @Inject
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    @Transactional
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse
            response) throws AuthenticationException, IOException, ServletException {
        String userName = sessionCookieMatcher.getUserNameForCookie(request.getCookies());

        if (userName != null) {
            log.info("Matching cookie value was found in Joomla session table for user: " + userName);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, "");
            authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
            return this.getAuthenticationManager().authenticate(authRequest);
        }
        log.info("There were no cookies that would match Joomla session table.");

        response.sendRedirect(redirectIfFailedUrl);
        return null;
    }
}
