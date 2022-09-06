package com.assignment1.apigateway.DomainClientLayer;

import com.assignment1.apigateway.PresentationLayer.LibraryDTO;
import com.assignment1.apigateway.Util.HttpErrorInfo;
import com.assignment1.apigateway.Util.InvalidInputException;
import com.assignment1.apigateway.Util.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@Service
public class LibraryServiceClient {

    private String url;


    private final RestTemplate restTemplate;

    public LibraryServiceClient(RestTemplate restTemplate, @Value("${app.library.port}") String libraryServicePort, @Value("${app.library.host}") String libraryServiceHost){

        this.restTemplate = restTemplate;

        this.url = "http://" + libraryServiceHost + ":" + libraryServicePort + "/api";
    }

    public Mono<LibraryDTO> getLibraryByLibraryId(Integer libraryId){
        Mono<LibraryDTO> libraryDTOMono;
        try{
            String requestUrl = url + "/library/" + libraryId;
            //responseModel = restTemplate.getForObject(requestUrl, .class);
            libraryDTOMono = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Mono<LibraryDTO>>() {}).getBody();
        }  catch (HttpClientErrorException e){
            System.out.println("Not Found Caught");
            throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
        }
        return libraryDTOMono;
    }
/*
    public MovieDetailsResponseModel createMovie(MovieAggregateRequestModel movieAggregateRequestModel){
        MovieRequestModel movieRequestModel = new MovieRequestModel();

        movieRequestModel.setDirector(movieAggregateRequestModel.getDirector());
        movieRequestModel.setTitle(movieAggregateRequestModel.getTitle());
        movieRequestModel.setLangName(movieAggregateRequestModel.getLang());

        MovieDetailsResponseModel responseModel;
        try {
            String requestUrl = url + "/movies";
            responseModel = restTemplate.postForObject(requestUrl, movieRequestModel, .class);
        }catch (NotFoundException e){
            System.out.println("Not Found Caught");
            throw handleHttpClientException(new HttpClientErrorException(NOT_FOUND));
        }  catch (Exception e){
            throw handleHttpClientException(new HttpClientErrorException(UNPROCESSABLE_ENTITY));
        }
        return responseModel;
    }

    public void deleteMovie(Integer movieId){
        try {
            String requestUrl = url + "/movies/" + movieId;

            restTemplate.delete(requestUrl);
        }catch (NotFoundException e){
            throw handleHttpClientException(new HttpClientErrorException(NOT_FOUND));
        }  catch (Exception e){
            throw handleHttpClientException(new HttpClientErrorException(UNPROCESSABLE_ENTITY));
        }
    }
*/
    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        switch (ex.getStatusCode()) {
            case NOT_FOUND:
                System.out.println("Not Found Thrown");
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
            return null;//mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (Exception ioex/*IOException ioex*/) {
            return ioex.getMessage();
        }
    }
}
