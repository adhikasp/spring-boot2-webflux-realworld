package id.my.adhikasp.conduitspringbootwebflux.configuration.database.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import id.my.adhikasp.conduitspringbootwebflux.model.Article;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog
public class Migration1 {

    @ChangeSet(order = "001", id = "001", author = "adhikasp")
    public void collectionInit(MongoTemplate db) {
        db.createCollection(Article.class);
        for (int i = 0; i < 100; i++) {
            Article article = Article.builder()
                    .id(i)
                    .slug("article-" + i)
                    .content("My article number " + i)
                    .build();
            db.insert(article);
        }
    }

}
