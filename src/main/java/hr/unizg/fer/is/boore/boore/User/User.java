package hr.unizg.fer.is.boore.boore.User;

import hr.unizg.fer.is.boore.boore.Auth.config.Roles;
import hr.unizg.fer.is.boore.boore.Person.Person;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class User implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public static User build(Person person){
        return new User(person.getId(), person.getUsername(), person.getPassword(), person.getFirstName(), person.getLastName());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Roles.ROLE_USER.name()));

        return authorities;
    }

    public Integer getId(){return id;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
