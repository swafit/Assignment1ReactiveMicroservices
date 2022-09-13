package com.assignment1.library.ServiceLayer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner{

    private final LibraryService libraryService;

    public DataSetupService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void run(String... args) throws Exception {

        LibraryDTO l1 = new LibraryDTO("Cloud Library", "123", "Mason Street", "Brossard");
        LibraryDTO l2 = new LibraryDTO("Cash Library", "567", "5th Avenue", "New York");
        LibraryDTO l3 = new LibraryDTO("Cactus", "3537", "Savana Street", "Somewhere");
        LibraryDTO l4 = new LibraryDTO("Electric Library", "3258", "Wire Street", "Electric Pole");
        LibraryDTO l5 = new LibraryDTO("Cloud Library", "9742", "Mason Street", "Brossard");

        Flux.just(l1,l2,l3,l4,l5)
                .flatMap(l -> libraryService.insertLibrary(Mono.just(l))
                    .log(l.toString()))
                .subscribe();

    }
}


