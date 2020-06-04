package filmuseum.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullname;

    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

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
    }


}
