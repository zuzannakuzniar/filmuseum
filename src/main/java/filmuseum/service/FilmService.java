package filmuseum.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import filmuseum.dao.repository.FilmRepository;
import filmuseum.dao.entity.Film;
import filmuseum.dao.entity.Review;

import java.util.List;
import java.util.Optional;

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
        resultFilm.setCategory(film.getCategory());
        resultFilm.setTitle(film.getTitle());
        resultFilm.setYear(film.getYear());
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


    public List<Review> getListOfReviews(Long id){
        return filmRepository.findById(id).get().getReviews();
    }

}
