package filmuseum.DAO;


import org.springframework.data.repository.CrudRepository;
import filmuseum.entity.Film;

import java.util.List;

public interface FilmRepository extends CrudRepository<Film, Long> {
     Film findFilmByTitle(String title);
     List<Film> findFilmsByCategory(String category);


}
