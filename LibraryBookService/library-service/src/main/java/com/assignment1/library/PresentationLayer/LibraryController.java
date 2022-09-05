package com.assignment1.library.PresentationLayer;

import com.assignment1.library.ServiceLayer.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping()
    public Flux<libraryDTO> getAllLibraries(){
        return libraryService.getAll();
    }
    @PostMapping()
    public Mono<LibraryDTO> insertLibrary(@RequestBody Mono<LibraryDTO> libraryDTOMono){
        return libraryService.insertLibary(libraryDTOMono);
    }
    @PostMapping("{libraryUUIDString}")
    public Mono<ResponseEntity<LibraryDTO>> updateLibraryByLibraryUUIDString(@PathVariable String libraryUUIDString,
                                                                             @RequestBody Mono<LibraryDTO> libraryDTOMono){
        return productService.updateProduct(libraryUUIDString, libraryDTOMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @GetMapping("{productUUIDString}")
    public Mono<ResponseEntity<ProductDTO>> getProductByProductUUID(@PathVariable String productUUIDString){
        return productService.getProductByProductUUIDString(productUUIDString)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("{productUUIDString}")
    public Mono<Void> deleteProductByProductUUID(@PathVariable String productUUIDString){
        return productService.deleteProductByProductUUID(productUUIDString);
    }
}

}
