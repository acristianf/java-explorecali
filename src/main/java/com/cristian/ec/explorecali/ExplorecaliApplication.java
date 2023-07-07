package com.cristian.ec.explorecali;

import com.cristian.ec.explorecali.domain.Tour;
import com.cristian.ec.explorecali.service.TourPackageService;
import com.cristian.ec.explorecali.service.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ExplorecaliApplication implements CommandLineRunner {

    @Autowired
    private TourPackageService tourPackageService;
    @Autowired
    private TourService tourService;

    public static void main(String[] args) {
        SpringApplication.run(ExplorecaliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createTourPackages();
        long numOfPackages = tourPackageService.total();
        createTours("ExploreCalifornia.json");
        long numOfTours = tourService.total();
    }

    private void createTours(String fileToImport) throws IOException {
        List<TourFromFile> toursFromFile = TourFromFile.read(fileToImport);
        toursFromFile.forEach(imported -> {
            tourService.createTour(
                    imported.getTitle(),
                    imported.getDescription(),
                    imported.getBlurb(),
                    imported.getPrice(),
                    imported.getDuration(),
                    imported.getDifficulty(),
                    imported.getRegion(),
                    imported.getBullets(),
                    imported.getKeywords(),
                    imported.getPackageType()
            );
        });

    }

    private void createTourPackages() {
        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("FS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");
    }

    private static class TourFromFile {

        private String packageType, title, description, blurb, price, length, difficulty, region,
                bullets, keywords;

        static List<TourFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper()
                    .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                    .readValue(new FileInputStream(fileToImport), new TypeReference<>() {
                    });
        }

        protected TourFromFile() {
        }

        public String getPackageType() {
            return packageType;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getBlurb() {
            return blurb;
        }

        public String getPrice() {
            return price;
        }

        public String getDuration() {
            return length;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public String getRegion() {
            return region;
        }

        public String getBullets() {
            return bullets;
        }

        public String getKeywords() {
            return keywords;
        }
    }

}
