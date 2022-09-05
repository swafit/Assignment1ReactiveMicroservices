package com.assignment1.book.PresentationLayer;

import com.assignment1.book.ServiceLayer.BookDTO;
import com.assignment1.book.ServiceLayer.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
        private BookService bookService;

    @GetMapping()
    public Flux<BookDTO> getAllBooks(){
            return bookService.getAll();
        }

    @PostMapping()
    public Mono<BookDTO> insertBook(@RequestBody Mono<BookDTO> bookDTOMono){
        return bookService.insertBook(bookDTOMono);
    }

    @PostMapping("{bookUUIDString}")
    public Mono<ResponseEntity<BookDTO>> updateBookByBookUUIDString(@PathVariable String bookUUIDString,
                                                                    @RequestBody Mono<BookDTO> bookDTOMono){
        return bookService.updateBook(bookUUIDString, bookDTOMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("{bookUUIDString}")
    public Mono<ResponseEntity<BookDTO>> getBookByBookUUID(@PathVariable String bookUUIDString){
        return bookService.getBookByBookUUIDString(bookUUIDString)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{bookUUIDString}")
    public Mono<Void> deleteBookByBookUUID(@PathVariable String bookUUIDString){
        return bookService.deleteBookByBookUUID(bookUUIDString);
    }
}

