package com.cristian.ec.explorecali.service;

import com.cristian.ec.explorecali.domain.Difficulty;
import com.cristian.ec.explorecali.domain.Region;
import com.cristian.ec.explorecali.domain.Tour;
import com.cristian.ec.explorecali.domain.TourPackage;
import com.cristian.ec.explorecali.repo.TourPackageRepository;
import com.cristian.ec.explorecali.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    private final TourRepository tourRepository;
    private final TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(Integer id, String title, String description, String blurb, Float price,
                           Integer duration, Difficulty difficulty, Region region, String bullets,
                           String keywords, String tourPackageName) {
        TourPackage tourPackage = tourPackageRepository
                .findById(tourPackageName)
                .orElseThrow(() -> new RuntimeException("Tour package does not exist " + tourPackageName));

        return tourRepository
                .findById(id)
                .orElse(
                        tourRepository.save(new Tour(id, title, description, blurb, price,
                                duration, difficulty, region, bullets, keywords, tourPackage))
                );
    }

    public long total() {
        return tourRepository.count();
    }
}
