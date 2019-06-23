package id.my.adhikasp.conduitspringbootwebflux.configuration.database.changelogs;

import com.github.javafaker.Faker;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import id.my.adhikasp.conduitspringbootwebflux.model.Article;
import id.my.adhikasp.conduitspringbootwebflux.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ChangeLog
@Profile("development")
public class SeedDataMigration {

    private Faker faker;

    private static final int ARTICLE_NUMBER = 100;
    private static final int USER_NUMBER = 10;

    public SeedDataMigration() {
        this.faker = new Faker();
    }

    @ChangeSet(order = "X001", id = "X001", author = "adhikasp")
    public void collectionArticle(MongoTemplate db) {
        for (int i = 1; i <= ARTICLE_NUMBER; i++) {
            String body = fakeParagraph(10);
            Article article = Article.builder()
                    .id(String.valueOf(i))
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

    @ChangeSet(order = "X002", id="X002", author = "adhikasp")
    public void seedUser(MongoTemplate db) {
        for (int i = 1; i <= USER_NUMBER; i++) {
            String username = this.faker.name().username();
            User user = User.builder()
                    .id(String.valueOf(i))
                    .email(username + "@example.com")
                    .username(username)
                    .bio(fakeParagraph(3))
                    .image("https://randomuser.me/api/portraits/thumb/men/99.jpg")
                    .build();
            db.insert(user);
        }
    }

    @ChangeSet(order = "X003", id="X003", author = "adhikasp")
    public void linkArticleWithAuthor(MongoTemplate db) {
        List<Article> articles = db.findAll(Article.class);
        for (Article article : articles) {
            String randomId = this.faker.expression(String.format("#{number.number_between '1','%s'}", USER_NUMBER));
            User author = db.findOne(new BasicQuery(String.format("{ _id : '%s'}", randomId)), User.class);
            article.setAuthor(author);
            System.out.println(author);
            List<String> articleIds = Optional.ofNullable(author.getArticleIdList())
                    .orElse(new ArrayList<>());
            articleIds.add(article.getId());
            author.setArticleIdList(articleIds);
            db.save(article);
            db.save(author);
        }
    }

    private String fakeParagraph(int count) {
        return this.faker.lorem().paragraphs(count)
                .stream()
                .reduce((a, b) -> a.concat(" ").concat(b))
                .orElse("");
    }
}
