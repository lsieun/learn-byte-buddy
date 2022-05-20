package lsieun.buddy.asm.annotation;

import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;

import java.lang.annotation.Annotation;

public class RemoveFieldAnnotation implements AsmVisitorWrapper.ForDeclaredFields.FieldVisitorWrapper {
    private final String annotationDesc;

    public RemoveFieldAnnotation(Class<? extends Annotation> annotationClass) {
        Type type = Type.getType(annotationClass);
        this.annotationDesc = type.getDescriptor();
    }

    public RemoveFieldAnnotation(String className) {
        String internalName = className.replace(".", "/");
        Type type = Type.getObjectType(internalName);
        this.annotationDesc = type.getDescriptor();
    }

    @Override
    public FieldVisitor wrap(TypeDescription instrumentedType,
                             FieldDescription.InDefinedShape fieldDescription,
                             FieldVisitor fieldVisitor) {
        return new FieldAnnotationRemovalAdapter(Opcodes.ASM9, fieldVisitor);
    }

    private class FieldAnnotationRemovalAdapter extends FieldVisitor {
        public FieldAnnotationRemovalAdapter(int api, FieldVisitor fieldVisitor) {
            super(api, fieldVisitor);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            if (descriptor.equals(annotationDesc)) {
                return null;
            }
            return super.visitAnnotation(descriptor, visible);
        }
    }
}
