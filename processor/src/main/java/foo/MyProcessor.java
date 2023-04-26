package foo;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_17)
@SupportedAnnotationTypes("foo.MyAnnotation")
public class MyProcessor extends AbstractProcessor {

    private int procRun = 0;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager msg = processingEnv.getMessager();
        msg.printMessage(Diagnostic.Kind.NOTE, String.format("[%d]: Hello Info", procRun));
        msg.printMessage(Diagnostic.Kind.WARNING, String.format("[%d]: Hello Warning", procRun));
        procRun++;
        return false;
    }

}
