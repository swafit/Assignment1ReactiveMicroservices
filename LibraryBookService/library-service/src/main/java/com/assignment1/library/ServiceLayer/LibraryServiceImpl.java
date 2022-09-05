package com.assignment1.library.ServiceLayer;


import com.assignment1.library.DataAccessLayer.LibraryRepository;
import com.assignment1.library.Util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.assignment1.library.DataAccessLayer.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LibraryServiceImpl implements LibraryService{

    @Autowired
    public LibraryRepository repository;

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
            .map(EntityDtoUtil::toDTO);
    }

    @Override
        public Mono<LibraryDTO> updateLibrary(String libraryUUIDString, Mono<LibraryDTO> libraryDTOMono) {
            return repository.findLibraryByLibraryUUID(libraryUUIDString)
                    .flatMap(p -> libraryDTOMono
                            .map(EntityDtoUtil::toEntity)
                            .doOnNext(e-> e.setLibraryUUID(p.getLibraryUUID()))
                            .doOnNext(e -> e.setId(p.getId()))
                    )
                    .flatMap(repository::save)
                    .map(EntityDtoUtil::toDTO);

        }

        @Override
        public Mono<LibraryDTO> getLibraryByLibraryUUIDString(String libraryUUIDString) {
            return repository.findLibraryByLibraryUUID(libraryUUIDString)
                    .map(EntityDtoUtil::toDTO);
        }

        @Override
        public Mono<Void> deleteLibraryByLibraryUUID(String libraryUUIDString) {
            return repository.deleteLibraryByLibraryUUID(libraryUUIDString);
        }

}
