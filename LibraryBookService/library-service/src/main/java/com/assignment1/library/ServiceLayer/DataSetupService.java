package com.assignment1.library.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner{

    @Autowired
    private LibraryService libraryService;

    @Override
    public void run(String... args) throws Exception {

        LibraryDTO l1 = new LibraryDTO("2", "Cloud Library", "123", "Mason Street", "Brossard");
        LibraryDTO l2 = new LibraryDTO("5", "Cash Library", "567", "5th Avenue", "New York");
        LibraryDTO l3 = new LibraryDTO("6", "Cactus", "3537", "Savana Street", "Somewhere");
        LibraryDTO l4 = new LibraryDTO("23", "Electric Library", "3258", "Wire Street", "Electric Pole");
        LibraryDTO l5 = new LibraryDTO("326", "Cloud Library", "9742", "Mason Street", "Brossard");

        Flux.just(l1,l2,l3,l4,l5)
                .flatMap(l -> libraryService.insertLibrary(Mono.just(l))
                    .log(l1.toString()))
                .subscribe();

    }
}


