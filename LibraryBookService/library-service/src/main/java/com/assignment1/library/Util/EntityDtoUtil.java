package com.assignment1.library.Util;

import com.assignment1.library.DataAccessLayer.Library;
import com.assignment1.library.ServiceLayer.LibraryDTO;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class EntityDtoUtil {
    
    public static LibraryDTO toDto (Library Library){
        LibraryDTO dto = new LibraryDTO();
        BeanUtils.copyProperties(Library, dto);
        return dto;
    }

    public static Library toEntity (LibraryDTO dto){
        Library library = new Library();
        BeanUtils.copyProperties(dto, library);
        return library;           
    }

    public static String generateUUIDString(){
        return UUID.randomUUID().toString();
    }
}
