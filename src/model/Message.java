package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {

    private String sender;
    private String content;
    private LocalDateTime timestamp;


    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }


    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getTimestampFormatted() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return timestamp.format(formatter);
    }


    @Override
    public String toString() {
        return "[" + getTimestampFormatted() + "] " + sender + ": " + content;
    }
}