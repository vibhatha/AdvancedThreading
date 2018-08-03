package edu.iu.dsc.tws.executor.api.shared.task;

import edu.iu.dsc.tws.executor.api.shared.resource.Message;

public class SourceTask implements Runnable {

    private String message = null;
    private int id = 0;

    public SourceTask(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public boolean send() {
        Message message = new Message(this.message, this.id);
        return true;
    }

    public void run() {
        System.out.println("Sending Message " + getMessage());
    }

    public Message getMessage() {
        Message newMessage = new Message(this.message, this.id);
        return newMessage.getMsg();
    }


}
