package id.my.adhikasp.conduitspringbootwebflux.service;

import id.my.adhikasp.conduitspringbootwebflux.model.Article;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArticleService {

    Flux<Article> getList();

    Flux<Article> getFeed();

    Mono<Article> getSingleArticle(String slug);

}
