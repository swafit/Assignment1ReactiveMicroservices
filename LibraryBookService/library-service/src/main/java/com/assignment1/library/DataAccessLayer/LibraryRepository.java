package com.assignment1.library.DataAccessLayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LibraryRepository extends ReactiveMongoRepository<Library, String> {

    Mono<Library> findLibraryByLibraryUUID(String libraryUUIDString);
    Mono<Void> deleteLibraryByLibraryUUID(String libraryUUIDString);
    //boolean existsLibraryByLibraryUUID(String libraryUUIDString);
}
