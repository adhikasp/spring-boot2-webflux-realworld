package id.my.adhikasp.conduitspringbootwebflux.service.impl;

import id.my.adhikasp.conduitspringbootwebflux.model.Article;
import id.my.adhikasp.conduitspringbootwebflux.repository.ArticleRepository;
import id.my.adhikasp.conduitspringbootwebflux.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Flux<Article> getList() {
        return this.articleRepository.findAll();
    }

    @Override
    public Flux<Article> getFeed() {
        return this.articleRepository.findAll();
    }

    @Override
    public Mono<Article> getSingleArticle(String slug) {
        Example<Article> example = Example.of(
                Article.builder()
                        .slug(slug)
                        .build()
        );
        return this.articleRepository.findOne(example);
    }
}
