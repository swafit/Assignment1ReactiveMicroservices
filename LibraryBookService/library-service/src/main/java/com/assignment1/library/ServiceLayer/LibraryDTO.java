package com.assignment1.library.ServiceLayer;

import lombok.ToString;
import lombok.Data;

@Data
@ToString
public class LibraryDTO {

    private String libraryUUID;
    private String name;
    private String streetNumber;
    private String streetName;
    private String city;

    public LibraryDTO(){}

    public LibraryDTO(String name, String streetNumber, String streetName, String city){
            
    }

}
