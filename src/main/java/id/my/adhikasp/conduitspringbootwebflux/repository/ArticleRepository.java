package id.my.adhikasp.conduitspringbootwebflux.repository;

import id.my.adhikasp.conduitspringbootwebflux.model.Article;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends ReactiveMongoRepository<Article, String> {
}
