package poussecafe.spring.mongo.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringMongoConfig {

    @Bean
    public SpringMongoDbStorage springMongoDbStorage() {
        return SpringMongoDbStorage.instance();
    }
}
