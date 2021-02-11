package poussecafe.spring.mongo.storage;

import poussecafe.storage.DirectMessageSending;
import poussecafe.storage.MessageSendingPolicy;
import poussecafe.storage.NoTransactionRunner;
import poussecafe.storage.Storage;
import poussecafe.storage.TransactionRunner;

public class SpringMongoDbStorage extends Storage {

    public static final String NAME = "SpringMongo";

    public static SpringMongoDbStorage instance() {
        return INSTANCE;
    }

    private static final SpringMongoDbStorage INSTANCE = new SpringMongoDbStorage();

    @Override
    protected MessageSendingPolicy initMessageSendingPolicy() {
        return new DirectMessageSending();
    }

    @Override
    protected TransactionRunner initTransactionRunner() {
        return new NoTransactionRunner();
    }

    private SpringMongoDbStorage() {

    }

    @Override
    public String name() {
        return NAME;
    }
}
