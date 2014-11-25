package pl.weimaraner.klub.baza.joomla.auth.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.weimaraner.klub.baza.joomla.model.Group;
import pl.weimaraner.klub.baza.joomla.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * {@link org.springframework.security.core.userdetails.UserDetails} implementation that uses {@link pl.weimaraner
 * .klub.baza.joomla.model.User} as datasource.
 */
public class JoomlaUserDetails implements UserDetails {

    /**
     * Data source.
     */
    private User user;

    public JoomlaUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<JoomlaUserGrantedAuthority> authorities = new ArrayList<>();
        for (Group group : user.getUserGroups()) {
            authorities.add(new JoomlaUserGrantedAuthority(group.getTitle()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        /**
         * At this point filter should find userName and get JoomlaUserDetails object with matching username.
         * Due to earlier checked constraints there is no point in using real passwords.
         *
         * @see pl.weimaraner.klub.baza.joomla.auth.JoomlaAuthenticationProcessingFilter
         */
        return "";
        //return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getBlock();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.getRequireReset();
    }

    @Override
    public boolean isEnabled() {
        /**
         * @see <a href="https://github.com/joomla/joomla-cms/blob/staging/libraries/joomla/user/helper
         * .php#L234">https://github.com/joomla/joomla-cms/blob/staging/libraries/joomla/user/helper.php#L234</a>
         */
        return isAccountNonLocked();
    }
}
