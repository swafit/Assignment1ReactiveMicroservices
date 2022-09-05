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

        LibraryDTO p1 = new LibraryDTO("Cloud Library", "123", "Mason Street", "Brossard");
        LibraryDTO p2 = new LibraryDTO("Cloud Library", "567", "Mason Street", "Brossard");
        LibraryDTO p3 = new LibraryDTO("Cloud Library", "3537", "Mason Street", "Brossard");
        LibraryDTO p4 = new LibraryDTO("Cloud Library", "3258", "Mason Street", "Brossard");
        LibraryDTO p5 = new LibraryDTO("Cloud Library", "9742", "Mason Street", "Brossard");

        Flux.just(p1,p2,p3,p4,p5)
                .flatMap(p -> libraryService.insertLibrary(Mono.just(p))
                    .log(p1.toString()))
                .subscribe();

    }
}

}
