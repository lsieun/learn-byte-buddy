package lsieun.buddy.description;

import lsieun.cst.FormatConst;
import net.bytebuddy.description.method.MethodDescription;

import java.util.Formatter;

public class DescriptionForCommon {
    public static void printImplementationClass(MethodDescription methodDescription) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Implementation%n");

        String className = methodDescription.getClass().getName();
        fm.format(FormatConst.STANDARD_FORMAT_NEW_LINE, "ClassName", className);
        System.out.println(sb);
    }
}
