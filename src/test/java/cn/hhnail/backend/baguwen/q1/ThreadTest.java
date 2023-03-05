package cn.hhnail.backend.baguwen.q1;

/**
 * @author Hhnail
 * @version 1.0
 * @description: Java启动线程的方式1——继承Thread
 * @date 2023/3/5 8:38
 */
public class ThreadTest extends Thread {

    public static void main(String[] args) {
        new ThreadTest().start();
    }

    @Override
    public void run() {
        System.out.println("running!");
    }


}
