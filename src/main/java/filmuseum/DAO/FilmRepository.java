package filmuseum.DAO;

import org.springframework.data.repository.CrudRepository;
import filmuseum.entity.Film;

public interface FilmRepository extends CrudRepository<Film, Long> {
    public Film findFilmByTitle(String title);

}
