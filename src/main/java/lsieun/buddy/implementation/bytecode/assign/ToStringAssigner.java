package lsieun.buddy.implementation.bytecode.assign;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.matcher.ElementMatchers;

public enum ToStringAssigner implements Assigner {
    INSTANCE; // singleton

    @Override
    public StackManipulation assign(TypeDescription.Generic source, TypeDescription.Generic target, Typing typing) {
        if (!source.isPrimitive() && target.represents(String.class)) {
            MethodDescription toStringMethod = new TypeDescription.ForLoadedType(Object.class)
                    .getDeclaredMethods()
                    .filter(ElementMatchers.named("toString"))
                    .getOnly();
            TypeDescription sourceType = source.asErasure();
            return MethodInvocation.invoke(toStringMethod).virtual(sourceType);
        }
        else {
            return StackManipulation.Illegal.INSTANCE;
        }
    }
}
