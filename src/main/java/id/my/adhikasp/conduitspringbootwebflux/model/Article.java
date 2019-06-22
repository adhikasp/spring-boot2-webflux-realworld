package id.my.adhikasp.conduitspringbootwebflux.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;

@Document
@Data
@Builder
public class Article {

    @Id
    private long id;

    @Indexed
    private String slug;

    private String title;

    private String description;

    private String body;

    private List<String> tagList;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private boolean favorited; // TODO this is for specific user

    private long favoritesCount; // TODO this is global

    @DBRef
    private User author;

}
