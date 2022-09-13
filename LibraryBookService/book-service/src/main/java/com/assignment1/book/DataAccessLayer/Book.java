package com.assignment1.book.DataAccessLayer;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Book {

    @Id
    private String id;
    private String bookUUID;
    private String libraryId;
    private String name;
    private String genre;
    private String ISBN;
    private int quantity;
}
