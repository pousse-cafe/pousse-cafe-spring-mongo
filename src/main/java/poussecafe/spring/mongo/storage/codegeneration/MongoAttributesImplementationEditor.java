package poussecafe.spring.mongo.storage.codegeneration;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import poussecafe.source.generation.AggregateAttributesImplementationEditor;
import poussecafe.source.generation.tools.ComilationUnitEditor;
import poussecafe.source.model.Aggregate;

import static java.util.Objects.requireNonNull;

public class MongoAttributesImplementationEditor {

    public void edit() {
        compilationUnitEditor.addImportFirst(Version.class.getCanonicalName());
        compilationUnitEditor.addImportFirst(Id.class.getCanonicalName());

        var typeEditor = compilationUnitEditor.typeDeclaration();

        var identifierField = typeEditor.field(AggregateAttributesImplementationEditor.IDENTIFIER_FIELD_NAME).get(0);
        identifierField.modifiers().markerAnnotation(Id.class);

        var versionField = typeEditor.field(AggregateAttributesImplementationEditor.VERSION_FIELD_NAME).get(0);
        versionField.modifiers().markerAnnotation(Version.class);
        versionField.modifiers().removeAnnotations(SuppressWarnings.class);

        compilationUnitEditor.flush();
    }

    private Aggregate aggregate;

    public static class Builder {

        private MongoAttributesImplementationEditor editor = new MongoAttributesImplementationEditor();

        public MongoAttributesImplementationEditor build() {
            requireNonNull(editor.compilationUnitEditor);
            requireNonNull(editor.aggregate);
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

    private MongoAttributesImplementationEditor() {

    }

    private ComilationUnitEditor compilationUnitEditor;
}
