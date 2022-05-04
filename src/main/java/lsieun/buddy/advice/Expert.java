package lsieun.buddy.advice;

import net.bytebuddy.asm.Advice;

public class Expert {
    @Advice.OnMethodEnter
    public static void methodEnter(@Advice.Local("startTime") long startTime) {
        startTime = System.currentTimeMillis();
        System.out.println(">>> >>> >>> >>> >>> >>> >>> >>> >>> Method Enter");
    }

    @Advice.OnMethodExit
    public static void methodExit(@Advice.Local("startTime") long startTime) {
        System.out.println("<<< <<< <<< <<< <<< <<< <<< <<< <<< Method Exit");
        long endTime = System.currentTimeMillis();

        long diff = endTime - startTime;
        String message = String.format("Execution Time: %s", diff);
        System.out.println(message);
    }
}
