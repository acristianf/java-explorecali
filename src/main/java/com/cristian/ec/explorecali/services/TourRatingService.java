package com.cristian.ec.explorecali.services;

import com.cristian.ec.explorecali.domain.TourRating;
import com.cristian.ec.explorecali.repo.TourRatingRepository;
import com.cristian.ec.explorecali.repo.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class TourRatingService {

    private final Logger LOGGER = LoggerFactory.getLogger(TourRatingService.class);
    private TourRatingRepository tourRatingRepository;
    private TourRepository tourRepository;

    TourRatingService(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRepository = tourRepository;
    }

    public void rateMany(Integer tourId, Integer score, Integer[] customers) {
        LOGGER.info("Rate tour {} by customers {}", tourId, Arrays.asList(customers));
        tourRepository.findById(tourId).ifPresent(tour -> {
            for (Integer customer : customers) {
                LOGGER.debug("Attempt to create rating by customer {}", customer);
                tourRatingRepository.save(new TourRating(tour, customer, score));
            }
        });
    }

}
