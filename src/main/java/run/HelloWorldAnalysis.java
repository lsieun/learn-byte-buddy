package run;


import lsieun.buddy.description.DescriptionForMethod;
import lsieun.utils.MethodUtils;
import net.bytebuddy.description.method.MethodDescription;

import java.lang.invoke.LambdaMetafactory;
import java.lang.reflect.Method;

public class HelloWorldAnalysis {
    public static void main(String[] args) {
        Method method = MethodUtils.findMethod(LambdaMetafactory.class, "metafactory");
        if (method == null) return;

        MethodDescription.ForLoadedMethod methodDesc = new MethodDescription.ForLoadedMethod(method);
        DescriptionForMethod.print(methodDesc);
    }
}
