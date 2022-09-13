package com.assignment1.book.DataAccessLayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Mono<Book> findBookByBookUUID(String bookUUIDString);
    Mono<Void> deleteBookByBookUUID(String bookUUIDString);
    //Mono<Boolean> existsBookByLibraryId(String libraryId);
    Flux<Book> findBooksByLibraryId(String libraryId);
}

