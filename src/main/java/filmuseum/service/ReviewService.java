package filmuseum.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import filmuseum.dao.repository.FilmRepository;
import filmuseum.dao.repository.ReviewRepository;
import filmuseum.dao.entity.Film;
import filmuseum.dao.entity.Review;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private FilmRepository filmRepository;

    public ReviewService(ReviewRepository reviewRepository, FilmRepository filmRepository) {
        this.reviewRepository = reviewRepository;
        this.filmRepository = filmRepository;
    }

    public Review addReview(Review review){
       return reviewRepository.save(saveReview(review));
    }

    public List<Review> getAllreviews() { return (List<Review>) reviewRepository.findAll();}

    public Review saveReview(Review review){
        Review resultReview = new Review();
        resultReview.setLiked(review.isLiked());
        resultReview.setId(review.getId());
        resultReview.setStarRating(review.getStarRating());
        resultReview.setDescription(review.getDescription());
        resultReview.setAuthor(review.getAuthor());

        return resultReview;
    }

    public boolean likeReview(Review review){

        if(review.isLiked()){
            review.setLiked(true);
        }
        return true;
    }

    public List<Review> getListOfReviewsForAFilm(Long id){
        return filmRepository.findById(id).get().getReviews();
    }

    public Review addReviewToAFilm(Review review){
        reviewRepository.save(review);

          Film reviewedFilm = filmRepository.findById(review.getFilm().getId())
                  .orElseThrow(() -> new ObjectNotFoundException(review.getFilm().getId(), Film.class.getName()));
          if(reviewedFilm.getReviews() == null) {
              List<Review> reviewList = new ArrayList<>();
              reviewList.add(review);
              reviewedFilm.setReviews(reviewList);
          } else {
              reviewedFilm.getReviews().add(review);
          }


        return review;
    }

    public void deleteReviewFromAFilm(Long filmId, Review review){
        List<Review> reviewList = filmRepository.findById(filmId).get().getReviews();
        reviewList.remove(review);
        reviewRepository.delete(review);

    }
}
