package com.assignment1.book.ServiceLayer;

import com.assignment1.book.DataAccessLayer.BookRepository;
import com.assignment1.book.Util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    public BookRepository repository;

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
            .map(EntityDtoUtil::toDTO);
    }

    @Override
        public Mono<BookDTO> updateBook(String bookUUIDString, Mono<BookDTO> bookDTOMono) {
            return repository.findBookByBookUUID(bookUUIDString)
                    .flatMap(p -> bookDTOMono
                            .map(EntityDtoUtil::toEntity)
                            .doOnNext(e->e.setBookUUID(p.getBookUUID()))
                            .doOnNext(e -> e.setId(p.getId()))
                    )
                    .flatMap(repository::save)
                    .map(EntityDtoUtil::toDTO);

        }

        @Override
        public Mono<BookDTO> getBookByBookUUIDString(String bookUUIDString) {
            return repository.findBookByBookUUID(bookUUIDString)
                    .map(EntityDtoUtil::toDTO);
        }

        @Override
        public Mono<Void> deleteBookByBookUUID(String bookUUIDString) {
            return repository.deleteBookByBookUUID(bookUUIDString);
        }
    }