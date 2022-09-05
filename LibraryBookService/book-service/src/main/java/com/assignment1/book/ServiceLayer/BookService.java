package com.assignment1.book.ServiceLayer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
        Flux<BookDTO> getAll();
        Mono<BookDTO> insertBook(Mono<BookDTO> bookDTOMono);
            Mono<BookDTO> updateBook(String bookUUIDString, Mono<BookDTO> bookDTOMono);
            Mono<BookDTO> getBookByBookUUIDString(String bookUUIDString);
            Mono<Void> deleteBookByBookUUID(String bookUUIDString);
        }

