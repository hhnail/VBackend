package cn.hhnail.backend.baguwen.q1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Hhnail
 * @version 1.0
 * @description: Java启动线程的方式1——实现Callable
 * @date 2023/3/5 9:26
 */
public class CallableTest implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CallableTest());
        Thread thread = new Thread(futureTask);
        thread.start();
        String result = futureTask.get();
        System.out.println(result);
    }


    @Override
    public String call() throws Exception {
        return "I am running";
    }
}
