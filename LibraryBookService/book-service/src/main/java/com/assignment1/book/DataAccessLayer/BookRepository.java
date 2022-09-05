package foo.bar;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Mono<Book> findProductByProductUUID(String productUUIDString);
    Mono<Void> deleteProductByProductUUID(String productUUIDString);
}

