package lsieun.buddy.advice;

import net.bytebuddy.asm.Advice;

public class Expert {
    @Advice.OnMethodEnter
    public static void methodAbc(@Advice.Origin("#t") String methodDeclaringType,
                                 @Advice.Origin("#m") String methodName,
                                 @Advice.Local("startTime") long startTime) {
        // (1) method enter
        String methodEnterMsg = String.format(">>> >>> >>> Method Enter: %s.%s()",
                methodDeclaringType, methodName);
        System.out.println(methodEnterMsg);

        // (2) timestamp
        startTime = System.currentTimeMillis();
    }

    @Advice.OnMethodExit
    public static void methodXyz(@Advice.Origin("#t") String methodDeclaringType,
                                 @Advice.Origin("#m") String methodName,
                                 @Advice.Local("startTime") long startTime) {
        // (1) timestamp
        long stopTime = System.currentTimeMillis();

        // (2) elapsed time
        long elapsedTime = stopTime - startTime;
        String elapsedTimeMsg = String.format("Execution Time: %s", elapsedTime);
        System.out.println(elapsedTimeMsg);

        // (3) method exit
        String methodExitMsg = String.format("<<< <<< <<< Method  Exit: %s.%s()",
                methodDeclaringType, methodName);
        System.out.println(methodExitMsg);
    }
}
