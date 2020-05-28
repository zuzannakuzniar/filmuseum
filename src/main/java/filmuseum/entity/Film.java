package filmuseum.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int year;
    private String category;

//    @JsonIgnore
//    @OneToMany( mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
//   @ElementCollection(targetClass=Review.class)
    @OneToMany(targetEntity = Review.class, mappedBy = "film")
    private List<Review> reviews;

    @ManyToMany(targetEntity = User.class)
    private List<User> likingUsers;



}
