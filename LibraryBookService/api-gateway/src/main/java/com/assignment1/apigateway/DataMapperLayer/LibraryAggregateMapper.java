package com.assignment1.apigateway.DataMapperLayer;

import com.assignment1.apigateway.PresentationLayer.LibraryAggregate;
import com.assignment1.apigateway.PresentationLayer.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibraryAggregateMapper {
    
    Mono<LibraryAggregate> DTOToAggregate(Mono<LibraryDTO> responseModelMono, Flux<BookDTO> bookDTOFlux, Flux<EmployeeDTO> employeeDTOFlux);

    Mono<LibraryDTO> aggregateToDTO(Mono<LibraryAggregate> aggregate);

    Flux<LibraryAggregate> DTOFluxToAggregateFlux(Flux<LibraryDTO> responseModels);

    Flux<LibraryDTO> aggregateFluxToDTOFlux(Flux<LibraryAggregate> aggregates);
}
