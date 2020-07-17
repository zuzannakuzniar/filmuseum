package filmuseum.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import filmuseum.DAO.FilmRepository;
import filmuseum.entity.Film;
import filmuseum.entity.Review;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FilmService {

    FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    public List<Film> getFilms(){
        return (List<Film>) filmRepository.findAll();
    }

    public Film getFilmByTitle(String title) { return filmRepository.findFilmByTitle(title);}

    public Film saveFilm(Film film){
        return filmRepository.save(addFilm(film));
    }

    public Film addFilm(Film film){
        Film resultFilm = new Film();
        resultFilm.setId(film.getId());
        resultFilm.setYear(film.getYear());
        resultFilm.setTitle(film.getTitle());
        resultFilm.setCategory(film.getCategory());
        return resultFilm;
    }

    public Film getFilmDetails(Long id){
        return filmRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Film.class.getName()));
    }

    public Film update(Long id, Film film) {

        Optional<Film> filmUpd = filmRepository.findById(id);

        if (filmUpd.isPresent()) {
            Film updatedFilm = filmUpd.get();

            updatedFilm.setTitle(filmUpd.get().getTitle());
            updatedFilm.setCategory(filmUpd.get().getCategory());
            updatedFilm.setYear(filmUpd.get().getYear());

            return filmRepository.save(updatedFilm);
        } else {
            return filmRepository.save(film);
        }
    }

    public Film deleteFilmById(Long id){
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Film.class.getName()));
        filmRepository.delete(film);
        return film;
    }

    public List<Film> findFilmsByCategory(String category){
        if(category != null) {
            return filmRepository.findFilmsByCategory(category);
        } return null;
    }

    public Set<String> findCategories(){
        Set<String> categories = new HashSet<>();
        List<Film> films = (List<Film>) filmRepository.findAll();
        for (Film f: films
             ) {
            categories.add(f.getCategory());
        }
        return categories;
    }

    public List<Review> getListOfReviews(Long id){
        return filmRepository.findById(id).get().getReviews();
    }

}
