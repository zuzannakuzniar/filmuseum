package filmuseum.controller;

import org.springframework.web.bind.annotation.*;
import filmuseum.service.ReviewService;
import filmuseum.entity.Review;

import java.util.List;

@RestController
@RequestMapping("/reviews/")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

//    @GetMapping("/{id}/")
//    public List<Review> getListOfReviewsForAFilm(Long id) {
//
//        return reviewService.getListOfReviewsForAFilm(id);
//    }

    @GetMapping("/all/")
    public List<Review> getAllReviews(){
        return reviewService.getAllreviews();
    }

    @DeleteMapping("/{id}/")
    public void deleteReview(@PathVariable("id") Long id, @RequestBody Review review) {
        reviewService.deleteReviewFromAFilm(id, review);
    }

    @PostMapping("/addtofilm/")
    public Review addReviewToAFilm(@RequestBody Review review) {
        return reviewService.addReviewToAFilm(review);
    }

    @PostMapping()
    public Review addReview(@RequestBody Review review){
        return reviewService.addReview(review);
    }
}
