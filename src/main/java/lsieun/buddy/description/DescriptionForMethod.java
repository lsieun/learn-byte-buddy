package lsieun.buddy.description;

import lsieun.annotation.ToDo;
import lsieun.cst.FormatConst;
import net.bytebuddy.description.TypeVariableSource;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.*;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;

import java.lang.reflect.Modifier;
import java.util.Formatter;

@ToDo(items = {
        "print method annotation"
})
public class DescriptionForMethod {
    public static void print(MethodDescription desc) {
        DescriptionForCommon.printImplementationClass(desc);

        // type or class
        printDeclaringType(desc);

        // method
        printMethodHead(desc);
        printMethodBody(desc);

        // category and modifiers
        printMethodCategory(desc);
        printMethodModifier(desc);

        // bootstrap
        printMethodBootstrap(desc);

        // annotation
        printMethodAnnotation(desc);

        // generic - type variable
        printTypeVariable(desc);

        // unknown
        unknown(desc);
    }

    private static void printDeclaringType(MethodDescription desc) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Declaring Type%n");

        TypeDefinition declaringType = desc.getDeclaringType();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getDeclaringType()", declaringType);

        System.out.println(sb);
    }

    private static void printMethodHead(MethodDescription desc) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Method Head%n");

        String name = desc.getName();
        String actualName = desc.getActualName();
        String internalName = desc.getInternalName();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getName()", name);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getActualName()", actualName);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getInternalName()", internalName);
        fm.format("%n");

        // modifiers
        int modifiers = desc.getModifiers();
        int actualModifiers = desc.getActualModifiers();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getModifiers()", Modifier.toString(modifiers));
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getActualModifiers()", Modifier.toString(actualModifiers));
        fm.format("%n");

        // descriptor
        String descriptor = desc.getDescriptor();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getDescriptor()", descriptor);

        // parameter
        ParameterList<?> parameters = desc.getParameters();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getParameters()", parameters);

        // return
        TypeDescription.Generic returnType = desc.getReturnType();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getReturnType()", returnType);

        // exception
        TypeList.Generic exceptionTypes = desc.getExceptionTypes();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getExceptionTypes()", exceptionTypes);
        fm.format("%n");

        TypeDescription.Generic receiverType = desc.getReceiverType();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getReceiverType()", receiverType);

        MethodDescription.SignatureToken signatureToken = desc.asSignatureToken();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "asSignatureToken()", signatureToken);

        MethodDescription.TypeToken typeToken = desc.asTypeToken();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "asTypeToken()", typeToken);

        String genericString = desc.toGenericString();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "toGenericString()", genericString);

        System.out.println(sb);
    }

    private static void printMethodBody(MethodDescription desc) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Method Body%n");

        int stackSize = desc.getStackSize();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getStackSize()", stackSize);

        System.out.println(sb);
    }

    private static void printMethodCategory(MethodDescription desc) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Method Category%n");

        boolean isTypeInitializer = desc.isTypeInitializer();
        boolean isConstructor = desc.isConstructor();
        boolean isMethod = desc.isMethod();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isTypeInitializer()", isTypeInitializer);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isConstructor()", isConstructor);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isMethod()", isMethod);
        fm.format("%n");

        boolean isDefaultMethod = desc.isDefaultMethod();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isDefaultMethod()", isDefaultMethod);

        System.out.println(sb);
    }

    private static void printMethodModifier(MethodDescription desc) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Method Modifier%n");

        boolean isPublic = desc.isPublic();
        boolean isProtected = desc.isProtected();
        boolean isPrivate = desc.isPrivate();
        boolean isPackagePrivate = desc.isPackagePrivate();
        Visibility visibility = desc.getVisibility();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isPublic()", isPublic);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isProtected()", isProtected);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isPrivate()", isPrivate);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isPackagePrivate()", isPackagePrivate);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getVisibility()", visibility);
        fm.format("%n");

        boolean isStatic = desc.isStatic();
        Ownership ownership = desc.getOwnership();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isStatic()", isStatic);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getOwnership()", ownership);
        fm.format("%n");

        boolean isAbstract = desc.isAbstract();
        boolean isFinal = desc.isFinal();
        boolean isNative = desc.isNative();
        boolean isVirtual = desc.isVirtual();
        MethodManifestation methodManifestation = desc.getMethodManifestation();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isAbstract()", isAbstract);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isFinal()", isFinal);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isNative()", isNative);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isVirtual()", isVirtual);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getMethodManifestation()", methodManifestation);
        fm.format("%n");

        boolean isDeprecated = desc.isDeprecated();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isDeprecated()", isDeprecated);
        fm.format("%n");

        boolean isVarArgs = desc.isVarArgs();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isVarArgs()", isVarArgs);
        fm.format("%n");

        boolean bridge = desc.isBridge();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isBridge()", bridge);
        fm.format("%n");

        boolean strict = desc.isStrict();
        MethodStrictness methodStrictness = desc.getMethodStrictness();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isStrict()", strict);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getMethodStrictness()", methodStrictness);
        fm.format("%n");

        boolean synthetic = desc.isSynthetic();
        SyntheticState syntheticState = desc.getSyntheticState();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isSynthetic()", synthetic);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getSyntheticState()", syntheticState);
        fm.format("%n");

        boolean isSynchronized = desc.isSynchronized();
        SynchronizationState synchronizationState = desc.getSynchronizationState();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isSynchronized()", isSynchronized);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getSynchronizationState()", synchronizationState);

        System.out.println(sb);
    }

    private static void printMethodBootstrap(MethodDescription desc) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Method Bootstrap%n");

        boolean invokeBootstrap = desc.isInvokeBootstrap();
        boolean constantBootstrap = desc.isConstantBootstrap();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isInvokeBootstrap()", invokeBootstrap);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isConstantBootstrap()", constantBootstrap);

        System.out.println(sb);
    }

    private static void printMethodAnnotation(MethodDescription desc) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Method Annotation%n");

        boolean isDefaultValue = desc.isDefaultValue();
        AnnotationValue<?, ?> defaultValue = desc.getDefaultValue();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isDefaultValue()", isDefaultValue);
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getDefaultValue()", defaultValue);

        System.out.println(sb);
    }

    private static void printTypeVariable(MethodDescription desc) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Type Variable%n");

        TypeList.Generic typeVariables = desc.getTypeVariables();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getTypeVariables()", typeVariables);

        TypeVariableSource enclosingSource = desc.getEnclosingSource();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "getEnclosingSource()", enclosingSource);

        boolean isInfer = desc.isInferrable();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isInferrable()", isInfer);

        boolean isGenerified = desc.isGenerified();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "isGenerified()", isGenerified);

        System.out.println(sb);
    }

    private static void unknown(MethodDescription desc) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Unknown%n");

        MethodDescription.InDefinedShape definedShape = desc.asDefined();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "asDefined()", definedShape);

        System.out.println(sb);
    }
}
