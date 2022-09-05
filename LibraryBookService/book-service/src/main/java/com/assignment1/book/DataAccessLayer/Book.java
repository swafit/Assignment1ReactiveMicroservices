package foo.bar;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Book {
    @ID
    private String id;
    private String name;
    private String genre;
    private String ISBN;
    private Bool availability;
}
