package lsieun.buddy.asm.annotation;

import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.pool.TypePool;

import java.lang.annotation.Annotation;

public class RemoveMethodAnnotation implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
    private final String annotationDesc;

    public RemoveMethodAnnotation(Class<? extends Annotation> annotationClass) {
        Type type = Type.getType(annotationClass);
        this.annotationDesc = type.getDescriptor();
    }

    public RemoveMethodAnnotation(String className) {
        String internalName = className.replace(".", "/");
        Type type = Type.getObjectType(internalName);
        this.annotationDesc = type.getDescriptor();
    }

    @Override
    public MethodVisitor wrap(TypeDescription instrumentedType,
                              MethodDescription instrumentedMethod,
                              MethodVisitor methodVisitor,
                              Implementation.Context implementationContext,
                              TypePool typePool,
                              int writerFlags,
                              int readerFlags) {
        return new MethodAnnotationRemovalAdapter(Opcodes.ASM9, methodVisitor);
    }

    private class MethodAnnotationRemovalAdapter extends MethodVisitor {
        public MethodAnnotationRemovalAdapter(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
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
