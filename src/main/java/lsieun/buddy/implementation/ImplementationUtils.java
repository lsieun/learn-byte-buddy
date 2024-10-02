package lsieun.buddy.implementation;

import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.PrintStream;

public class ImplementationUtils {
    public static MethodCall print(String str) {
//        MethodDescription methodDesc = new MethodDescription.Latent(
//                TypeDescription.ForLoadedType.of(PrintStream.class),
//                new MethodDescription.Token(
//                        "println",
//                        Opcodes.ACC_PUBLIC,
//                        TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(void.class),
//                        List.of(
//                                TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(String.class)
//                        )
//                )
//        );

        MethodDescription methodDesc = TypeDescription.ForLoadedType.of(PrintStream.class)
                .getDeclaredMethods().filter(
                        ElementMatchers.named("println").and(
                                ElementMatchers.takesArgument(0, String.class)
                        )
                ).getOnly();


//        FieldDescription fieldDesc = new FieldDescription.Latent(
//                TypeDescription.ForLoadedType.of(System.class),
//                new FieldDescription.Token(
//                        "out",
//                        Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL,
//                        TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(PrintStream.class)
//                )
//        );

        FieldDescription fieldDesc = TypeDescription.ForLoadedType.of(System.class)
                .getDeclaredFields().filter(ElementMatchers.named("out")).getOnly();

        return MethodCall.invoke(methodDesc)
                .onField(fieldDesc)
                .with(str);
    }
}
