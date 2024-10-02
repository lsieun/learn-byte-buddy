package lsieun.utils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * from lsieun-utils
 */
public class ValueUtils {
    public static String formatValue(Object val) {
        if (val == null) {
            return "null";
        }
        else if (val instanceof Type) {
            return formatType((Type) val);
        }
        else if (val instanceof Member) {
            return formatMember((Member) val);
        }
        else if (val instanceof AnnotatedType) {
            return formatAnnotatedType((AnnotatedType) val);
        }
        else if (val instanceof Optional<?>) {
            boolean isPresent = ((Optional<?>) val).isPresent();
            if (!isPresent) {
                return "";
            }

            Object obj = ((Optional<?>) val).get();
            return formatValue(obj);
        }
        else {
            return formatObject(val);
        }
    }

    private static String formatAnnotatedType(AnnotatedType annotatedType) {
        Type type = annotatedType.getType();
        return formatType(type);
    }

    private static String formatMember(Member member) {
        String name = member.getName();
        return member instanceof Executable ? name + "()" : name;
    }

    private static String formatType(Type type) {
        if (type instanceof Class<?>) {
            return formatClass((Class<?>) type);
        }
        else if (type instanceof GenericArrayType) {
            return formatGenericArrayType((GenericArrayType) type);
        }
        else if (type instanceof ParameterizedType) {
            return formatParameterizedType((ParameterizedType) type);
        }
        else if (type instanceof WildcardType) {
            return formatWildcardType((WildcardType) type);
        }
        else if (type instanceof TypeVariable<?>) {
            return formatTypeVariable((TypeVariable<?>) type);
        }
        else {
            return type.getTypeName();
        }
    }

    private static String formatClass(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            return clazz.getSimpleName();
        }
        else if (clazz.isArray()) {
            Class<?> componentType = clazz.getComponentType();
            return formatValue(componentType) + "[]";
        }
        else {
            Package pkg = clazz.getPackage();
            if (pkg == null) {
                return clazz.getSimpleName();
            }
            String packageName = pkg.getName();

            String clazzName = clazz.getName();
            String str = clazzName.substring(packageName.length() + 1);
            if (clazz.isMemberClass()) {
                return str.replace("$", ".");
            }
            return str;
        }
    }

    private static String formatGenericArrayType(GenericArrayType genericArrayType) {
        return formatValue(genericArrayType.getGenericComponentType()) + "[]";
    }

    private static String formatTypeVariable(TypeVariable<?> typeVar) {
        Type[] bounds = typeVar.getBounds();
        if (bounds.length == 1 && bounds[0].equals(Object.class)) {
            return typeVar.getName();
        }
        else {
            return typeVar.getName() + " extends " +
                    Arrays.stream(bounds)
                            .map(ValueUtils::formatValue)
                            .collect(Collectors.joining(" & "));
        }
    }

    private static String formatParameterizedType(ParameterizedType parameterizedType) {
        Type rawType = parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

        return formatValue(rawType) + "<" +
                Arrays.stream(actualTypeArguments)
                        .map(ValueUtils::formatValue)
                        .collect(Collectors.joining(" & "))
                + ">";
    }

    private static String formatWildcardType(WildcardType wildcardType) {
        Type[] upperBounds = wildcardType.getUpperBounds();
        Type[] lowerBounds = wildcardType.getLowerBounds();

        int upperLength = upperBounds.length;
        int lowerLength = lowerBounds.length;

        if (upperLength == 1 && upperBounds[0].equals(Object.class) && lowerLength == 0) {
            return "?";
        }
        else if (lowerLength > 0) {
            return "? super " +
                    Arrays.stream(lowerBounds)
                            .map(ValueUtils::formatValue)
                            .collect(Collectors.joining(" & "));
        }
        else if (upperLength > 0) {
            return "? extends " +
                    Arrays.stream(upperBounds)
                            .map(ValueUtils::formatValue)
                            .collect(Collectors.joining(" & "));
        }
        else {

            String msg = String.format("Something is Wrong: upperBounds = %s, lowerBounds = %s",
                    Arrays.toString(upperBounds),
                    Arrays.toString(lowerBounds)
            );
            throw new RuntimeException(msg);
        }
    }

    private static String formatObject(Object obj) {
        Class<?> clazz = obj.getClass();
        if (clazz.isPrimitive()) {
            return String.valueOf(obj);
        }
        else if (clazz.isArray()) {
            Object[] array = (Object[]) obj;
            int length = array.length;
            if (length == 0) {
                return "[]";
            }

            String[] strArray = new String[length];
            for (int i = 0; i < length; i++) {
                String str = formatValue(array[i]);
                strArray[i] = str;
            }

            return Arrays.toString(strArray);
        }
        else {
            return obj.toString();
        }
    }
}
