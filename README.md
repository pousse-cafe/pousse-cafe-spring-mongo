![Travis build status](https://travis-ci.org/pousse-cafe/pousse-cafe-spring-mongo.svg?branch=master)
![Maven status](https://maven-badges.herokuapp.com/maven-central/org.pousse-cafe-framework/pousse-cafe-spring-mongo/badge.svg)

# Pousse-Café Spring Mongo

This storage plugin uses [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb) as its backend and is
therefore able to store data into a MongoDB database.

`EntityAttributes` [implementations](http://www.pousse-cafe-framework.org/doc/reference-guide/#implement-aggregates)
should be annotated like Spring Data MongoDB documents (i.e. using `@Document`, `@Id`, etc.).

`MongoDataAccess` expects subclasses to define `mongoRepository` method which must return a Spring Data
`MongoRepository`. The `convertId` method must be defined in order to convert an aggregate's ID into a serializable
object. In order to access the `MongoRepository`, simply autowire it in your data access.

Here is an example of data access implementation:

    public class ExampleMongoDataAccess extends MongoDataAccess<ExampleId, ExampleData, String> implements ExampleDataAccess<ProductData> {
    
        @Override
        protected String convertId(ExampleId id) {
            return ...;
        }
    
        @Override
        protected MongoRepository<ExampleData, String> mongoRepository() {
            return repository;
        }
    
        @Autowired
        private ExampleDataMongoRepository repository;
    
        ...
    }

Your Spring configuration class then looks like this (do not forget to include `poussecafe.spring` in your
package scan):

    @Configuration
    @ComponentScan(basePackages = { "poussecafe.spring" })
    public class AppConfiguration {
    
        @Bean
        public Bundles bundles(
                Messaging messaging,
                SpringMongoDbStorage storage) {
            MessagingAndStorage messagingAndStorage = new MessagingAndStorage(messaging, storage);
            return new Bundles.Builder()
                // Register your bundles here using withBundle and use messagingAndStorage
                // when building them
                .build();
        }
    }

## Configure your Spring Boot Maven project

Add the following snippet to your POM:

    <dependency>
        <groupId>org.pousse-cafe-framework</groupId>
        <artifactId>pousse-cafe-spring-pulsar</artifactId>
        <version>${poussecafe.spring.pulsar.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.pulsar</groupId>
        <artifactId>pulsar-client</artifactId>
        <version>${pulsar.client.version}</version>
    </dependency>
