package edu.escuelaing.arep.entry;

import java.util.Date;

/**
 * Represents a message that will be stored in the database.
 * @author Zuly Vargas.
 */
public class Message {

    private String content;
    private String date;

    /**
     * Construct a new message with date system.
     * @param content - content of the message.
     */
    public Message(String content){
        this.content=content;
        this.date =java.time.LocalDate.now().toString();
    }

    /**
     * Construct a new message
     * @param content - content of the message.
     * @param date - insertion date.
     */
    public Message(String content, String date){
        this.content  = content;
        this.date = date;
    }

    /**
     * Return to message content.
     * @return content - message content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Updates the message content.
     * @param content - new message content.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Return to message date.
     * @return date - message date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Updates the message date.
     * @param date - new message date.
     */
    public void setDate(String date) {
        this.date = date;
    }
}
