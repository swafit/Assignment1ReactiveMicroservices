package com.assignment1.book.ServiceLayer;

import com.assignment1.book.DataAccessLayer.BookRepository;
import com.assignment1.book.Util.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService{

    public final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<BookDTO> getAll(){
        return repository.findAll()
            .map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<BookDTO> insertBook(Mono<BookDTO> bookDTOMono){
        return bookDTOMono
            .map(EntityDtoUtil::toEntity)
            .doOnNext(e -> e.setBookUUID(EntityDtoUtil.generateUUIDString()))
            .flatMap(repository::insert)
            .map(EntityDtoUtil::toDto);
    }

    @Override
        public Mono<BookDTO> updateBook(String bookUUIDString, Mono<BookDTO> bookDTOMono) {
//            if(!repository.existsBookByLibraryId(bookUUIDString)) {
//                System.out.println("NotFoundThrown");
//                throw new NotFoundException("Unknown BookUUID provided: " + bookUUIDString);
//                }
            return repository.findBookByBookUUID(bookUUIDString)
                    .flatMap(p -> bookDTOMono
                            .map(EntityDtoUtil::toEntity)
                            .doOnNext(e->e.setBookUUID(p.getBookUUID()))
                            .doOnNext(e -> e.setId(p.getId())))
                            .flatMap(repository::save)
                            .map(EntityDtoUtil::toDto);

        }

        @Override
        public Flux<BookDTO> getBooksByLibraryId(String libraryId) {
//            if(!repository.existsBookByLibraryId(libraryId)) {
//                System.out.println("NotFoundThrown");
//                throw new NotFoundException("Unknown BookUUID provided: " + libraryId);
//                }
            return repository.findBooksByLibraryId(libraryId)
                    .map(EntityDtoUtil::toDto);
        }

        @Override
        public Mono<Void> deleteBookByBookUUID(String bookUUIDString) {
//            if(!repository.existsBookByLibraryId(bookUUIDString)) {
//                System.out.println("NotFoundThrown");
//                throw new NotFoundException("Unknown BookUUID provided: " + bookUUIDString);
//                }
            return repository.deleteBookByBookUUID(bookUUIDString);
        }
    }