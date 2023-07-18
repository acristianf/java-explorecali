package com.cristian.ec.explorecali.services;

import com.cristian.ec.explorecali.domain.TourRating;
import com.cristian.ec.explorecali.repo.TourRatingRepository;
import com.cristian.ec.explorecali.repo.TourRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TourRatingService {
    TourRatingRepository tourRatingRepository;
    TourRepository tourRepository;

    TourRatingService(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRepository = tourRepository;
    }

    public void createManyTourRatings(Integer tourId, Integer score, Integer[] customers) {
        tourRepository.findById(tourId).ifPresent(tour -> {
            for (Integer customer : customers) {
                tourRatingRepository.save(new TourRating(tour, customer, score));
            }
        });
    }

}
