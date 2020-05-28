package filmuseum.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import filmuseum.entity.Review;
import filmuseum.service.FilmService;
import filmuseum.entity.Film;

import java.util.List;

@RestController
@RequestMapping("/films/")
public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/all/")
    public List<Film> getFilms() {
        return filmService.getFilms();
    }

    @RequestMapping()
    public ModelAndView getFPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("films");
        return mv;
    }

    @GetMapping("/details/{id}")
    public Film getFilmDetails(@PathVariable("id") Long id) {
        return filmService.getFilmDetails(id);
    }

    @GetMapping("/{id}/")
    public List<Review> getListOfReviewsForAFilm(@PathVariable Long id) {

        return filmService.getListOfReviews(id);
    }


    @PostMapping("/{id}/")
    public Film saveFilm(@RequestBody Film film) {
        return filmService.saveFilm(film);
    }

    @PutMapping("/{id}/")
    public Film updateFilm(@PathVariable("id") Long id, @RequestBody Film film) {
        return filmService.update(id, film);
    }

    @DeleteMapping("/{id}/")
    public void deleteFilm(@PathVariable("id") Long id) {
        filmService.deleteFilmById(id);

    }

    @GetMapping("/title/{title}/")
    public Film getFilmByTitle(@RequestParam("title") String title){return filmService.getFilmByTitle(title);}


}
