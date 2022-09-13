package com.assignment1.library.PresentationLayer;

import com.assignment1.library.ServiceLayer.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.assignment1.library.ServiceLayer.LibraryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("library")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping()
    public Flux<LibraryDTO> getAllLibraries(){
        return libraryService.getAll();
    }
    @PostMapping()
    public Mono<LibraryDTO> insertLibrary(@RequestBody Mono<LibraryDTO> libraryDTOMono){
        return libraryService.insertLibrary(libraryDTOMono);
    }
    @PutMapping("{libraryUUIDString}")
    public Mono<ResponseEntity<LibraryDTO>> updateLibraryByLibraryUUIDString(@PathVariable String libraryUUIDString, @RequestBody Mono<LibraryDTO> libraryDTOMono){
        return libraryService.updateLibrary(libraryUUIDString, libraryDTOMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @GetMapping("{libraryUUIDString}")
    public Mono<ResponseEntity<LibraryDTO>> getProductByProductUUID(@PathVariable String libraryUUIDString){
        return libraryService.getLibraryByLibraryUUIDString(libraryUUIDString)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("{libraryUUIDString}")
    public Mono<Void> deleteProductByProductUUID(@PathVariable String libraryUUIDString){
        return libraryService.deleteLibrary(libraryUUIDString);
    }
}