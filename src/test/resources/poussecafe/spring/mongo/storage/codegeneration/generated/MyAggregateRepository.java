package poussecafe.spring.mongo.storage.codegeneration.generated;

import poussecafe.domain.AggregateRepository;

public class MyAggregateRepository extends
        AggregateRepository<MyAggregateId, MyAggregateRoot, MyAggregateRoot.Attributes> {

    @Override
    public MyAggregateDataAccess<MyAggregateRoot.Attributes> dataAccess() {
        return (MyAggregateDataAccess<MyAggregateRoot.Attributes>) super.dataAccess();
    }
}