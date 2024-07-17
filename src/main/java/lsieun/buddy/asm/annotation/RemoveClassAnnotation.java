package lsieun.buddy.asm.annotation;

import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.pool.TypePool;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.lang.annotation.Annotation;

public class RemoveClassAnnotation extends AsmVisitorWrapper.AbstractBase {
    private final String annotationDesc;

    public RemoveClassAnnotation(Class<? extends Annotation> targetAnnotationClass) {
        Type type = Type.getType(targetAnnotationClass);
        this.annotationDesc = type.getDescriptor();
    }

    public RemoveClassAnnotation(String className) {
        String internalName = className.replace(".", "/");
        Type type = Type.getObjectType(internalName);
        this.annotationDesc = type.getDescriptor();
    }

    @Override
    public ClassVisitor wrap(TypeDescription instrumentedType,
                             ClassVisitor classVisitor,
                             Implementation.Context implementationContext,
                             TypePool typePool,
                             FieldList<FieldDescription.InDefinedShape> fields,
                             MethodList<?> methods,
                             int writerFlags,
                             int readerFlags) {
        return new ClassAnnotationRemovalVisitor(Opcodes.ASM9, classVisitor);
    }

    private class ClassAnnotationRemovalVisitor extends ClassVisitor {
        public ClassAnnotationRemovalVisitor(int api, ClassVisitor classVisitor) {
            super(api, classVisitor);
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
