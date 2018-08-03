package edu.iu.dsc.tws.executor.api.shared.resource;

public class Message {
    private String message;
    private int id;

    public Message(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Message getMsg(){
        return new Message(this.message, this.id);
    }
}
