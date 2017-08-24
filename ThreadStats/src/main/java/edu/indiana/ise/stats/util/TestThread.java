package edu.indiana.ise.stats.util;

/**
 * Created by vibhatha on 8/1/17.
 */
class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    RunnableDemo( String name, String filename) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // Let the thread sleep for a while.
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}

public class TestThread {

    public static void main(String args[]) {
        RunnableDemo R1 = new RunnableDemo( "Thread-1","data/file1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo( "Thread-2","data/file1");
        R2.start();
    }
}