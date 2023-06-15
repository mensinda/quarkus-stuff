package foo;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_17)
@SupportedAnnotationTypes("foo.MyAnnotation")
public class MyProcessor extends AbstractProcessor {

    private int procRun = 0;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            generate(annotations, roundEnv);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private void generate(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) throws IOException {
        Messager msg = processingEnv.getMessager();
        msg.printMessage(Diagnostic.Kind.NOTE, String.format("[%d]: Hello Info", procRun));
        msg.printMessage(Diagnostic.Kind.WARNING, String.format("[%d]: Hello Warning", procRun));
        procRun++;

        if (procRun > 1) {
            return;
        }

        JavaFileObject file = processingEnv.getFiler().createSourceFile("bar.MyGeneratedClass");
        try (Writer writer = file.openWriter()) {
            writer.write("""
                    package bar;

                    public class MyGeneratedClass {
                    }
                    """);
        }
    }

}
