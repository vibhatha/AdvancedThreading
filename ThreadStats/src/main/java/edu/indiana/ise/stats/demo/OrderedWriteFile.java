package edu.indiana.ise.stats.demo;

/**
 * Created by vibhatha on 8/1/17.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Multi thread to write file
 *
 * @author Administrator
 *
 */
public class OrderedWriteFile {

    public static void main(String[] args) {
        File file=new File("data/ThreadDemo3.txt");
        try {
            FileOutputStream out=new FileOutputStream(file, true);
            ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<String>();
            for(int i=0;i<10;i++){
                new Thread(new MyThread(queue,"Thread " +i+"\n ")).start(); // multi thread into the queue data
            }
            new Thread(new DealFile(out,queue)).start();//The thread of monitoring, continuously from the queue read and write data to a file
            try {
                Thread.sleep(1);
                if(!Thread.currentThread().isAlive()){
                    System.out.println("The thread has finished");
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

/**
 * Will be written to the file data into the queue
 *
 * @author Administrator
 *
 */
class MyThread implements Runnable {
    private ConcurrentLinkedQueue<String> queue;
    private String contents;

    public MyThread() {
    }

    public MyThread(ConcurrentLinkedQueue<String> queue, String contents) {
        this.queue = queue;
        this.contents = contents;
    }

    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        queue.add(contents);
    }
}
/**
 * Write data to a file in the queue
 * @author Administrator
 *
 */
class DealFile implements Runnable {
    private FileOutputStream out;
    private ConcurrentLinkedQueue<String> queue;

    public DealFile() {
    }

    public DealFile(FileOutputStream out, ConcurrentLinkedQueue<String> queue) {
        this.out = out;
        this.queue = queue;
    }

    public void run() {
        synchronized (queue) {
            while (true) {
                if (!queue.isEmpty()) {
                    try {
                        out.write(queue.poll().getBytes("UTF-8"));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
