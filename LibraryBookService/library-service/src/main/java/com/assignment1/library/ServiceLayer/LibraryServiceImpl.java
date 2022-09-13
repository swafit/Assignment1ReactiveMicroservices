package com.assignment1.library.ServiceLayer;


import com.assignment1.library.DataAccessLayer.LibraryRepository;
import com.assignment1.library.Util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LibraryServiceImpl implements LibraryService{

    public final LibraryRepository repository;

    public LibraryServiceImpl(LibraryRepository repository) { this.repository = repository; }

    @Override
    public Flux<LibraryDTO> getAll(){
        return repository.findAll()
            .map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<LibraryDTO> insertLibrary(Mono<LibraryDTO> libraryDTOMono){
        return libraryDTOMono
            .map(EntityDtoUtil::toEntity)
            .doOnNext(e -> e.setLibraryUUID(EntityDtoUtil.generateUUIDString()))
            .flatMap(repository::insert)
            .map(EntityDtoUtil::toDto);
    }

    @Override
        public Mono<LibraryDTO> updateLibrary(String libraryUUIDString, Mono<LibraryDTO> libraryDTOMono) {
//            if(!repository.existsLibraryByLibraryUUID(libraryUUIDString)) {
//                System.out.println("NotFoundThrown");
//                throw new NotFoundException("Unknown BookUUID provided: " + libraryUUIDString);
//                }
            return repository.findLibraryByLibraryUUID(libraryUUIDString)
                    .flatMap(p -> libraryDTOMono
                            .map(EntityDtoUtil::toEntity)
                            .doOnNext(e-> e.setLibraryUUID(p.getLibraryUUID()))
                            .doOnNext(e -> e.setId(p.getId()))
                    )
                    .flatMap(repository::save)
                    .map(EntityDtoUtil::toDto);

        }

        @Override
        public Mono<LibraryDTO> getLibraryByLibraryUUIDString(String libraryUUIDString) {
//            if(!repository.existsLibraryByLibraryUUID(libraryUUIDString)) {
//                System.out.println("NotFoundThrown");
//                throw new NotFoundException("Unknown BookUUID provided: " + libraryUUIDString);
//                }
            return repository.findLibraryByLibraryUUID(libraryUUIDString)
                    .map(EntityDtoUtil::toDto);
        }

        @Override
        public Mono<Void> deleteLibrary(String libraryUUIDString) {
//            if(!repository.existsLibraryByLibraryUUID(libraryUUIDString)) {
//                System.out.println("NotFoundThrown");
//                throw new NotFoundException("Unknown BookUUID provided: " + libraryUUIDString);
//                }
            return repository.deleteLibraryByLibraryUUID(libraryUUIDString);

        }

}
