package filmuseum.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import java.util.*;

@Data
@Entity
@Table(name = "users")

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullname;
    private Integer enabled = 1;



    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(targetEntity = Film.class)
    private List<Film> likedFilmes;

    @OneToMany(targetEntity = Review.class, mappedBy = "author")
    private List<Review> usersReviews;

    public User() {
    }

    public User(String username, String password, String fullname, String email) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.username = username;
        this.roles = new HashSet<>();

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
}
