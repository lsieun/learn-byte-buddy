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

public class RemoveMethodParameterAnnotation implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
    private final int paramIndex;
    private final String annotationDesc;


    public RemoveMethodParameterAnnotation(int paramIndex, Class<? extends Annotation> annotationClass) {
        this.paramIndex = paramIndex;
        Type type = Type.getType(annotationClass);
        this.annotationDesc = type.getDescriptor();
    }

    public RemoveMethodParameterAnnotation(int paramIndex, String className) {
        this.paramIndex = paramIndex;
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
        return new MethodParameterAnnotationRemovalAdapter(Opcodes.ASM9, methodVisitor);
    }

    private class MethodParameterAnnotationRemovalAdapter extends MethodVisitor {
        public MethodParameterAnnotationRemovalAdapter(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
        }

        @Override
        public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
            if (paramIndex == parameter && descriptor.equals(annotationDesc)) {
                return null;
            }
            return super.visitParameterAnnotation(parameter, descriptor, visible);
        }
    }
}
