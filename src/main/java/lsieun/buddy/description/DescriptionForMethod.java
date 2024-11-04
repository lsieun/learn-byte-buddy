package lsieun.buddy.description;

import lsieun.annotation.ToDo;
import lsieun.cst.FormatConst;
import lsieun.box.BoxUtils;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.TypeVariableSource;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.*;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;

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

    public static void compare(MethodDescription... methodDescArray) {
        List<MethodDescription> methodList = Arrays.asList(methodDescArray);
        compare(methodList);
    }

    public static void compare(List<? extends MethodDescription> methodList) {
        int length = methodList.size();
        String[][] firstMatrix = new String[13][length + 1];
        firstMatrix[0][0] = "Method Info";
        firstMatrix[1][0] = "getDeclaringType()";
        firstMatrix[2][0] = "getActualModifiers()";
        firstMatrix[3][0] = "isTypeInitializer()";
        firstMatrix[4][0] = "isConstructor()";
        firstMatrix[5][0] = "isMethod()";
        firstMatrix[6][0] = "getParameters()";
        firstMatrix[7][0] = "getReturnType()";
        firstMatrix[8][0] = "getExceptionTypes()";
        firstMatrix[9][0] = "asSignatureToken()";
        firstMatrix[10][0] = "asTypeToken()";
        firstMatrix[11][0] = "isBridgeCompatible()";
        firstMatrix[12][0] = "getStackSize()";

        for (int i = 0; i < length; i++) {
            MethodDescription methodDesc = methodList.get(i);

            int colIndex = i + 1;
            firstMatrix[0][colIndex] = methodDesc.getInternalName();
            firstMatrix[1][colIndex] = methodDesc.getDeclaringType().getTypeName();
            firstMatrix[2][colIndex] = Modifier.toString(methodDesc.getActualModifiers());
            firstMatrix[3][colIndex] = String.valueOf(methodDesc.isTypeInitializer());
            firstMatrix[4][colIndex] = String.valueOf(methodDesc.isConstructor());
            firstMatrix[5][colIndex] = String.valueOf(methodDesc.isMethod());
            firstMatrix[6][colIndex] = formatParamList(methodDesc.getParameters());
            firstMatrix[7][colIndex] = methodDesc.getReturnType().toString();
            firstMatrix[8][colIndex] = format(methodDesc.getExceptionTypes());
            firstMatrix[9][colIndex] =  methodDesc.asSignatureToken().toString();
            firstMatrix[10][colIndex] =methodDesc.asTypeToken().toString();
            firstMatrix[11][colIndex] =String.valueOf(methodDesc.isBridgeCompatible(
                    new MethodDescription.TypeToken(
                            TypeDescription.ForLoadedType.of(void.class),
                            Collections.emptyList()
                    )
            ));
            firstMatrix[12][colIndex] =String.valueOf(methodDesc.getStackSize());
        }

        BoxUtils.print(firstMatrix);

        String[][] secondMatrix = new String[4][length + 1];
        secondMatrix[0][0] = "Method Invoke";
        secondMatrix[1][0] = "getReceiverType()";
        secondMatrix[2][0] = "isInvokableOn()";
        secondMatrix[3][0] = "isSpecializableFor()";

        for (int i = 0; i < length; i++) {
            MethodDescription methodDesc = methodList.get(i);

            int colIndex = i + 1;
            secondMatrix[0][colIndex] = methodDesc.getInternalName();
            secondMatrix[1][colIndex] = String.valueOf(methodDesc.getReceiverType());
            secondMatrix[2][colIndex] = String.valueOf(methodDesc.isInvokableOn(methodDesc.getDeclaringType().asErasure()));
            secondMatrix[3][colIndex] = String.valueOf(methodDesc.isSpecializableFor(methodDesc.getDeclaringType().asErasure()));
        }

        BoxUtils.print(secondMatrix);

        String[][] thirdMatrix = new String[7][length + 1];
        thirdMatrix[0][0] = "";
        thirdMatrix[1][0] = "isVirtual()";
        thirdMatrix[2][0] = "isDefaultMethod()";
        thirdMatrix[3][0] = "isDefaultValue()";
        thirdMatrix[4][0] = "getDefaultValue()";
        thirdMatrix[5][0] = "isInvokeBootstrap()";
        thirdMatrix[6][0] = "isConstantBootstrap()";


        for (int i = 0; i < length; i++) {
            MethodDescription methodDesc = methodList.get(i);

            int colIndex = i + 1;
            thirdMatrix[0][colIndex] = methodDesc.getInternalName();
            thirdMatrix[1][colIndex] = String.valueOf(methodDesc.isVirtual());
            thirdMatrix[2][colIndex] = String.valueOf(methodDesc.isDefaultMethod());
            thirdMatrix[3][colIndex] = String.valueOf(methodDesc.isDefaultValue());
            thirdMatrix[4][colIndex] = String.valueOf(methodDesc.getDefaultValue());
            thirdMatrix[5][colIndex] = String.valueOf(methodDesc.isInvokeBootstrap());
            thirdMatrix[6][colIndex] = String.valueOf(methodDesc.isConstantBootstrap());
        }

        BoxUtils.print(thirdMatrix);
    }

    private static String format(List<? extends NamedElement> nameList) {
        int size = nameList.size();
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            NamedElement type = nameList.get(i);
            String actualName = type.getActualName();
            int index = actualName.lastIndexOf(".");
            if (index >= 0) {
                array[i] = actualName.substring(index + 1);
            }
            else {
                array[i] = actualName;
            }
        }
        return Arrays.toString(array);
    }

    private static String formatParamList(ParameterList<?> parameterList) {
        List<TypeDescription.Generic> list = parameterList.stream().map(ParameterDescription::getType).toList();
        return format(list);
    }
}
