package lsieun.buddy.implementation.bind;

import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;

public enum StringValueBinder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<StringValue> {
    INSTANCE; // singleton

    @Override
    public Class<StringValue> getHandledType() {
        return StringValue.class;
    }

    @Override
    public MethodDelegationBinder.ParameterBinding<?> bind(
            AnnotationDescription.Loadable<StringValue> annotation,
            MethodDescription source,
            ParameterDescription target,
            Implementation.Target implementationTarget,
            Assigner assigner,
            Assigner.Typing typing) {
        if (!target.getType().asErasure().represents(String.class)) {
            throw new IllegalStateException(target + " makes illegal use of @StringValue");
        }

        StackManipulation constant = new TextConstant(annotation.load().value());
        return new MethodDelegationBinder.ParameterBinding.Anonymous(constant);
    }
}
