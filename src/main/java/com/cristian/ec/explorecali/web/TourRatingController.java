package com.cristian.ec.explorecali.web;

import com.cristian.ec.explorecali.domain.Tour;
import com.cristian.ec.explorecali.domain.TourRating;
import com.cristian.ec.explorecali.domain.TourRatingPk;
import com.cristian.ec.explorecali.repo.TourRatingRepository;
import com.cristian.ec.explorecali.repo.TourRepository;
import com.cristian.ec.explorecali.service.TourRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TourRatingController.class);
    private TourRatingRepository tourRatingRepository;
    private TourRepository tourRepository;
    private TourRatingService tourRatingService;

    @Autowired
    public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository tourRepository, TourRatingService tourRatingService) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRepository = tourRepository;
        this.tourRatingService = tourRatingService;
    }

    protected TourRatingController() {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody @Validated RatingDto ratingDto) {
        LOGGER.info("POST /tours/{}/ratings", tourId);
        Tour tour = verifyTour(tourId);
        tourRatingRepository
                .save(
                        new TourRating(
                                new TourRatingPk(tour, ratingDto.getCustomerId()), ratingDto.getScore(), ratingDto.getComment()
                        )
                );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<RatingDto> getTourRatings(@PathVariable(value = "tourId") int tourId, Pageable pageable) {
        LOGGER.info("GET /tours/{}/ratings", tourId);
        verifyTour(tourId);
        Page<TourRating> ratings = tourRatingRepository.findByPkTourId(tourId, pageable);
        List<RatingDto> ratingDtos = ratings.stream().map(rating -> new RatingDto(rating.getRating(), rating.getComment(), rating.getPk().getCustomerId())).toList();
        return new PageImpl<>(ratingDtos, pageable, ratings.getTotalElements());
    }

    @GetMapping(path = "/average")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Double> getAverage(@PathVariable(value = "tourId") int tourId) {
        LOGGER.info("GET /tours/{}/ratings/average", tourId);
        verifyTour(tourId);
        return Map.of(
                "average",
                tourRatingRepository
                        .findByPkTourId(tourId)
                        .stream()
                        .mapToInt(TourRating::getRating)
                        .average()
                        .orElseThrow(() -> new NoSuchElementException("Tour has no ratings")));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RatingDto updateRating(@PathVariable(value = "tourId") int tourId, @RequestBody @Validated RatingDto ratingDto) {
        LOGGER.info("PUT /tours/{}/ratings", tourId);
        TourRating tourRating = verifyTourRating(tourId, ratingDto.getCustomerId());
        tourRating.setRating(ratingDto.getScore());
        tourRating.setComment(ratingDto.getComment());
        return new RatingDto(tourRatingRepository.save(tourRating));
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public RatingDto updateRatingWithPatch(@PathVariable(value = "tourId") int tourId, @RequestBody @Validated RatingDto ratingDto) {
        LOGGER.info("PATCH /tours/{}/ratings", tourId);
        TourRating tourRating = verifyTourRating(tourId, ratingDto.getCustomerId());
        if (ratingDto.getScore() != null) {
            tourRating.setRating(ratingDto.getScore());
        }
        if (ratingDto.getComment() != null) {
            tourRating.setComment(ratingDto.getComment());
        }
        return new RatingDto(tourRatingRepository.save(tourRating));
    }

    @DeleteMapping(path = "/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRating(@PathVariable(value = "tourId") int tourId, @PathVariable(value = "customerId") int customerId) {
        LOGGER.info("DELETE /tours/{}/ratings/{}", tourId, customerId);
        TourRating tourRating = verifyTourRating(tourId, customerId);
        tourRatingRepository.delete(tourRating);
    }

    @PostMapping("/{score}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createManyTourRatings(@PathVariable(value = "tourId") int tourId,
                                      @PathVariable(value = "score") int score,
                                      @RequestParam("customers") Integer[] customers) {
        LOGGER.info("POST /tours/{}/ratings/{}", tourId, customers);
        tourRatingService.rateMany(tourId, score, customers);
    }

    private Tour verifyTour(int tourId) throws NoSuchElementException {
        return tourRepository
                .findById(tourId)
                .orElseThrow(() -> new NoSuchElementException("Tour doesn't exist " + tourId));
    }

    private TourRating verifyTourRating(int tourId, int customerId) throws NoSuchElementException {
        return tourRatingRepository
                .findByPkTourIdAndPkCustomerId(tourId, customerId)
                .orElseThrow(() -> new NoSuchElementException("Tour-Rating pair for request(" + tourId + " for customer " + customerId + ")"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        LOGGER.error("Unable to complete transaction", ex);
        return ex.getMessage();
    }
}
