package poussecafe.spring.mongo.storage.codegeneration.generated.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.spring.mongo.storage.MongoDataAccess;
import poussecafe.spring.mongo.storage.SpringMongoDbStorage;
import poussecafe.spring.mongo.storage.codegeneration.generated.MyAggregate;
import poussecafe.spring.mongo.storage.codegeneration.generated.MyAggregateDataAccess;
import poussecafe.spring.mongo.storage.codegeneration.generated.MyAggregateId;

@DataAccessImplementation(aggregateRoot = MyAggregate.class, dataImplementation = MyAggregateAttributes.class, storageName = SpringMongoDbStorage.NAME)
public class MyAggregateMongoDataAccess extends MongoDataAccess<MyAggregateId, MyAggregateAttributes, String>
        implements
            MyAggregateDataAccess<MyAggregateAttributes> {

    @Override
    protected String convertId(MyAggregateId key) {
        return key.stringValue();
    }

    @Override
    protected MyAggregateDataMongoRepository mongoRepository() {
        return repository;
    }

    @Autowired
    private MyAggregateDataMongoRepository repository;
}