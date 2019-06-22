package id.my.adhikasp.conduitspringbootwebflux.configuration.database;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongobeeConfiguration {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private MongoProperties properties;

    @Bean
    public Mongobee mongobee(){
        Mongobee runner = new Mongobee(properties.getUri())
                .setDbName(properties.getMongoClientDatabase())
                .setSpringEnvironment(environment)
                .setMongoTemplate(mongoTemplate)
                .setChangeLogsScanPackage(MongobeeConfiguration.class.getPackageName());
        return runner;
    }
}
