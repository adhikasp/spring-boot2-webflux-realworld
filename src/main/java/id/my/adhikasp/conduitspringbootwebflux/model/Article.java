package id.my.adhikasp.conduitspringbootwebflux.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Article {

    @Id
    private long id;

    @Indexed
    private String slug;

    private String content;

}
