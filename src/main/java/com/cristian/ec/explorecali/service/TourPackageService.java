package com.cristian.ec.explorecali.service;

import com.cristian.ec.explorecali.domain.TourPackage;
import com.cristian.ec.explorecali.repo.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackageService {
    private final TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
        return tourPackageRepository
                .findById(code)
                .orElse(
                        tourPackageRepository.save(new TourPackage(code, name))
                );
    }

    // TODO: complete this method
    public Iterable<TourPackage> lookup() {
        return tourPackageRepository.findAll();
    }

    // TODO: complete this method
    public long total() {
        return tourPackageRepository.count();
    }
}
