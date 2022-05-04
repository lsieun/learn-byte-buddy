package lsieun.buddy.delegation;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class HardWorker {
    @RuntimeType
    public static Object doWork(@SuperCall Callable<?> targetCode, @Origin String origin) throws Exception {
        // 1. 记录开始时间
        long startTime = System.currentTimeMillis();
        System.out.println(">>> >>> >>> >>> >>> >>> >>> >>> >>> Method Enter");
        System.out.println("@Origin: " + origin);

        // 2. 执行原来的方法
        Object result = targetCode.call();
        System.out.println("Result: " + result);

        // 3. 记录结束时间
        System.out.println("<<< <<< <<< <<< <<< <<< <<< <<< <<< Method Exit");
        long endTime = System.currentTimeMillis();

        // 4. 输出运行时间
        long diff = endTime - startTime;
        String message = String.format("Execution Time: %s", diff);
        System.out.println(message);

        // 5. 返回结果
        return result;
    }
}
