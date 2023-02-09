package person.justin.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import person.justin.blog.enums.AccountStateEm;
import person.justin.blog.pojo.Role;
import person.justin.blog.pojo.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>登录用户实体
 *
 * @author gym on 2023-01-07 22:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = -3459536902335106672L;

    /**
     * 用户
     */
    private User user;
    /**
     * 角色集
     */
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        assertRoles(this.roles);
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        assertUser(this.user);
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        assertUser(this.user);
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        assertUser(this.user);
        return !AccountStateEm.isAccountExpired(this.user.getAccountState());
    }

    @Override
    public boolean isAccountNonLocked() {
        assertUser(this.user);
        return !AccountStateEm.isAccountLocked(this.user.getAccountState());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        assertUser(this.user);
        return !AccountStateEm.isCredentialsExpired(this.user.getAccountState());
    }

    @Override
    public boolean isEnabled() {
        assertUser(this.user);
        return AccountStateEm.isEnabled(this.user.getAccountState());
    }

    protected void assertUser(User user) {
        Assert.notNull(user, "登录用户user参数内容为空");
    }

    protected void assertRoles(List<Role> roles) {
        Assert.notEmpty(roles, "登录用户roles角色集为空");
    }
}


