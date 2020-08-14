package poussecafe.spring.mongo.storage.codegeneration;

import poussecafe.source.analysis.Name;
import poussecafe.source.generation.AggregateCodeGenerationConventions;
import poussecafe.source.generation.tools.AstWrapper;
import poussecafe.source.generation.tools.ComilationUnitEditor;
import poussecafe.source.generation.tools.Visibility;
import poussecafe.source.model.Aggregate;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("unchecked")
public class MongoDataRepositoryEditor {

    public void edit() {
        compilationUnitEditor.setPackage(AggregateCodeGenerationConventions.adaptersPackageName(aggregate));

        var mongoRepositoryTypeName = new Name("org.springframework.data.mongodb.repository.MongoRepository");
        compilationUnitEditor.addImportLast(mongoRepositoryTypeName);

        var typeEditor = compilationUnitEditor.typeDeclaration();

        typeEditor.modifiers().setVisibility(Visibility.PUBLIC);
        typeEditor.setInterface(true);
        var typeName = MongoStorageAdaptersCodeGenerator.aggregateMongoRepositoryTypeName(aggregate);
        typeEditor.setName(typeName);

        var mongoRepositoryType = ast.newParameterizedType(mongoRepositoryTypeName.getIdentifier());
        mongoRepositoryType.typeArguments().add(ast.newSimpleType(
                AggregateCodeGenerationConventions.aggregateAttributesImplementationTypeName(aggregate).getIdentifier()));
        mongoRepositoryType.typeArguments().add(ast.newSimpleType("String"));
        typeEditor.addSuperinterface(mongoRepositoryType);

        compilationUnitEditor.flush();
    }

    private Aggregate aggregate;

    public static class Builder {

        private MongoDataRepositoryEditor editor = new MongoDataRepositoryEditor();

        public MongoDataRepositoryEditor build() {
            requireNonNull(editor.compilationUnitEditor);
            requireNonNull(editor.aggregate);

            editor.ast = new AstWrapper(editor.compilationUnitEditor.ast());

            return editor;
        }

        public Builder compilationUnitEditor(ComilationUnitEditor compilationUnitEditor) {
            editor.compilationUnitEditor = compilationUnitEditor;
            return this;
        }

        public Builder aggregate(Aggregate aggregate) {
            editor.aggregate = aggregate;
            return this;
        }
    }

    private MongoDataRepositoryEditor() {

    }

    private ComilationUnitEditor compilationUnitEditor;

    private AstWrapper ast;
}
