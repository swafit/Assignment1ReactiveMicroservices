package com.assignment1.apigateway.DomainClientLayer;

import com.assignment1.apigateway.PresentationLayer.BookDTO;
import com.assignment1.apigateway.Util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@Slf4j
@Service
public class BookServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    String baseUrl;

    public BookServiceClient(@Value("${app.book-service.host}") String bookServiceHost, @Value("${app.book-service.port}") String bookServicePort, RestTemplate restTemplate, ObjectMapper objectMapper){
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        baseUrl = "http://" + bookServiceHost + ":"+bookServicePort+"/api";
    }

    // TODO GET ALL BOOKS
    public Flux<BookDTO> getAllBooks(){
        String url = baseUrl + "/books/";
        //ProductDetailsResponseModel productDetailsResponseModel = restTemplate.getForObject(url, BookDTO.class);
        Flux<BookDTO> bookDTOFlux = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Flux<BookDTO>>() {}).getBody();
        return bookDTOFlux;
    }

    // TODO GET BOOK
        public Flux<BookDTO> getBooksByLibraryId(Integer libraryId){
            Flux<BookDTO> bookDTOFlux;
            try{
                String url = baseUrl + "/books/" + libraryId;
                //responseModel = restTemplate.getForObject(requestUrl, .class);
                bookDTOFlux = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Flux<BookDTO>>() {}).getBody();
            }  catch (HttpClientErrorException e){
                System.out.println("Not Found Caught");
                throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
            }
            return bookDTOFlux;
        }


    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        switch (ex.getStatusCode()) {
            case NOT_FOUND:
                System.out.println("NOt Found Thrown");
                return new NotFoundException(getErrorMessage(ex));
            case UNPROCESSABLE_ENTITY :
                System.out.println("Unprocessable");
                return new InvalidInputException(getErrorMessage(ex));
            default:
                log.warn("Got an unexpected HTTP error: {}, will rethrow it",
                        ex.getStatusCode());
                log.warn("Error body: {}", ex.getResponseBodyAsString());
                return ex;
        }
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return objectMapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }

}
