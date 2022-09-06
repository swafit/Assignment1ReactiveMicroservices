package com.assignment1.apigateway.BusinessLayer;


import com.assignment1.apigateway.DataMapperLayer.LibraryAggregateMapper;
import com.assignment1.apigateway.DomainClientLayer.BookServiceClient;
import com.assignment1.apigateway.DomainClientLayer.EmployeeServiceClient;
import com.assignment1.apigateway.DomainClientLayer.LibraryServiceClient;
import com.assignment1.apigateway.PresentationLayer.BookDTO;
import com.assignment1.apigateway.PresentationLayer.EmployeeDTO;
import com.assignment1.apigateway.PresentationLayer.LibraryAggregate;
import com.assignment1.apigateway.PresentationLayer.LibraryDTO;
import com.assignment1.apigateway.Util.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class LibraryAggregatorServiceImpl implements LibraryAggregatorService {
    BookServiceClient bookServiceClient;
    EmployeeServiceClient employeeServiceClient;
    LibraryServiceClient libraryServiceClient;
    LibraryAggregateMapper libraryAggregateMapper;


    public LibraryAggregatorServiceImpl(BookServiceClient bookServiceClient, EmployeeServiceClient employeeServiceClient, LibraryServiceClient libraryServiceClient, LibraryAggregateMapper libraryAggregateMapper) {
        this.bookServiceClient = bookServiceClient;
        this.employeeServiceClient = employeeServiceClient;
        this.libraryServiceClient = libraryServiceClient;
        this.libraryAggregateMapper = libraryAggregateMapper;
    }

    @Override
    public Flux<LibraryAggregate> getAllLibraries() {
        
        return null;
    }

    @Override
    public Mono<LibraryAggregate> getLibraryByLibraryId(Integer libraryId) {

        Mono<LibraryDTO> libraryDTO = libraryServiceClient.getLibraryByLibraryId(libraryId);

        Flux<BookDTO> bookDTOFlux = bookServiceClient.getBooksByLibraryId(libraryId);
        Flux<EmployeeDTO> employeeDTOFlux = employeeServiceClient.getEmployeesByLibraryId(libraryId);

        Mono<LibraryAggregate> aggregate = libraryAggregateMapper.DTOToAggregate(libraryDTO, bookDTOFlux, employeeDTOFlux);


        if(aggregate == null) throw new NotFoundException("No library with library id: " + libraryId);


        return aggregate;
    }

    @Override
    public Mono<LibraryAggregate> addLibrary(LibraryDTO libraryDTO) {
        return null;
    }

    @Override
    public Mono<LibraryAggregate> updateLibraryByLibraryId(LibraryDTO libraryAggregaterequestModel, Integer aggregateId) {
        return null;
    }

    @Override
    public Mono<Void> deleteLibraryByLibraryId(Integer aggregateId) {
        return null;
    }

    @Override
    public Mono<LibraryAggregate> createLibrary(Mono<LibraryAggregate> libraryAggregate){
        return libraryAggregate;
    }
}
