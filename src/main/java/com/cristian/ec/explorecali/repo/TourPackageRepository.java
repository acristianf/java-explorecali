package com.cristian.ec.explorecali.repo;

import com.cristian.ec.explorecali.domain.TourPackage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TourPackageRepository extends CrudRepository<TourPackage, String> {
    Optional<TourPackage> findTourPackageByTitle(String s);
}
