package com.assignment1.apigateway.BusinessLayer;

import com.assignment1.apigateway.PresentationLayer.LibraryAggregate;
import com.assignment1.apigateway.PresentationLayer.LibraryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.ResponseEntity;
public interface LibraryAggregatorService {

    Flux<LibraryAggregate> getAllLibraries();


    Mono<LibraryAggregate> getLibraryByLibraryId(Integer libraryId);

    Mono<LibraryAggregate> addLibrary(LibraryDTO libraryDTO);

    Mono<LibraryAggregate> updateLibraryByLibraryId(LibraryDTO libraryDTO, Integer libraryId);

    Mono<Void> deleteLibraryByLibraryId(Integer libraryId);
    Mono<LibraryAggregate> createLibrary(Mono<LibraryAggregate> libraryAggregate);
    
}
