package lsieun.buddy.description;

import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationSource;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;

public class DescriptionForAnnotation {
    public static void printAnnotationSource(AnnotationSource annotationSource) {
        AnnotationList annotationList = annotationSource.getDeclaredAnnotations();
        printAnnotationList(annotationList);
    }

    public static void printAnnotationList(AnnotationList annotationList) {
        for (AnnotationDescription annotationDesc : annotationList) {
            printAnnotationDescription(annotationDesc);
        }
    }

    public static void printAnnotationDescription(AnnotationDescription annotationDesc) {
        TypeDescription annotationType = annotationDesc.getAnnotationType();
        System.out.println(annotationType);

        String format = "    %s = %s (%s)";
        MethodList<? extends MethodDescription> methodList = annotationType.getDeclaredMethods();
        for (MethodDescription method : methodList) {
            String name = method.getName();
            AnnotationValue<?, ?> annotationElementValue = annotationDesc.getValue(name);
            AnnotationValue.Sort sort = annotationElementValue.getSort();

            String message = String.format(format, name, annotationElementValue, sort);
            System.out.println(message);
        }
    }
}
