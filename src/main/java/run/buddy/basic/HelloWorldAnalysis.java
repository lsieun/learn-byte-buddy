package run.buddy.basic;


import lsieun.buddy.description.DescriptionForMethod;
import lsieun.utils.MemberFindUtils;
import net.bytebuddy.description.method.MethodDescription;

import java.lang.invoke.LambdaMetafactory;
import java.lang.reflect.Method;

public class HelloWorldAnalysis {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = MemberFindUtils.findOneMethodWithoutArgs(LambdaMetafactory.class, "metafactory");

        MethodDescription.ForLoadedMethod methodDesc = new MethodDescription.ForLoadedMethod(method);
        DescriptionForMethod.print(methodDesc);
    }
}
