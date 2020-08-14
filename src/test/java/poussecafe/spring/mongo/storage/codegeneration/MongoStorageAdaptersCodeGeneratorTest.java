package poussecafe.spring.mongo.storage.codegeneration;

import poussecafe.source.generation.GeneratorTest;

public class MongoStorageAdaptersCodeGeneratorTest extends GeneratorTest {

    @Override
    protected String[] packageNameSegments() {
        return new String[] { "poussecafe", "spring", "mongo", "storage", "codegeneration", "generated" };
    }

    @Override
    protected void givenStorageGenerator() {
        generator = new MongoStorageAdaptersCodeGenerator.Builder()
                .sourceDirectory(sourceDirectory())
                .build();
    }

    private MongoStorageAdaptersCodeGenerator generator;

    @Override
    protected void whenGeneratingStorageCode() {
        generator.generate(aggregate());
    }
}
