package com.assignment1.apigateway.PresentationLayer;

import lombok.*;


@Data
@ToString
@NoArgsConstructor
public class BookDTO {

    private String bookUUID;
    private String name;
    private String genre;
    private String ISBN;
    private int quantity;

    public BookDTO(String name, String genre, String ISBN, int quantity){
        this.name = name;
        this.genre = genre;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }
}

