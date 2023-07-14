package lsieun.buddy.description;

import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;

import java.util.Formatter;

public class DescriptionForType {
    public static void print(TypeDescription typeDescription) {
        printMain(typeDescription);
        printAnnotation(typeDescription);
        printField(typeDescription);
        printMethod(typeDescription);
    }

    public static void printMain(TypeDescription typeDescription) {
        String name = typeDescription.getName();
        String internalName = typeDescription.getInternalName();
        String typeName = typeDescription.getTypeName();
        String actualName = typeDescription.getActualName();
        String canonicalName = typeDescription.getCanonicalName();
        String simpleName = typeDescription.getSimpleName();
        String longSimpleName = typeDescription.getLongSimpleName();

        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Meta%n");
        fm.format("    Name          : %s%n", name);
        fm.format("    ActualName    : %s%n", actualName);
        fm.format("    CanonicalName : %s%n", canonicalName);
        fm.format("    TypeName      : %s%n", typeName);
        fm.format("    InternalName  : %s%n", internalName);
        fm.format("    SimpleName    : %s%n", simpleName);
        fm.format("    LongSimpleName: %s%n", longSimpleName);

        System.out.println(sb);
    }

    public static void printAnnotation(TypeDescription typeDescription) {
        AnnotationList declaredAnnotations = typeDescription.getDeclaredAnnotations();
        AnnotationList inheritedAnnotations = typeDescription.getInheritedAnnotations();
        int size1 = declaredAnnotations.size();
        int size2 = inheritedAnnotations.size();

        if (size1 == 0 && size2 == 0) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Annotation%n");
        if (size1 > 0) {
            fm.format("    DeclaredAnnotations%n");
            for (AnnotationDescription anno : declaredAnnotations) {
                fm.format("        %s%n", anno.getAnnotationType().getSimpleName());
            }
        }

        System.out.println(sb);
    }

    public static void printField(TypeDescription typeDescription) {
        FieldList<FieldDescription.InDefinedShape> declaredFields = typeDescription.getDeclaredFields();
        int size = declaredFields.size();

        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Fields: %d%n", size);

        for (FieldDescription fieldDesc : declaredFields) {
            String name = fieldDesc.getInternalName();
            String descriptor = fieldDesc.getDescriptor();
            fm.format("    %-10s: %s%n", name, descriptor);
        }

        System.out.println(sb);
    }

    public static void printMethod(TypeDescription typeDescription) {
        MethodList<MethodDescription.InDefinedShape> declaredMethods = typeDescription.getDeclaredMethods();
        int size = declaredMethods.size();

        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Methods: %d%n", size);

        for (MethodDescription methodDesc : declaredMethods) {
            String name = methodDesc.getInternalName();
            String descriptor = methodDesc.getDescriptor();
            fm.format("    %-10s: %s%n", name, descriptor);
        }

        System.out.println(sb);
    }
}
