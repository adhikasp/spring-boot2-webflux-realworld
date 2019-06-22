package id.my.adhikasp.conduitspringbootwebflux.configuration.database.changelogs;

import com.github.javafaker.Faker;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import id.my.adhikasp.conduitspringbootwebflux.model.Article;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.ZonedDateTime;

@ChangeLog
@Profile("development")
public class SeedDataMigration {

    private Faker faker;

    public SeedDataMigration() {
        this.faker = new Faker();
    }

    @ChangeSet(order = "X001", id = "X001", author = "adhikasp")
    public void collectionArticle(MongoTemplate db) {
        for (int i = 0; i < 100; i++) {
            String body = this.faker.lorem().paragraphs(10)
                    .stream()
                    .reduce((a, b) -> a.concat(" ").concat(b))
                    .orElse("");
            Article article = Article.builder()
                    .id(i)
                    .slug("article-" + i)
                    .title(this.faker.lorem().sentence(5))
                    .description(body.subSequence(0, 30).toString())
                    .body(body)
                    .createdAt(ZonedDateTime.now())
                    .updatedAt(ZonedDateTime.now())
                    .favorited(true)
                    .favoritesCount(100)
                    .build();
            db.insert(article);
        }
    }
}
