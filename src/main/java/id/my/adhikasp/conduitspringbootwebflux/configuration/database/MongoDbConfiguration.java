package id.my.adhikasp.conduitspringbootwebflux.configuration.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
public class MongoDbConfiguration {

    @Component
    public class ZonedDateTimeReaderConverter implements Converter<Date, ZonedDateTime> {
        @Override
        public ZonedDateTime convert(Date source) {
            return source.toInstant().atZone(ZoneOffset.UTC);
        }
    }

    @Component
    public class ZonedDateTimeWriterConverter implements Converter<ZonedDateTime, Date> {
        @Override
        public Date convert(ZonedDateTime source) {
            return Date.from(source.toInstant());
        }
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions(
            ZonedDateTimeReaderConverter reader,
            ZonedDateTimeWriterConverter writer
    ) {
        List<Converter> converters = Arrays.asList(reader, writer);
        return new MongoCustomConversions(converters);
    }
}
