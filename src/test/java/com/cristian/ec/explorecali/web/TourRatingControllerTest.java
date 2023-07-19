package com.cristian.ec.explorecali.web;

import com.cristian.ec.explorecali.domain.TourRating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@Transactional
@Timeout(1000)
public class TourRatingControllerTest {
    private static final int CUSTOMER_ID = 500;
    private static final int TOUR_ID = 1;
    private static final int NOT_A_TOUR_ID = 123;
    private static final String COMMENT = "test: not updated";
    private static final String UPDATED_COMMENT = "test: updated";
    private static final int SCORE = 5;
    private static final int UPDATED_SCORE = 4;

    @Autowired
    private TourRatingController controller;

    @Test
    public void delete() {
        List<TourRating> tourRatings = controller.lookUpAll();
        controller.deleteRating(tourRatings.get(0).getPk().getTour().getId(), tourRatings.get(0).getPk().getCustomerId());
        Assertions.assertEquals(controller.lookUpAll().size(), tourRatings.size() - 1);
    }

    @Test
    public void deleteException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> controller.deleteRating(NOT_A_TOUR_ID, 1234));
    }

    @Test
    public void insertOne() {
        RatingDto ratingDto = new RatingDto(SCORE, COMMENT, CUSTOMER_ID);
        try {
            controller.deleteRating(TOUR_ID, CUSTOMER_ID);
        } catch (NoSuchElementException ignore) {
        }
        List<TourRating> tourRatings = controller.lookUpAll();
        controller.createTourRating(TOUR_ID, ratingDto);
        Assertions.assertEquals(tourRatings.size() + 1, controller.lookUpAll().size());
        TourRating tourRating = controller.findByPkTourIdAndPkCustomerId(TOUR_ID, CUSTOMER_ID).orElseThrow(NoSuchElementException::new);
        Assertions.assertEquals(tourRating.getRating(), SCORE);
        Assertions.assertEquals(tourRating.getComment(), COMMENT);
        Assertions.assertEquals(tourRating.getPk().getCustomerId(), CUSTOMER_ID);
    }

    @Test
    public void updateRating() {
        RatingDto ratingDto = new RatingDto(SCORE, COMMENT, CUSTOMER_ID);
        controller.createTourRating(TOUR_ID, ratingDto);
        ratingDto.setScore(UPDATED_SCORE);
        ratingDto.setComment(UPDATED_COMMENT);
        RatingDto saved = controller.updateRating(TOUR_ID, ratingDto);
        Assertions.assertEquals(saved.getScore(), UPDATED_SCORE);
        Assertions.assertEquals(saved.getComment(), UPDATED_COMMENT);
        Assertions.assertEquals(saved.getCustomerId(), CUSTOMER_ID);
    }
}
