package com.assignment1.apigateway.PresentationLayer;

import com.assignment1.apigateway.BusinessLayer.LibraryAggregatorService;
import com.assignment1.apigateway.Util.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/libraries")
public class LibraryAggregateController {
    private LibraryAggregatorService libraryAggregatorService;

        public LibraryAggregateController(LibraryAggregatorService libraryAggregatorService){
            this.libraryAggregatorService = libraryAggregatorService;
        }

        @GetMapping(
                produces = "application/json"
        )
        public ResponseEntity<Flux<LibraryAggregate>> getAllLibraries(){
            return null;
        }

        @GetMapping(
                value = "/{libraryId}",
                produces = "application/json"
        )
        public ResponseEntity<Mono<LibraryAggregate>> getLibraryByLibraryId(@PathVariable Integer libraryId){
            Mono<LibraryAggregate> libraryAggregate;
            validateLibraryId(libraryId);
            libraryAggregate = libraryAggregatorService.getLibraryByLibraryId(libraryId);
            return ResponseEntity.status(HttpStatus.OK).body(libraryAggregate);
        }

        @PostMapping(
                produces = "application/json",
                consumes = "application/json"
        )
        public ResponseEntity<Mono<LibraryAggregate>> createLibraryAggregate(@RequestBody Mono<LibraryAggregate> libraryAggregate){
            Mono<LibraryAggregate> res;
            res = libraryAggregatorService.createLibrary(libraryAggregate);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }


        @DeleteMapping(
                value = "/{libraryId}"
        )
        public ResponseEntity<?> deleteLibraryByLibraryId(@PathVariable Integer libraryId){
            validateLibraryId(libraryId);
            libraryAggregatorService.deleteLibraryByLibraryId(libraryId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        private void validateLibraryId(Integer libraryId){
            if(libraryId < 0) throw new InvalidInputException("Invalid libraryId given: " + libraryId);
        }
    }
