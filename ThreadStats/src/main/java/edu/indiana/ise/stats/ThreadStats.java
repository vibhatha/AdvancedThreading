package edu.indiana.ise.stats;

/**
 * Created by vibhatha on 8/1/17.
 */
public class ThreadStats {

    public static void main(String[] args) {

        Thread t1 = Thread.currentThread();
        long threadId = t1.getId();
        String threadName = t1.getName();
        Thread.State state = t1.getState();

        System.out.println("Thread ID : "+threadId);
        System.out.println("Thread Name : "+threadName);
        System.out.println("Thread State : "+state);

    }
}
