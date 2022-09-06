package com.assignment1.apigateway.DomainClientLayer;

import com.assignment1.apigateway.PresentationLayer.EmployeeDTO;
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
public class EmployeeServiceClient{

        private final RestTemplate restTemplate;

        private ObjectMapper objectMapper;

        String baseUrl;

        public EmployeeServiceClient(@Value("${app.employee-service.host}") String employeeServiceHost, @Value("${app.employee-service.port}") String employeeServicePort, RestTemplate restTemplate, ObjectMapper objectMapper){
            this.restTemplate = restTemplate;
            this.objectMapper = objectMapper;
            baseUrl = "http://" + employeeServiceHost + ":"+employeeServicePort+"/api";
        }
        //Get all employees
        public Flux<EmployeeDTO> getAllEmployees(){
            Flux<EmployeeDTO> employeeDTOFlux;
            try{
            String url = baseUrl + "/employees/";
            //ProductDetailsResponseModel productDetailsResponseModel = restTemplate.getForObject(url, BookDTO.class);
            employeeDTOFlux = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Flux<EmployeeDTO>>() {}).getBody();
            } catch(HttpClientErrorException e){
                System.out.println("Not Found Caught");
                throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
            }
            return employeeDTOFlux;
        }

            //Get single employee

        public Flux<EmployeeDTO> getEmployeesByLibraryId(Integer libraryId){
            Flux<EmployeeDTO> employeeDTOFlux;
            try{
                String url = baseUrl + "/employee"+libraryId;
                employeeDTOFlux = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Flux<EmployeeDTO>>() {}).getBody();
            }   catch(HttpClientErrorException e){
                System.out.println("Not Found Caught");
                throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
            }
            return employeeDTOFlux;
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

        /*
        //Update employee
        public Mono<EmployeeDTO> updateEmployeeByEmployeeId(Integer employeeId){
            String url = baseUrl + "/employee/"+employeeId;
            Mono<EmployeeDTO> employeeDtoMono = restTemplate.exchange(url, HttpMethod.PUT, null, new ParameterizedTypeReference<Mono<EmployeeDTO>>() {}).getBody();        String url = baseUrl + ("/employee/"+employeeId);
            return employeeDtoMono;
        }


        //Create employee
        public Mono<EmployeeDTO> createEmployee(Mono<EmployeeDTO> employeeDTOMono){
            String url = baseUrl + "/employee/name/{name}/role/{role}/libraryUUID/{libraryUUID}";
            Mono<EmployeeDTO> employeeDTOMono = restTemplate.exchange(url, HttpMethod.POST, null, new ParameterizedTypeReference<Mono<EmployeeDTO>>() {}).getBody();
            return employeeDTOMono;
        }

        //Delete employee
        public void deleteEmployee(Mono<EmployeeDTO> employeeDTOMono){
            String url = baseUrl + "/employee/name/{name}/role/{role}/libraryUUID/{libraryUUID}";
            (url, HttpMethod.DELETE, null, new ParameterizedTypeReference<Mono<EmployeeDTO>>() {}).getBody();
                    restTemplate.Delete(url)
          }
              }
    */


}
