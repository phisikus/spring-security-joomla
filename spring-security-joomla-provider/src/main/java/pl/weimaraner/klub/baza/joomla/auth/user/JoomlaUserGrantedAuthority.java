package pl.weimaraner.klub.baza.joomla.auth.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * This class implements {@link org.springframework.security.core.GrantedAuthority} to map Joomla's user groups to
 * authorities.
 */
public class JoomlaUserGrantedAuthority implements GrantedAuthority {

    private String authority;

    public JoomlaUserGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public String toString() {
        return "[" + authority + "]";
    }
}
