package id.my.adhikasp.conduitspringbootwebflux.configuration.database.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import id.my.adhikasp.conduitspringbootwebflux.model.Article;
import id.my.adhikasp.conduitspringbootwebflux.model.User;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog
public class CreateTableMigration {

    @ChangeSet(order = "A001", id = "A001", author = "adhikasp")
    public void collectionArticle(MongoTemplate db) {
        MongoCollection<Document> collection = db.createCollection(Article.class);
        collection.createIndex(Indexes.ascending("slug"));
    }

    @ChangeSet(order = "A002", id = "A002", author = "adhikasp")
    public void collectionUser(MongoTemplate db) {
        MongoCollection<Document> collection = db.createCollection(User.class);
        collection.createIndex(Indexes.ascending("username"));
        collection.createIndex(Indexes.ascending("email"));
    }

}
