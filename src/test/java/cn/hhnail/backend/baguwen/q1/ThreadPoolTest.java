package cn.hhnail.backend.baguwen.q1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hhnail
 * @version 1.0
 * @description: Java启动线程的方式1——从线程池获取，由线程池调用
 * @date 2023/3/5 9:32
 */
public class ThreadPoolTest implements Runnable {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ThreadPoolTest());
    }

    @Override
    public void run() {
        System.out.println("running by thread pool");
    }
}
