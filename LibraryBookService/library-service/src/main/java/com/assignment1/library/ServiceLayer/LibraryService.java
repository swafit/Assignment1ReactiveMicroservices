package com.assignment1.library.ServiceLayer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LibraryService {
    Flux<LibraryDTO> getAll();
    Mono<LibraryDTO> insertLibrary(Mono<LibraryDTO> libraryDTOMono);
    Mono<LibraryDTO> updateLibrary(String libraryUUID, Mono<LibraryDTO> libraryDTOMono);
    Mono<LibraryDTO> getLibraryByLibraryUUIDString(String libraryUUIDString);
    Mono<Void> deleteLibrary(String productUUIDString);
}
