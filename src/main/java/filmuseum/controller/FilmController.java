package filmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import filmuseum.entity.Review;
import filmuseum.service.FilmService;
import filmuseum.entity.Film;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/films/")
public class FilmController {

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/all/")
    public ModelAndView getFilms() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("allfilms");
        List<Film> films = filmService.getFilms();
        mv.addObject("films", films);
        return  mv;

    }

    @GetMapping("/categories/")
    public ModelAndView getCategories(){
        ModelAndView mv = new ModelAndView("allfilms");
        Set<String> categories = filmService.findCategories();
        mv.addObject("categories", categories);
        return mv;
    }

    @GetMapping("/{cat}/")
    public ModelAndView getFilmsByCategories(@RequestParam("cat") String category){
        ModelAndView mv = new ModelAndView("allfilms");
        List<Film> filmsByCategory = filmService.findFilmsByCategory(category);
        mv.addObject("filmsByCat", filmsByCategory);
        return mv;
    }

    @GetMapping("/films/")
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
