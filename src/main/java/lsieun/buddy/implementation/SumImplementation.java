package lsieun.buddy.implementation;

import lsieun.buddy.implementation.bytecode.SumMethod;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;

public enum SumImplementation implements Implementation {
    INSTANCE; // singleton

    @Override
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }

    @Override
    public ByteCodeAppender appender(Target implementationTarget) {
        return SumMethod.INSTANCE;
    }
}
