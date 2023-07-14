package run;

import lsieun.utils.OutputUtils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.dynamic.DynamicType;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class HelloWorldGenerate {
    public static void main(String[] args) throws Exception {
        String className = "sample.HelloWorld";

        ByteBuddy byteBuddy = new ByteBuddy();
        DynamicType.Builder<? extends Annotation> builder = byteBuddy.makeAnnotation().name(className);

        AnnotationDescription retentionMetaAnnotation = AnnotationDescription.Builder.ofType(Retention.class)
                .define("value", RetentionPolicy.CLASS)
                .build();
        builder = builder.annotateType(retentionMetaAnnotation);

        DynamicType.Unloaded<? extends Annotation> unloadedType = builder.make();
        OutputUtils.save(unloadedType);
    }
}
