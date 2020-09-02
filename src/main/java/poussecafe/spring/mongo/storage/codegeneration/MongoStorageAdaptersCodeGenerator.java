package poussecafe.spring.mongo.storage.codegeneration;

import java.nio.file.Path;
import poussecafe.source.analysis.Name;
import poussecafe.source.generation.NamingConventions;
import poussecafe.source.generation.StorageAdaptersCodeGenerator;
import poussecafe.source.generation.tools.CompilationUnitEditor;
import poussecafe.source.model.Aggregate;

import static java.util.Objects.requireNonNull;

public class MongoStorageAdaptersCodeGenerator extends StorageAdaptersCodeGenerator {

    @Override
    public void generate(Aggregate aggregate) {
        super.generate(aggregate);
        addMongoDataRepository(aggregate);
    }

    @Override
    protected void updateDefaultAttributesImplementation(Aggregate aggregate, CompilationUnitEditor compilationUnitEditor) {
        var editor = new MongoAttributesImplementationEditor.Builder()
                .compilationUnitEditor(compilationUnitEditor)
                .aggregate(aggregate)
                .build();
        editor.edit();
    }

    @Override
    protected void addDataAccessImplementation(Aggregate aggregate, CompilationUnitEditor compilationUnitEditor) {
        var editor = new MongoDataAccessImplementationEditor.Builder()
                .compilationUnitEditor(compilationUnitEditor)
                .aggregate(aggregate)
                .build();
        editor.edit();
    }

    private void addMongoDataRepository(Aggregate aggregate) {
        var typeName = aggregateMongoRepositoryTypeName(aggregate);
        var compilationUnitEditor = compilationUnitEditor(typeName);
        var editor = new MongoDataRepositoryEditor.Builder()
                .compilationUnitEditor(compilationUnitEditor)
                .aggregate(aggregate)
                .build();
        editor.edit();
    }

    public static Name aggregateMongoRepositoryTypeName(Aggregate aggregate) {
        return new Name(NamingConventions.adaptersPackageName(aggregate),
                aggregate.simpleName() + "DataMongoRepository");
    }

    @Override
    protected String storageName() {
        return STORAGE_NAME;
    }

    public static final String STORAGE_NAME = "Mongo";

    public static class Builder {

        private MongoStorageAdaptersCodeGenerator generator = new MongoStorageAdaptersCodeGenerator();

        public MongoStorageAdaptersCodeGenerator build() {
            requireNonNull(generator.sourceDirectory);
            return generator;
        }

        public Builder sourceDirectory(Path sourceDirectory) {
            generator.sourceDirectory = sourceDirectory;
            return this;
        }
    }

    private MongoStorageAdaptersCodeGenerator() {

    }
}
