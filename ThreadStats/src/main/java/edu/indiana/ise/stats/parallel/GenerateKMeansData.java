package edu.indiana.ise.stats.parallel;

import edu.indiana.ise.stats.util.WriteFileSingleton;

import java.io.IOException;
import java.util.Random;

/**
 * Created by vibhatha on 8/1/17.
 */
class RunnableWrite implements Runnable {
    private Thread t;
    private String threadName;
    private String filename;
    private int samples;
    private int features;

    public RunnableWrite(Thread t, String threadName, String filename) {
        this.t = t;
        this.threadName = threadName;
        this.filename = filename;
    }

    public RunnableWrite(String threadName, String filename, int samples, int features) {
        this.threadName = threadName;
        this.filename = filename;
        this.samples = samples;
        this.features = features;
        System.out.println("Creating :"+threadName);
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
            for (int i = 0; i < this.samples; i++) {
                String row ="";
                for (int j = 0; j < this.features; j++) {
                    row += new Random().nextDouble()+" ";
                }
                WriteFileSingleton.getInstance().writeToFile(this.filename, row+" \n");
                Thread.sleep(0);
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

    public void join() throws InterruptedException {
        if (t != null) t.join();
    }
}

public class GenerateKMeansData {

    public static void main(String args[]) throws InterruptedException {
        if(args.length!=4){
            System.out.println("Arguments : <parallelism> <file-path> <samples> <features>: ( positive integers) (file location) (positive integer) (positive integer)");
        }
        else{
            long start_time = System.currentTimeMillis();
            int parallelism = Integer.parseInt(args[0]);
            String filepath = args[1];
            int samples = Integer.parseInt(args[2]);
            int features = Integer.parseInt(args[3]);
            RunnableWrite rObjects [] = new RunnableWrite[parallelism];
            for (int i = 0; i < parallelism; i++) {
                rObjects[i] = new RunnableWrite("Thread "+i, filepath, samples, features);
                rObjects[i].start();
                //rObjects[i].join();
            }

            for (RunnableWrite rw :
                    rObjects) {
                rw.join();
            }
            long end_time = System.currentTimeMillis();
            System.out.println("Execution Time : "+(end_time-start_time)/1000.0+" s");

        }

    }

//129.515 s => 100K
}
