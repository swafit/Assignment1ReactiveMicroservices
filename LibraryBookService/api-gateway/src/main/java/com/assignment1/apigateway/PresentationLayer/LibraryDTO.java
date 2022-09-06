package com.assignment1.apigateway.PresentationLayer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LibraryDTO {

    private String libraryUUID;
    private String name;
    private String streetNumber;
    private String streetName;
    private String city;

    public LibraryDTO(String name, String streetNumber, String streetName, String city) {
        this.name = name;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
    }


}
