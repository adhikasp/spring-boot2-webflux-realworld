package id.my.adhikasp.conduitspringbootwebflux.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
public class User {

    @Id
    private String id;

    @Indexed
    private String email;

    @Indexed
    private String username;

    private String bio;

    private String image;

    // Relation
    private List<String> articleIdList;

}
