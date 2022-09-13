package com.assignment1.library.DataAccessLayer;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Library {

    @Id
    private String id;
    private String libraryUUID;
    private String name;
    private String streetNumber;
    private String streetName;
    private String city;
}
