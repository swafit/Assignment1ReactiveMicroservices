package com.assignment1.book.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Override
        public void run(String... args) throws Exception {

            BookDTO b1 = new BookDTO("256","The lord of the rings","Adventure,fantasy", "123324345", 6);
            BookDTO b2 = new BookDTO("23","Dune","Sci-fi","546432678", 1);
            BookDTO b3 = new BookDTO("326","Harry Potter","Fantasy","1489747483",24);
            BookDTO b4 = new BookDTO("256","It","Horror","462365632",17);
            BookDTO b5 = new BookDTO("23","The Da Vinci Code","Crime,thriller","646582378",4);

            Flux.just(b1,b2,b3,b4,b5)
                    .flatMap(b -> bookService.insertBook(Mono.just(b))
                        .log(b.toString()))
                    .subscribe();

    }
}


