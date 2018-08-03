package edu.iu.dsc.tws.executor.api.shared.resource;

import edu.iu.dsc.tws.executor.api.shared.task.SinkTask;
import edu.iu.dsc.tws.executor.api.shared.task.SourceTask;

public class Executor {

    public static void main(String[] args) throws InterruptedException {
        SourceTask sourceTask = new SourceTask("Hi From Source 0", 0);
        SourceTask sourceTask1 = new SourceTask("Hi From Source 1", 1);
        Message msg0 = sourceTask.getMessage();
        Message msg1 = sourceTask1.getMessage();
        SinkTask sinkTask = new SinkTask();
        Communication communication1 = new Communication(sourceTask, sinkTask, msg0);
        Communication communication2 = new Communication(sourceTask1, sinkTask, msg1);

        Communicator communicator1 = new Communicator(communication1);
        Communicator communicator2 = new Communicator(communication2);

        Thread commsThread1 = new Thread(communicator1);
        commsThread1.start();

        Thread commsThread2 = new Thread(communicator2);
        commsThread2.start();
        //setting communication delay manually

        Thread.sleep(2000);

        Thread sinkThread = new Thread(sinkTask);
        sinkThread.start();



    }
}
