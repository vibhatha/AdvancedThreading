package edu.iu.dsc.tws.executor.api.shared.resource;

import edu.iu.dsc.tws.executor.api.shared.task.SinkTask;
import edu.iu.dsc.tws.executor.api.shared.task.SourceTask;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Communication {
    private ReentrantLock lock = new ReentrantLock();
    private SourceTask sourceTask;
    private SinkTask sinkTask;
    private Message message;
    private HashMap<SourceTask, Message> msgFromSource = new HashMap<SourceTask, Message>();
    private HashMap<SourceTask, SinkTask> connections = new HashMap<SourceTask, SinkTask>();
    private HashMap<SinkTask, Boolean> receivedMessages = new HashMap<SinkTask, Boolean>();

    public Communication(SourceTask sourceTask, SinkTask sinkTask, Message message) {
        this.sourceTask = sourceTask;
        this.sinkTask = sinkTask;
        this.message = message;
    }

    public void registerConnection() {
        this.connections.put(this.sourceTask, this.sinkTask);
    }

    public void registerSourceGeneration() {
        this.msgFromSource.put(this.sourceTask, this.message);
    }

    public void registerRecievedMessages() {

    }

    public HashMap<SourceTask, Message> getMsgFromSource() {
        return msgFromSource;
    }

    public void setMsgFromSource(HashMap<SourceTask, Message> msgFromSource) {
        this.msgFromSource = msgFromSource;
    }

    public HashMap<SourceTask, SinkTask> getConnections() {
        return connections;
    }

    public void setConnections(HashMap<SourceTask, SinkTask> connections) {
        this.connections = connections;
    }

    public HashMap<SinkTask, Boolean> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(HashMap<SinkTask, Boolean> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }
}
