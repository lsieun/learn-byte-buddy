package lsieun.utils;

import lsieun.buddy.description.ClassWithMemberName;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemberFindUtils {
    public static Method findOneMethodWithoutArgs(Class<?> clazz, String methodName) throws NoSuchMethodException {
        return clazz.getMethod(methodName);
    }

    public static List<Method> findMethodList(Class<?> clazz, String methodName) {
        List<Method> resultList = new ArrayList<>();

        Method[] methodArray = clazz.getDeclaredMethods();
        Method targetMethod = null;
        for (Method m : methodArray) {
            if (m.getName().equals(methodName)) {
                targetMethod = m;
                break;
            }
        }
        return resultList;
    }

    public static List<FieldDescription> findFieldDescList(ClassWithMemberName... array) {
        List<ClassWithMemberName> list = Arrays.asList(array);
        return findFieldDescList(list);
    }

    public static List<FieldDescription> findFieldDescList(List<ClassWithMemberName> list) {
        List<FieldDescription> resultList = new ArrayList<>();

        for (ClassWithMemberName item : list) {
            Class<?> clazz = item.getClazz();
            List<String> nameList = item.getNameList();

            TypeDescription typeDesc = TypeDescription.ForLoadedType.of(clazz);
            FieldList<? extends FieldDescription> fieldList = typeDesc.getDeclaredFields();
            for (String name : nameList) {
                FieldList<? extends FieldDescription> filterList = fieldList.filter(ElementMatchers.named(name));
                resultList.addAll(filterList);
            }
        }

        return resultList;
    }

    public static List<MethodDescription> findMethodDescList(ClassWithMemberName... array) {
        List<ClassWithMemberName> list = Arrays.asList(array);
        return findMethodDescList(list);
    }

    public static List<MethodDescription> findMethodDescList(List<ClassWithMemberName> list) {
        List<MethodDescription> resultList = new ArrayList<>();
        for (ClassWithMemberName item : list) {
            Class<?> clazz = item.getClazz();
            List<String> nameList = item.getNameList();

            TypeDescription typeDesc = TypeDescription.ForLoadedType.of(clazz);
            MethodList<? extends MethodDescription> methodList = typeDesc.getDeclaredMethods();
            for (String name : nameList) {
                if (MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME.equals(name)) {
                    MethodDescription typeInitializer = new MethodDescription.Latent.TypeInitializer(typeDesc);
                    resultList.add(typeInitializer);
                }
                else if (MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(name)) {
                    MethodList<? extends MethodDescription> filterList = methodList.filter(ElementMatchers.isConstructor());
                    resultList.addAll(filterList);
                }
                else {
                    MethodList<? extends MethodDescription> filterList = methodList.filter(ElementMatchers.named(name));
                    resultList.addAll(filterList);
                }
            }
        }
        return resultList;
    }
}
