package edu.iu.dsc.tws.executor.api.shared.resource;

import edu.iu.dsc.tws.executor.api.shared.task.SinkTask;
import edu.iu.dsc.tws.executor.api.shared.task.SourceTask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Communicator implements Runnable {
    private Communication communication;
    private ReentrantLock lock = new ReentrantLock();

    public Communicator(Communication communication) {
        this.communication = communication;
    }

    public void run() {
        System.out.println("Running Communicator");
        lock.lock();
        try {
            this.communication.registerConnection();
            this.communication.registerSourceGeneration();
            sendMessage();

        } finally {
            lock.unlock();
        }
    }

    public boolean sendMessage() {
        boolean sentStatus = true;
        lock.lock();
        try{
            HashMap<SourceTask, SinkTask> cons =  this.communication.getConnections();
            HashMap<SourceTask, Message> msgsFromSrc = this.communication.getMsgFromSource();
            for(Map.Entry<SourceTask, SinkTask> comms : cons.entrySet()) {
                for (Map.Entry<SourceTask, Message> msgs : msgsFromSrc.entrySet()) {
                    if (comms.getKey().equals(msgs.getKey())) {
                        Message m = msgs.getValue();
                        SinkTask destination = comms.getValue();
                        destination.recieveMessage(comms.getKey(), m);
                    } else {
                        sentStatus = false;
                    }
                }
            }

        }finally {
            lock.unlock();
        }
        return sentStatus;
    }
}
