package com.assignment1.book.Util;

import com.assignment1.book.DataAccessLayer.Book;
import com.assignment1.book.ServiceLayer.BookDTO;
import org.springframework.beans.BeanUtils;
import java.util.UUID;

public class EntityDtoUtil {

    public static BookDTO toDto (Book book){
        BookDTO dto = new BookDTO();
        BeanUtils.copyProperties(book, dto);
        return dto;
    }

    public static Book toEntity (BookDTO dto){
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);
        return book;           
    }

    public static String generateUUIDString(){
        return UUID.randomUUID().toString();
    }

}
