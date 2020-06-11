package filmuseum.DAO;

import org.springframework.data.repository.CrudRepository;
import filmuseum.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
    User findByEmail(String email);
}
