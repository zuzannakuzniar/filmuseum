package filmuseum.DAO;

import org.springframework.data.repository.CrudRepository;
import filmuseum.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
