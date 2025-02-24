package run.buddy.print;

import lsieun.buddy.description.ClassWithMemberName;
import lsieun.buddy.description.DescriptionForMethod;
import lsieun.utils.MemberFindUtils;
import net.bytebuddy.description.method.MethodDescription;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.StringConcatFactory;
import java.lang.runtime.ObjectMethods;
import java.util.List;

// TODO: remove
public class PrintMethodInfo {
    public static void main(String[] args) {
        // 第 1 步，准备参数
        ClassWithMemberName[] array = {
                ClassWithMemberName.of(LambdaMetafactory.class, "metafactory", "altMetafactory"),
                ClassWithMemberName.of(StringConcatFactory.class, "makeConcat", "makeConcatWithConstants"),
                ClassWithMemberName.of(ObjectMethods.class, "bootstrap"),
        };

        // 第 2 步，查找方法
        List<MethodDescription> methodList = MemberFindUtils.findMethodDescList(array);

        // 第 3 步，进行比对
        DescriptionForMethod.compare(methodList);
    }
}
