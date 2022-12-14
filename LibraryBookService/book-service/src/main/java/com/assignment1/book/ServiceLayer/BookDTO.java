package com.assignment1.book.ServiceLayer;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BookDTO {

    private String bookUUID;
    private String libraryId;
    private String name;
    private String genre;
    private String ISBN;
    private int quantity;


    public BookDTO(String libraryId, String name, String genre, String ISBN, int quantity){
        this.libraryId = libraryId;
        this.name = name;
        this.genre = genre;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }

}