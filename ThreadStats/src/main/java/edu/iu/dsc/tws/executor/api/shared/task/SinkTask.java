package edu.iu.dsc.tws.executor.api.shared.task;

import edu.iu.dsc.tws.executor.api.shared.resource.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class SinkTask implements Runnable{

    private String message;
    private int id;
    private Message msg;
    private ReentrantLock lock = new ReentrantLock();
    private HashMap<SourceTask, Message> receivedMessages = new HashMap<SourceTask, Message>();

    public SinkTask(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public SinkTask(Message msg) {
        this.msg = msg;
    }

    public SinkTask() {

    }

    public void run() {
        System.out.println("Load Messages :");
        lock.lock();
        try {
            for(Map.Entry<SourceTask, Message> e : this.receivedMessages.entrySet()) {
                System.out.println("Src : " + e.getKey() + ", Msg : " + e.getValue().getMessage());
            }
        } finally {
            lock.unlock();
        }

    }

    public boolean recieveMessage(SourceTask src, Message msg) {
        System.out.println("Receiving Message : " + msg.getMessage());
        lock.lock();
        try {
            this.receivedMessages.put(src, msg);
        } finally {
            lock.unlock();
        }
        return true;
    }
}
