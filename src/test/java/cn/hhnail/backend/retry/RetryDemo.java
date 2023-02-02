package cn.hhnail.backend.retry;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author r221587
 * @version 1.0
 * @description: 请求重试
 * @date 2023/2/2 14:41
 */
public class RetryDemo {

    @Test
    public void test() throws Exception {
        //定义重试机制
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                //retryIf 重试条件
                .retryIfException()
                .retryIfRuntimeException()
                .retryIfExceptionOfType(Exception.class)
                .retryIfException(Predicates.equalTo(new Exception()))
                .retryIfResult(Predicates.equalTo(false))

                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))

                //停止策略 : 尝试请求6次
                .withStopStrategy(StopStrategies.stopAfterAttempt(6))

                //时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))

                .build();

        //定义请求实现
        Callable<Boolean> callable = new Callable<Boolean>() {
            int times = 1;

            @Override
            public Boolean call() throws Exception {
                System.out.println("call times=" + times);
                times++;

                if (times == 2) {
                    System.out.println("空指针异常");
                    throw new NullPointerException();
                } else if (times == 3) {
                    System.out.println("异常");
                    throw new Exception();
                } else if (times == 4) {
                    System.out.println("运行时异常");
                    throw new RuntimeException();
                } else if (times == 5) {
                    System.out.println("不符合预期");
                    return false;
                } else {
                    System.out.println("执行成功");
                    return true;
                }

            }
        };
        //利用重试器调用请求
        retryer.call(callable);
    }
}
