package lsieun.buddy.implementation.bytecode;

import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.utility.JavaConstant;
import org.objectweb.asm.MethodVisitor;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SysOutPrint implements ByteCodeAppender {

    private final Object paramValue;
    private final Field outField;
    private final Method printMethod;

    private SysOutPrint(Class<?> paramType, Object paramValue) throws NoSuchFieldException, NoSuchMethodException {
        this.paramValue = paramValue;

        this.outField = System.class.getField("out");
        this.printMethod = PrintStream.class.getMethod("println", paramType);
    }

    @Override
    public Size apply(MethodVisitor methodVisitor,
                      Implementation.Context implementationContext,
                      MethodDescription instrumentedMethod) {

        FieldDescription fieldDesc = new FieldDescription.ForLoadedField(outField);
        JavaConstant cst = JavaConstant.Simple.ofLoaded(paramValue);
        MethodDescription methodDesc = new MethodDescription.ForLoadedMethod(printMethod);

        StackManipulation stackManipulation = new StackManipulation.Compound(
                FieldAccess.forField(fieldDesc).read(),    // getstatic java/lang/System.out
                new JavaConstantValue(cst),                // ldc "Hello Type Initializer"
                MethodInvocation.invoke(methodDesc)        // invokevirtual java/io/PrintStream.println
        );
        StackManipulation.Size size = stackManipulation.apply(methodVisitor, implementationContext);

        return new Size(size.getMaximalSize(), instrumentedMethod.getStackSize());
    }

    public static SysOutPrint of(String str) {
        return of(String.class, str);
    }

    public static SysOutPrint of(Class<?> clazz, Object obj) {
        try {
            return new SysOutPrint(clazz, obj);
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
