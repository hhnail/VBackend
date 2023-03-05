package cn.hhnail.backend.baguwen.q1;

/**
 * @author Hhnail
 * @version 1.0
 * @description: Java启动线程的方式1——实现Runnable
 * @date 2023/3/5 8:40
 */
public class RunnableTest implements Runnable {

    public static void main(String[] args) {
        // 实现Runnable方式1
        Thread thread = new Thread(new RunnableTest());
        thread.start();

        // 实现Runnable。简化为匿名内部类
        new Thread(new RunnableTest() {
            @Override
            public void run() {
                System.out.println("running2");
            }
        }).start();

        // 实现Runnable。Runable是函数式接口，匿名内部类简写为lambda表达式
        new Thread(() -> System.out.println("running3")).start();


    }

    @Override
    public void run() {
        System.out.println("running1");
    }
}
