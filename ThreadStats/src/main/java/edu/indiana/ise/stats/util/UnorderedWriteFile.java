package edu.indiana.ise.stats.util;

import java.io.IOException;

/**
 * Created by vibhatha on 8/1/17.
 */
class RunnableWrite implements Runnable {
    private Thread t;
    private String threadName;
    private String filename;

    public RunnableWrite(Thread t, String threadName, String filename) {
        this.t = t;
        this.threadName = threadName;
        this.filename = filename;
    }

    RunnableWrite(String name, String filename) {
        threadName = name;
        this.filename = filename;
        System.out.println("Creating " +  threadName );
    }

    public void run() {
        System.out.println("Running " +  threadName );

        try {
            //WriteFile writeFile = new WriteFile();
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // Let the thread sleep for a while.
                WriteFileSingleton.getInstance().writeToFile(this.filename, threadName+" : "+i+" \n");
                //WriteFile.createFileUsingFileClass(this.filename,threadName+" : "+(i));

                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        } catch (IOException e) {
            e.printStackTrace();
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

public class UnorderedWriteFile {

    public static void main(String args[]) {
       /* RunnableWrite R1 = new RunnableWrite( "Thread-1","data/file1");
        R1.start();

       RunnableWrite R2 = new RunnableWrite( "Thread-2","data/file1");
       R2.start();*/
       if(args.length!=2){
           System.out.println("Arguments : <parallelism> <file-path> : ( positive integers) (file location)");
       }
       else{
           int parallelism = Integer.parseInt(args[0]);
           String filepath = args[1];
           RunnableWrite rObjects [] = new RunnableWrite[parallelism];
           for (int i = 0; i < parallelism; i++) {
               rObjects[i] = new RunnableWrite("Thread "+i, filepath);
               rObjects[i].start();
           }
       }

    }
}
