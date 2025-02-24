package sample.delegation;

import sample.hierarchy.IDog;

import net.bytebuddy.implementation.bind.annotation.DefaultCallHandle;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.invoke.MethodHandle;

public class HardWorker {
    @RuntimeType
    public static Object doWork(@DefaultCallHandle(targetType = IDog.class) MethodHandle methodHandle) {
        return String.format("@DefaultCallHandle MethodHandle %s", methodHandle.toString());
    }
}
