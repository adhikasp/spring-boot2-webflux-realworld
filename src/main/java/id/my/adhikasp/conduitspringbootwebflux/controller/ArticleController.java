package id.my.adhikasp.conduitspringbootwebflux.controller;

import id.my.adhikasp.conduitspringbootwebflux.model.Article;
import id.my.adhikasp.conduitspringbootwebflux.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private ArticleService articleService;

    @GetMapping()
    public Flux<Article> getArticles() {
        return articleService.getList();
    }

}
