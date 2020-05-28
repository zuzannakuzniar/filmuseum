package filmuseum.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int starRating;
    private String description;

    @ManyToOne
    private User author;
    private boolean liked = false;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "film_id")
    @ManyToOne
    private Film film;

    public boolean isLiked() {
        return liked;
    }
}
